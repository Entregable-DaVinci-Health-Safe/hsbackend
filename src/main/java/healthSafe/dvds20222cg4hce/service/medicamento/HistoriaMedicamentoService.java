package healthSafe.dvds20222cg4hce.service.medicamento;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface HistoriaMedicamentoService {
	
	HistoriaMedicamento save(HistoriaMedicamento historiaMedicamento) throws BusinessException;
	HistoriaMedicamento update(HistoriaMedicamento historiaMedicamento) throws BusinessException;
	
	HistoriaMedicamento findById(Long id) throws BusinessException;
	
	List<HistoriaMedicamento> getHistoriaMedicamentosByHistoriaMedica(Long historiaMedicaId) throws BusinessException;
	
	void delete(HistoriaMedicamento historiaMedicamento) throws BusinessException;
	void delete(Long historiaMedicamentoId) throws BusinessException;
	
	long count();
}
