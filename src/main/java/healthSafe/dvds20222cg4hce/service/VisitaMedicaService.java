package healthSafe.dvds20222cg4hce.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.controller.request.visitamedica.VisitaMedicaRangoTiempo;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface VisitaMedicaService {
	List<VisitaMedica> list();
	Page<VisitaMedica> list(Pageable pageable);
	
	VisitaMedica save(VisitaMedica visitaMedica) throws BusinessException;
	
	VisitaMedica update(VisitaMedica visitaMedica, VisitaMedica newVisitaMedica) throws BusinessException;
	
	VisitaMedica findById(Long id) throws BusinessException;
	
	//List<VisitaMedica> findRevisionsById(Long id) throws BusinessException;
	List<VisitaMedica> getVisitasMedicasByHistoriaMedica(Long historiaMedicaId)throws BusinessException;
	List<VisitaMedica> getVisitasMedicasWithDocumentsByHistoriaMedica(Long historiaMedicaId)throws BusinessException;
	List<VisitaMedica> filterVisitasMedicasByRangeTime(List<VisitaMedica> requestVisitaMedica, VisitaMedicaRangoTiempo rangoTiempo) throws BusinessException;
	
	VisitaMedica setCentroSalud(Long visitaMedicaId, Long centroSaludId) throws BusinessException;
	
	VisitaMedica setProfesional(Long visitaMedicaId, Long profesionalId) throws BusinessException;
	
	void addPrescripcion(Long id, Prescripcion prescripcion) throws BusinessException;
	void delete(VisitaMedica visitaMedica);
	void delete(Long id);
	
	VisitaMedica activarVisitaMedica(VisitaMedica visita) throws BusinessException;
	void desactivarVisitaMedica(VisitaMedica visita) throws BusinessException;
	
	long count();
}
