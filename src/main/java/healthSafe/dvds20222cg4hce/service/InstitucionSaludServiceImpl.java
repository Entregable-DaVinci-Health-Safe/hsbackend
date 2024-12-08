package healthSafe.dvds20222cg4hce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.InstitucionSaludRepository;
import healthSafe.dvds20222cg4hce.service.profesional.ProfesionalService;
import healthSafe.dvds20222cg4hce.service.usuario.ContactoService;
import healthSafe.dvds20222cg4hce.service.usuario.DireccionService;
import healthSafe.dvds20222cg4hce.utils.ContactoUtils;
import healthSafe.dvds20222cg4hce.utils.DireccionUtils;

@Service
public class InstitucionSaludServiceImpl implements InstitucionSaludService{

	private InstitucionSaludRepository repository;
	private ContactoService contactoService;
	private DireccionService direccionService;
	private ProfesionalService profesionalService;
	private HistoriaMedicaService historiaMedicaService;
	
	@Autowired
	public InstitucionSaludServiceImpl(final InstitucionSaludRepository repository,
			final ContactoService contactoService,
			final ProfesionalService profesionalService,
			final HistoriaMedicaService historiaMedicaService,
			final DireccionService direccionService) {
		this.repository = repository;
		this.contactoService = contactoService;
		this.profesionalService = profesionalService;
		this.historiaMedicaService = historiaMedicaService;
		this.direccionService = direccionService;
	}
	
	@Override
	public List<InstitucionSalud> listInstitucionesSalud() {
		
		return repository.findAllByOrderByNombreAsc();
	}

	@Override
	public Page<InstitucionSalud> listInstitucionesSalud(Pageable pageable) {
		
		return repository.findAllByOrderByNombreAsc(pageable);
	}

	@Override
	public InstitucionSalud save(InstitucionSalud institucionSalud) throws BusinessException {
		
		
		HistoriaMedica historia = null;
		if(institucionSalud.getHistoriaMedica().getId() != null) {
			historia = getHistoriaMedica(institucionSalud.getHistoriaMedica().getId());
		}else {
			throw new BusinessException("La Historia Medica es obligatoria");
		}
		
		institucionSalud = createDireccion(institucionSalud, institucionSalud.getDireccion());
		
		List<ContactoInstitucionSalud> contactos = new ArrayList<ContactoInstitucionSalud>();
		if(institucionSalud.getContactos() != null) contactos = ContactoUtils.getListContactosCentrosSalud(institucionSalud.getContactos());
		
		List<Profesional> profesionales = new ArrayList<Profesional>();
		if(institucionSalud.getProfesionales() != null) profesionales = getProfesionales(institucionSalud.getProfesionales());
		
		institucionSalud = InstitucionSalud.builder()
				.nombre(institucionSalud.getNombre())
				.historiaMedica(historia)
				.profesionales(profesionales)
				.contactos(contactos)
				.direccion(institucionSalud.getDireccion())
				.activo(institucionSalud.getActivo())
				.build();
		
		institucionSalud = repository.save(institucionSalud);
		
		historiaMedicaService.addCentroSalud(historia.getId(), institucionSalud);
		
		return institucionSalud;
	}

	@Override
	public InstitucionSalud update(InstitucionSalud institucionSalud) throws BusinessException {
		
		if(institucionSalud.getId() == null) throw new BusinessException("No se puede actualizar un centro de salud que no fue creado");
		
		if(existRazonSocialwithSameDireccionInSameHistoriaMedica(
				institucionSalud, institucionSalud.getDireccion())) throw new BusinessException("ERROR");
		
		return  repository.save(institucionSalud);
		
	}

	@Override
	public InstitucionSalud findById(Long id) throws BusinessException {
		
		Optional<InstitucionSalud> institucionSaludOptional = repository.findById(id);
		if(institucionSaludOptional.isPresent()) return institucionSaludOptional.get();
		throw new BusinessException("No se pudo encontrar el centro de salud con el id: " + id);
	}
	
	@Override
	public List<InstitucionSalud> getInstitucionesSaludByHistoriaMedica(Long historiaMedicaId) throws BusinessException {
		
		List<InstitucionSalud> institucionSalud = repository.findByHistoriaMedicaId(historiaMedicaId);
		if(!institucionSalud.isEmpty()) return institucionSalud;
		throw new BusinessException("No hay centros de salud asociados a la historia medica"); 
	}
	
	@Override
	public List<InstitucionSalud> getInstitucionesSaludByProfesionales(Long profesionalId) throws BusinessException {
		
		List<InstitucionSalud> institucionSalud = repository.findInstitucionesSaludByProfesionalesIdOrderByNombreAsc(profesionalId);
		if(!institucionSalud.isEmpty()) return institucionSalud;
		throw new BusinessException("No hay centros de salud asociados a este profesional"); 
	}

	@Override
	public InstitucionSalud addProfesional(Long institucionSaludId, Long profesionalId) throws BusinessException {
		
		InstitucionSalud institucionSalud = findById(institucionSaludId);
		Profesional profesional =  getProfesional(profesionalId);
		
		if(!tieneProfesional(institucionSalud, profesional)) {
			institucionSalud.addProfesional(profesional);
		}

		return update(institucionSalud);
	}
	
	@Override
	public InstitucionSalud removeProfesional(Long institucionSaludId, Long profesionalId) throws BusinessException {
		
		InstitucionSalud institucionSalud = findById(institucionSaludId);
		Profesional profesional =  getProfesional(profesionalId);
		
		institucionSalud.removeProfesional(profesional);;
		return update(institucionSalud);
	}
	
	@Override
	public InstitucionSalud addProfesionales(InstitucionSalud institucionSalud, List<Long> profesionalesIds) throws BusinessException {
		
		for(Long profesionalId: profesionalesIds) {
			Profesional profesional =  getProfesional(profesionalId);
			if(!tieneProfesional(institucionSalud, profesional)) {
				institucionSalud.addProfesional(profesional);
			}
		}
		return update(institucionSalud);
	}
	
	@Override
	public InstitucionSalud removeProfesionales(InstitucionSalud institucionSalud, List<Long> profesionalesIds) throws BusinessException {
		
		
		for(Long profesionalId: profesionalesIds) {
			Profesional profesional =  getProfesional(profesionalId);
			institucionSalud.removeProfesional(profesional);
		}
		
		return update(institucionSalud);
	}

	@Override
	public InstitucionSalud createContacto(InstitucionSalud institucionSalud, ContactoInstitucionSalud contacto) throws BusinessException {
		
		contacto.setInstitucionSalud(institucionSalud);
		institucionSalud.addContacto(contactoService.save(contacto));;
		return institucionSalud;
	}
	
	@Override
	public InstitucionSalud updateContacto(InstitucionSalud institucionSalud, Long contactoId, ContactoInstitucionSalud contacto)
			throws BusinessException, ClassCastException {
		
		ContactoInstitucionSalud contactoModificar = (ContactoInstitucionSalud) contactoService.findById(contactoId);
		contacto.setInstitucionSalud(institucionSalud);
		
		contactoModificar = contactoService.update(contactoModificar, contacto);
		institucionSalud.updateContacto(contactoModificar);
		
		return institucionSalud;
	}

	@Override
	public InstitucionSalud removeContacto(InstitucionSalud institucionSalud, Long contactoId) throws BusinessException {
		
		Contacto contacto = contactoService.findById(contactoId);
		institucionSalud.removeContacto(contacto);
		contactoService.delete(contacto);
		return institucionSalud;
	}
	
	@Override
	public InstitucionSalud createDireccion(InstitucionSalud institucionSalud, DireccionInstitucionSalud direccion) throws BusinessException {
		
		if(existRazonSocialwithSameDireccionInSameHistoriaMedica(
				institucionSalud, direccion)) {
			throw new BusinessException("ERROR");
		}
		institucionSalud.setDireccion(direccionService.save(direccion));
		return institucionSalud;
	}
	
	@Override
	public InstitucionSalud updateDireccion(InstitucionSalud institucionSalud, Long direccionId, DireccionInstitucionSalud direccion)
			throws BusinessException, ClassCastException {
		
		if(existRazonSocialwithSameDireccionInSameHistoriaMedica(
				institucionSalud, direccion)) {
			throw new BusinessException("ERROR");
		}
		
		DireccionInstitucionSalud direccionModificar = (DireccionInstitucionSalud) direccionService.findById(direccionId);
		direccionModificar = direccionService.update(direccionModificar, direccion);
		
		institucionSalud.setDireccion(direccionModificar);
		return institucionSalud;

	}
	
	@Override
	public long count() {
		
		return repository.count();
	}
	
	private Boolean tieneProfesional(InstitucionSalud institucionSalud, Profesional profesional) {
		return institucionSalud.tieneProfesional(profesional);
	}

	private HistoriaMedica getHistoriaMedica(Long id) throws BusinessException{
		return historiaMedicaService.findById(id);
	}
	
	private Boolean existRazonSocialwithSameDireccionInSameHistoriaMedica(InstitucionSalud institucionSalud, Direccion direccion) throws BusinessException {
		if(direccion == null) return false;
		List<InstitucionSalud> centroSaludMatch = repository
				.findByNombreAndHistoriaMedica_IdAndDireccion_Direccion(
						institucionSalud.getNombre(), 
						institucionSalud.getHistoriaMedica().getId(), 
						direccion.getDireccion());
		
		return centroSaludMatch.stream().anyMatch(cts -> cts.getActivo() && cts.getId() != institucionSalud.getId());
	
	}
	
	private Profesional getProfesional(Long profesionalId) throws BusinessException{
		if(profesionalId != null) return profesionalService.findById(profesionalId);
		throw new BusinessException("El Profesional es obligatoria");
	}
	
	private List<Profesional> getProfesionales(List<Profesional> requestProfesionales) throws BusinessException{
		List<Profesional> profesionales = new ArrayList<Profesional>();
		for(Profesional requestProfesional: requestProfesionales) {
			if(requestProfesional.getActivo()) {
				Profesional profesional = Profesional.builder()
						.nombre(requestProfesional.getNombre())
						.contactos(ContactoUtils.getListContactosProfesionales(requestProfesional.getContactos()))
						.direcciones(DireccionUtils.getListDireccionesProfesionales(requestProfesional.getDirecciones()))
						.especialidades(profesionalService.getProfesionalEspecialidades(requestProfesional))
						.build();
						
				profesionales.add(profesional);
			}
		}
		
		return profesionales;
	}

	@Override
	public InstitucionSalud activarInstitucionSalud(InstitucionSalud institucionSalud) throws BusinessException {
		
		institucionSalud.setActivo(true);
		return update(institucionSalud);
	}

	@Override
	public void desactivarInstitucionSalud(InstitucionSalud institucionSalud) throws BusinessException {
		
		institucionSalud.setActivo(false);
		update(institucionSalud);
	}

}
