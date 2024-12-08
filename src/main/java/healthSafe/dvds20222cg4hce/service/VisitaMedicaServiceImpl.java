package healthSafe.dvds20222cg4hce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.google.common.base.Objects;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.controller.request.visitamedica.VisitaMedicaRangoTiempo;
import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.VisitaMedicaRepository;
import healthSafe.dvds20222cg4hce.service.profesional.ProfesionalService;
import healthSafe.dvds20222cg4hce.utils.DateUtils;

@Service
public class VisitaMedicaServiceImpl implements VisitaMedicaService{
	
	private VisitaMedicaRepository repository;
	private HistoriaMedicaService historiaMedicaService;
	private InstitucionSaludService institucionSaludService;
	private ProfesionalService profesionalService;
	private DiagnosticoService diagnosticoService;

	@Autowired
	public VisitaMedicaServiceImpl(final VisitaMedicaRepository repository,
									final HistoriaMedicaService historiaMedicaService,
									final InstitucionSaludService institucionSaludService,
									final ProfesionalService profesionalService,
									final DiagnosticoService diagnosticoService) {
		this.repository = repository;
		this.historiaMedicaService = historiaMedicaService;
		this.institucionSaludService = institucionSaludService;
		this.profesionalService = profesionalService;
		this.diagnosticoService = diagnosticoService;
	}
	
	
	@Override
	public List<VisitaMedica> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<VisitaMedica> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public VisitaMedica save(VisitaMedica visitaMedica) throws BusinessException {
		
		HistoriaMedica historia = null;
		Profesional profesional = null;
		Especialidad especialidad = null;
		InstitucionSalud institucionSalud = null;
		Diagnostico diagnostico = null;
		
		if(visitaMedica.getHistoriaMedica() != null) {
			historia = getHistoriaMedica(visitaMedica.getHistoriaMedica().getId());
		}else {
			throw new BusinessException("La Historia Medica es obligatoria");
		}
		
		if(visitaMedica.getAtencionVirtual()) {
			if(visitaMedica.getInstitucionSalud() != null) {
				institucionSalud = getInstitucionSalud(visitaMedica.getInstitucionSalud().getId());
			}
		}else {
			if(visitaMedica.getInstitucionSalud() != null) {
				institucionSalud = getInstitucionSalud(visitaMedica.getInstitucionSalud().getId());
			}else {
				throw new BusinessException("La Institución de salud es obligatorio");
			}
		}
		
		if(visitaMedica.getProfesional() != null) {
			if(institucionSalud == null) {
				profesional = getProfesional(visitaMedica.getProfesional().getId());
			}else {
				profesional = getProfesionalFromCentroSalud(institucionSalud, visitaMedica.getProfesional().getId());
			}	
		}else {
			throw new BusinessException("El Profesional es obligatorio");
		}
		
		if(visitaMedica.getEspecialidad() != null) {
			especialidad = getEspecialidadFromProfesional(profesional, visitaMedica.getEspecialidad().getId());
		}else {
			throw new BusinessException("La Especialidad es obligatoria");
		}
		
		if(visitaMedica.getDiagnostico() != null) {
			diagnostico = getDiagnostico(visitaMedica.getDiagnostico().getId());
		}else {
			throw new BusinessException("El Diagnostico es obligatorio");
		}
	
		visitaMedica = VisitaMedica.builder()
				.atencionVirtual(visitaMedica.getAtencionVirtual())
				.fechaVisita(visitaMedica.getFechaVisita())
				.indicaciones(visitaMedica.getIndicaciones())
				.prescripciones(visitaMedica.getPrescripciones())
				.historiaMedica(historia)
				.institucionSalud(institucionSalud)
				.profesional(profesional)
				.especialidad(especialidad)
				.diagnostico(diagnostico)
				.activo(visitaMedica.getActivo())
				.build();
		
		visitaMedica = repository.save(visitaMedica);
		
		historiaMedicaService.addVisitaMedica(historia.getId(), visitaMedica);
		
		return visitaMedica;
	}
	
	@Override
	public void addPrescripcion(Long id, Prescripcion prescripcion) throws BusinessException{
		VisitaMedica visitaMedica = findById(id);
		visitaMedica.addPrescripcion(prescripcion);
	}

	@Override
	public VisitaMedica update(VisitaMedica visitaMedica, VisitaMedica newVisitaMedica) throws BusinessException {
		
		
		HistoriaMedica historia = null;
		Profesional profesional = null;
		Especialidad especialidad = null;
		InstitucionSalud institucionSalud = null;
		Diagnostico diagnostico = null;
		
		if(newVisitaMedica.getHistoriaMedica() != null) {
			historia = getHistoriaMedica(newVisitaMedica.getHistoriaMedica().getId());
		}else {
			throw new BusinessException("La Historia Medica es obligatoria");
		}
	
		if(visitaMedica.getAtencionVirtual()) {
			if(visitaMedica.getInstitucionSalud() != null) {
				institucionSalud = getInstitucionSalud(visitaMedica.getInstitucionSalud().getId());
			}
		}else {
			if(visitaMedica.getInstitucionSalud() != null) {
				institucionSalud = getInstitucionSalud(visitaMedica.getInstitucionSalud().getId());
			}else {
				throw new BusinessException("La Institución de salud es obligatorio");
			}
		}
		
		if(visitaMedica.getProfesional() != null) {
			if(institucionSalud == null) {
				profesional = getProfesional(visitaMedica.getProfesional().getId());
			}else {
				profesional = getProfesionalFromCentroSalud(institucionSalud, visitaMedica.getProfesional().getId());
			}	
		}else {
			throw new BusinessException("El Profesional es obligatorio");
		}
		
		if(newVisitaMedica.getEspecialidad() != null) {
			especialidad = getEspecialidadFromProfesional(profesional, newVisitaMedica.getEspecialidad().getId());
		}else {
			throw new BusinessException("La Especialidad es obligatoria");
		}
		
		if(newVisitaMedica.getDiagnostico() != null) {
			diagnostico = getDiagnostico(newVisitaMedica.getDiagnostico().getId());
		}else {
			throw new BusinessException("El Diagnostico es obligatorio");
		}
	
		visitaMedica = VisitaMedica.builder()
				.id(visitaMedica.getId())
				.atencionVirtual(newVisitaMedica.getAtencionVirtual())
				.fechaVisita(newVisitaMedica.getFechaVisita())
				.indicaciones(newVisitaMedica.getIndicaciones())
				.prescripciones(visitaMedica.getPrescripciones())
				.historiaMedica(historia)
				.institucionSalud(institucionSalud)
				.profesional(profesional)
				.especialidad(especialidad)
				.diagnostico(diagnostico)
				.activo(newVisitaMedica.getActivo())
				.build();
		
		return repository.save(visitaMedica);
		
		//historiaMedicaService.addVisitaMedica(historia.getId(), visitaMedica);
		
	}

	@Override
	public VisitaMedica findById(Long id) throws BusinessException {
		
		Optional<VisitaMedica> visitaOptional = repository.findById(id);
		if(visitaOptional.isPresent()) return visitaOptional.get();
		throw new BusinessException("No se pudo encontrar la Visita Medica con el id: " + id);
	}

	@Override
	public List<VisitaMedica> getVisitasMedicasByHistoriaMedica(Long historiaMedicaId) throws BusinessException {	
		return repository.findByHistoriaMedicaId(historiaMedicaId);
	}
	
	@Override
	public List<VisitaMedica> getVisitasMedicasWithDocumentsByHistoriaMedica(Long historiaMedicaId) throws BusinessException {
		
		List<VisitaMedica> visitasMedicas = getVisitasMedicasByHistoriaMedica(historiaMedicaId);
		visitasMedicas.forEach(vta -> 
			vta.getPrescripciones().removeIf(pcp -> pcp.getEstudios().isEmpty() && pcp.getRecetas().isEmpty()));
		return visitasMedicas;
	}
	
	@Override
	public List<VisitaMedica> filterVisitasMedicasByRangeTime(List<VisitaMedica> requestVisitasMedicas,
			VisitaMedicaRangoTiempo timeRange) throws BusinessException {
		
		List<VisitaMedica> visitasMedicasEnRango = new ArrayList<VisitaMedica>();
		if(timeRange.getGiveLatest()) {
			List<VisitaMedica> visitasMedicasWithDateOrder = requestVisitasMedicas.stream()
								.sorted((v1, v2) -> Long.compare(v1.getFechaVisita(), v2.getFechaVisita()))
								.collect(Collectors.toList());
			visitasMedicasEnRango.add(visitasMedicasWithDateOrder.get(visitasMedicasWithDateOrder.size()-1));
			
		}else {
			if(timeRange.getLastDate() == null || timeRange.getStartDate() == null) {
				throw new BusinessException("Debes ingresar las dos fechas");
			}
			requestVisitasMedicas.stream()
			.filter(vta -> DateUtils.dateIsInRange(timeRange.getStartDate(), 
													timeRange.getLastDate(), 
													DateUtils.getStringDate(vta.getFechaVisita())))
			.sorted((v1, v2) -> Long.compare(v1.getFechaVisita(), v2.getFechaVisita()))
			.forEach(vta -> visitasMedicasEnRango.add(vta));
		}
		
		return visitasMedicasEnRango;
	}
	
	@Override
	public VisitaMedica setCentroSalud(Long visitaMedicaId, Long centroSaludId) throws BusinessException{
		VisitaMedica visitaMedica = findById(visitaMedicaId);
		InstitucionSalud institucionSalud = getInstitucionSalud(centroSaludId);
		
		visitaMedica.setInstitucionSalud(institucionSalud);
		
		return update(visitaMedica, visitaMedica);
	}
	
	@Override
	public VisitaMedica setProfesional(Long visitaMedicaId, Long profesionalId) throws BusinessException{
		VisitaMedica visitaMedica = findById(visitaMedicaId);
		Profesional profesional = getProfesional(profesionalId);
		
		visitaMedica.setProfesional(profesional);
		
		return update(visitaMedica, visitaMedica);
	}
	
	@Override
	public void delete(VisitaMedica visitaMedica) {
		
		repository.delete(visitaMedica);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}
	
	private HistoriaMedica getHistoriaMedica(Long id) throws BusinessException{
		return historiaMedicaService.findById(id);
	}
	
	private InstitucionSalud getInstitucionSalud(Long id) throws BusinessException{
		return institucionSaludService.findById(id);
	}
	
	private Profesional getProfesional(Long id) throws BusinessException{
		return profesionalService.findById(id);
	}
	
	private Diagnostico getDiagnostico(Long id) throws BusinessException{
		return diagnosticoService.findById(id);
	}
	
	private Profesional getProfesionalFromCentroSalud(InstitucionSalud institucionSalud, Long profesionalId) throws BusinessException{
		return institucionSalud.getProfesionales().stream()
				.filter(pfl -> Objects.equal(pfl.getId(), profesionalId))
				.findFirst()
				.orElseThrow(() -> new BusinessException("El centro de salud no contiene ese profesional"));
	}
	
	private Especialidad getEspecialidadFromProfesional(Profesional profesional, Long especialidadId) throws BusinessException{
		return profesional.getEspecialidades().stream()
												.filter(epc -> Objects.equal(epc.getId(), especialidadId))
												.findFirst()
												.orElseThrow(() -> new BusinessException("El profesional no contiene esa especialidad"));
	}

	@Override
	public VisitaMedica activarVisitaMedica(VisitaMedica visita) throws BusinessException {
		
		visita.setActivo(true);
		return update(visita, visita);
	}

	@Override
	public void desactivarVisitaMedica(VisitaMedica visita) throws BusinessException {
		
		visita.setActivo(false);
		update(visita, visita);
	}
}
