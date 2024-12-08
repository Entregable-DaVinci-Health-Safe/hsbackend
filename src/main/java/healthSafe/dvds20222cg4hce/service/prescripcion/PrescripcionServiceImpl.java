package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.factory.ArgFactory;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.PaisPrescripcion;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.prescripcion.PrescripcionRepository;
import healthSafe.dvds20222cg4hce.service.VisitaMedicaService;
import healthSafe.dvds20222cg4hce.utils.PrescripcionUtils;

@Service
public class PrescripcionServiceImpl implements PrescripcionService{
	
	private PrescripcionRepository repository;
	private EstudioService estudioService;
	private RecetaService recetaService;
	private VisitaMedicaService visitaMedicaService;
	
	@Autowired
	public PrescripcionServiceImpl(final PrescripcionRepository repository,
									final EstudioService estudioService,
									final RecetaService recetaService,
									final VisitaMedicaService visitaMedicaService) {
		this.repository = repository;
		this.estudioService = estudioService;
		this.recetaService = recetaService;
		this.visitaMedicaService = visitaMedicaService;
	}

	@Override
	public List<Prescripcion> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<Prescripcion> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
	
	@Override
	public List<Prescripcion> getPrescripcionesWithDocumentsByHistoriaMedicaId(Long historiaMedicaId) throws BusinessException {
		
		List<Prescripcion> prescripciones = null;
		List<Prescripcion> prescripcionesWithDocuments = new ArrayList<Prescripcion>();
		prescripciones = repository.findByVisitaMedica_HistoriaMedica_Id(historiaMedicaId);
		prescripciones.forEach(pcp -> {
			if(!pcp.getEstudios().isEmpty() || !pcp.getRecetas().isEmpty()) {
				prescripcionesWithDocuments.add(pcp);
			} });
		return prescripcionesWithDocuments;
	}

	@Override
	public Prescripcion save(Prescripcion prescripcion) throws BusinessException {
		
		List<Receta> recetas = new ArrayList<Receta>();
		if(prescripcion.getRecetas() != null) recetas = PrescripcionUtils.getListRecetas(prescripcion.getRecetas());
		
		List<Estudio> estudios = new ArrayList<Estudio>();
		if(prescripcion.getEstudios() != null) estudios = PrescripcionUtils.getListEstudios(prescripcion.getEstudios());
		
		VisitaMedica visita = null;
		if(prescripcion.getVisitaMedica().getId() != null) {
			visita = getVisitaMedica(prescripcion.getVisitaMedica().getId());
		}else {
			throw new BusinessException("La Visita Medica es obligatoria");
		}
		
		prescripcion = Prescripcion.builder()
						.paisPrescripcion(prescripcion.getPaisPrescripcion())
						.recetas(recetas)
						.estudios(estudios)
						.visitaMedica(visita)
						.build();
		
		prescripcion = repository.save(prescripcion);
		
		visitaMedicaService.addPrescripcion(visita.getId(), prescripcion);
		
		return prescripcion;
	}

	@Override
	public Prescripcion createReceta(Prescripcion prescripcion, Receta receta) throws BusinessException {
		
		if(prescripcion.getPaisPrescripcion().getDescripcion().equals("Argentina")) {
			prescripcion.setPrescripcionComponentFactory(new ArgFactory());
		}
		Receta newReceta = prescripcion.getReceta();
		if(newReceta instanceof ArgReceta) {
			newReceta.setPrescripcion(prescripcion);
			((ArgReceta) newReceta).setTipoReceta(((ArgReceta) receta).getTipoReceta());
			newReceta.setUrl(receta.getUrl());
			newReceta.setDescripcion(receta.getDescripcion());
			newReceta.setFechaCreado(receta.getFechaCreado());
			newReceta = recetaService.save((ArgReceta)newReceta);
			prescripcion.addReceta(newReceta);
			return prescripcion;
		}else {
			throw new BusinessException("No se puede crear la receta en base al pais de la prescripcion");
		}
	}

	@Override
	public Prescripcion createEstudio(Prescripcion prescripcion, Estudio estudio) throws BusinessException {
		
		if(prescripcion.getPaisPrescripcion().getDescripcion().equals("Argentina")) {
			prescripcion.setPrescripcionComponentFactory(new ArgFactory());
		}
		Estudio newEstudio = prescripcion.getEstudio();
		if(newEstudio instanceof ArgEstudio) {
			newEstudio.setPrescripcion(prescripcion);
			((ArgEstudio) newEstudio).setTipoEstudio(((ArgEstudio) estudio).getTipoEstudio());
			newEstudio.setUrl(estudio.getUrl());
			newEstudio.setDescripcion(estudio.getDescripcion());
			newEstudio.setFechaCreado(estudio.getFechaCreado());
			newEstudio = estudioService.save((ArgEstudio)newEstudio);
			prescripcion.addEstudio(newEstudio);
			return prescripcion;
		}else {
			throw new BusinessException("No se puede crear el estudio en base al pais de la prescripcion");
		}
		
	}

	@Override
	public Prescripcion update(Prescripcion prescripcion) throws BusinessException {
		
		return null;
	}

	@Override
	public Prescripcion findById(Long id) throws BusinessException {
		
		Optional<Prescripcion> prescripcionOptional = repository.findById(id);
		if(prescripcionOptional.isPresent()) return prescripcionOptional.get();
		throw new BusinessException("No se pudo encontrar la prescripcion con la id: " + id);
	}

	@Override
	public void delete(Prescripcion prescripcion) {
		
		repository.delete(prescripcion);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	
	@Override
	public long count() {
		
		return repository.count();
	}

	@Override
	public List<PaisPrescripcion> getPaisPrescripcion() {
		
		return PaisPrescripcion.getPaisPrescripciones();
	}
	
	private VisitaMedica getVisitaMedica(Long id) throws BusinessException{
		return visitaMedicaService.findById(id);
	}

	@Override
	public void deleteEstudio(Prescripcion prescripcion, Long id) {
		
		prescripcion.deleteEstudio(id);
		estudioService.delete(id);
	}

	@Override
	public void deleteReceta(Prescripcion prescripcion, Long id) {
		
		prescripcion.deleteReceta(id);
		recetaService.delete(id);
	}


}
