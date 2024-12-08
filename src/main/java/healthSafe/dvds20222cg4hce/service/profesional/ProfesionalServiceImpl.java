package healthSafe.dvds20222cg4hce.service.profesional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.exception.TipoAlerta;
import healthSafe.dvds20222cg4hce.repository.profesional.ProfesionalRepository;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;
import healthSafe.dvds20222cg4hce.service.usuario.ContactoService;
import healthSafe.dvds20222cg4hce.service.usuario.DireccionService;
import healthSafe.dvds20222cg4hce.utils.ContactoUtils;
import healthSafe.dvds20222cg4hce.utils.DireccionUtils;

@Service
public class ProfesionalServiceImpl implements ProfesionalService{
	
	private ProfesionalRepository repository;
	private HistoriaMedicaService historiaMedicaService;
	private DireccionService direccionService;
	private ContactoService contactoService;
	private EspecialidadService especialidadService;
	
	@Autowired
	public ProfesionalServiceImpl(final ProfesionalRepository repository, 
			final HistoriaMedicaService historiaMedicaService,
			final DireccionService direccionService,
			final ContactoService contactoService,
			final EspecialidadService especialidadService) {
		this.repository = repository;
		this.historiaMedicaService = historiaMedicaService;
		this.direccionService = direccionService;
		this.contactoService = contactoService;
		this.especialidadService = especialidadService;
	}
	
	@Override
	public List<Profesional> listProfesionales() {
		
		return repository.findAllByOrderByNombreAsc();
	}

	@Override
	public Page<Profesional> listProfesionales(Pageable pageable) {
		
		return repository.findAllByOrderByNombreAsc(pageable);
	}

	@Override
	public Profesional save(Profesional profesional) throws BusinessException {
		
		HistoriaMedica historia = null;
		
		if(repository.existsByMatriculaAndTipoMatriculaAndHistoriaMedicaIdAndActivoTrue(profesional.getMatricula(), 
																		profesional.getTipoMatricula(),
																		profesional.getHistoriaMedica().getId())) {
			throw new BusinessException("PRF01", "Ya existe un profesional con la misma matricula", TipoAlerta.ERROR);
		}
		
		if(profesional.getHistoriaMedica().getId() != null) {
			historia = getHistoriaMedica(profesional.getHistoriaMedica().getId());
		}else {
			throw new BusinessException("La Historia Medica es obligatoria");
		}
		
		List<DireccionProfesional> direcciones = new ArrayList<DireccionProfesional>();
		if(profesional.getDirecciones() != null) direcciones = DireccionUtils.getListDireccionesProfesionales(profesional.getDirecciones());
		
		List<ContactoProfesional> contactos = new ArrayList<ContactoProfesional>();
		if(profesional.getContactos() != null) contactos = ContactoUtils.getListContactosProfesionales(profesional.getContactos());
		
		List<Especialidad> especialidades = new ArrayList<Especialidad>();
		if(profesional.getEspecialidades() != null) especialidades = getProfesionalEspecialidades(profesional);
				
		profesional = Profesional.builder()
				.nombre(profesional.getNombre())
				.tipoMatricula(profesional.getTipoMatricula())
				.matricula(profesional.getMatricula())
				.direcciones(direcciones)
				.contactos(contactos)
				.historiaMedica(historia)
				.especialidades(especialidades)
				.activo(profesional.getActivo())
				.build();
		
		profesional = repository.save(profesional);
		
		historiaMedicaService.addProfesional(historia.getId(), profesional);
		
		return profesional;
	}

	@Override
	public Profesional update(Profesional profesional) throws BusinessException {
		
		if(profesional.getId() != null) return repository.save(profesional);
		throw new BusinessException("No se puede actualizar un profesional que no ha sido creado");
	}
	
	@Override
	public Profesional createDireccion(Profesional profesional, DireccionProfesional direccion) throws BusinessException {
		
		
		direccion.setProfesional(profesional);
		profesional.addDireccion(direccionService.save(direccion));
		return profesional;
	}
	
	@Override
	public Profesional updateDireccion(Profesional profesional, Long direccionId, DireccionProfesional direccion)
			throws BusinessException, ClassCastException {
		
		DireccionProfesional direccionModificar = (DireccionProfesional) direccionService.findById(direccionId);
		direccion.setProfesional(profesional);
		
		direccionModificar = direccionService.update(direccionModificar, direccion);
		profesional.updateDireccion(direccionModificar);
		
		return profesional;
	}

	@Override
	public Profesional removeDireccion(Profesional profesional, Long direccionId) throws BusinessException {
		
		Direccion direccion = direccionService.findById(direccionId);
		profesional.removeDireccion(direccion);
		direccionService.delete(direccion);
		return profesional;
	}
	
	@Override
	public Profesional createContacto(Profesional profesional, ContactoProfesional contacto) throws BusinessException {
		
		contacto.setProfesional(profesional);
		profesional.addContacto(contactoService.save(contacto));;
		return profesional;
	}
	
	@Override
	public Profesional updateContacto(Profesional profesional, Long contactoId, ContactoProfesional contacto)
			throws BusinessException {
		
		ContactoProfesional contactoModificar = (ContactoProfesional) contactoService.findById(contactoId);
		contacto.setProfesional(profesional);
		
		contactoModificar = contactoService.update(contactoModificar, contacto);
		profesional.updateContacto(contactoModificar);
		
		return profesional;
	}

	@Override
	public Profesional removeContacto(Profesional profesional, Long contactoId) throws BusinessException {
		
		Contacto contacto = contactoService.findById(contactoId);
		profesional.removeContacto(contacto);
		contactoService.delete(contacto);
		return profesional;
	}

	@Override
	public Profesional findById(Long id) throws BusinessException {
		
		Optional<Profesional> profesionalOptional = repository.findById(id);
		if(profesionalOptional.isPresent()) return profesionalOptional.get();
		throw new BusinessException("No se pudo encontrar el profesional con el id: " + id);
	}
	
	@Override
	public List<Profesional> getProfesionalesByHistoriaMedica(Long historiaMedicaId)throws BusinessException{
		List<Profesional> profesionales = repository.findByHistoriaMedicaId(historiaMedicaId);
		if(!profesionales.isEmpty()) return profesionales;
		throw new BusinessException("No hay profesionales asociados a la historia medica"); 
	}
	
	@Override
	public List<Profesional> getProfesionalesByCentrosSalud(Long centroSaludId) throws BusinessException {
		
		List<Profesional> profesionales = repository.findProfesionalesByInstitucionesSaludIdOrderByNombreAsc(centroSaludId);
		if(!profesionales.isEmpty()) return profesionales;
		throw new BusinessException("No hay profesionales asociados al centro de salud"); 
	}
	
	@Override
	public long count() {
		
		return repository.count();
	}
	
	@Override
	public Profesional addEspecialidad(Long profesionalId, Long especialidadId) throws BusinessException {
		
		Profesional profesional = findById(profesionalId);
		Especialidad especialidad = getEspecialidad(especialidadId);
		
		if(!tieneEspecialidad(profesional, especialidad)) {
			profesional.addEspecialidad(especialidad);
		}
		
		return update(profesional);
	}

	@Override
	public Profesional removeEspecialidad(Long profesionalId, Long especialidadId) throws BusinessException {
		
		Profesional profesional = findById(profesionalId);
		Especialidad especialidad = getEspecialidad(especialidadId);
		
		profesional.removeEspecialidad(especialidad);
		
		return update(profesional);

	}
	
	@Override
	public Profesional addEspecialidades(Profesional profesional, List<Long> especialidadesIds)
			throws BusinessException {
		
		for(Long especialidadId: especialidadesIds) {
			Especialidad especialidad = especialidadService.findById(especialidadId);
			if(!tieneEspecialidad(profesional, especialidad)) {
				profesional.addEspecialidad(especialidad);
			}
		}
		return update(profesional);
	}

	@Override
	public Profesional removeEspecialidades(Profesional profesional, List<Long> especialidadesIds)
			throws BusinessException {
		
		for(Long especialidadId: especialidadesIds) {
			profesional.removeEspecialidad(especialidadService.findById(especialidadId));
		}
		return update(profesional);
	}
	
	@Override
	public List<Especialidad> getProfesionalEspecialidades(Profesional profesional) throws BusinessException {
		
		return especialidadService.findEspecialidadesByProfesionalId(profesional.getId());
	}
	
	private HistoriaMedica getHistoriaMedica(Long id) throws BusinessException{
		return historiaMedicaService.findById(id);
	}
	
	private Especialidad getEspecialidad(Long especialidadId) throws BusinessException{
		if(especialidadId != null) return especialidadService.findById(especialidadId);
		throw new BusinessException("La Especialidad es obligatoria");
	}
	
	private Boolean tieneEspecialidad(Profesional profesional, Especialidad especialidad) {
		return profesional.tieneEspecialidad(especialidad);
	}
	
	@Override
	public Profesional activarProfesional(Profesional profesional) throws BusinessException {
		
		profesional.setActivo(true);
		return update(profesional);
	}

	@Override
	public void desactivarProfesional(Profesional profesional) throws BusinessException {
		
		profesional.setActivo(false);
		update(profesional);
	}
	
	private Boolean isActive(Long profesionalId) {
		return repository.existsByIdAndActivoFalse(profesionalId);
	}


}
