package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.PaisPrescripcion;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface PrescripcionService {
	
	List<Prescripcion> list();
	Page<Prescripcion> list(Pageable pageable);
	
	List<Prescripcion> getPrescripcionesWithDocumentsByHistoriaMedicaId(Long historiaMedicaId) throws BusinessException;
	
	Prescripcion save(Prescripcion prescripcion) throws BusinessException;
	Prescripcion createReceta(Prescripcion prescripcion, Receta receta) throws BusinessException;
	Prescripcion createEstudio(Prescripcion prescripcion, Estudio estudio) throws BusinessException;
	
	Prescripcion update(Prescripcion prescripcion) throws BusinessException;
	
	Prescripcion findById(Long id) throws BusinessException;
	
	void delete(Prescripcion prescripcion);
	void delete(Long id);
	void deleteEstudio(Prescripcion prescripcion, Long id);
	void deleteReceta(Prescripcion prescripcion, Long id);
	
	long count();
	
	List<PaisPrescripcion> getPaisPrescripcion();
}
