package healthSafe.dvds20222cg4hce.service.medicamento;

import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoProducto;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface MedicamentoProductoService {
	
	MedicamentoProducto findById(Long id) throws BusinessException;
	
	void addMedicamento(Long id, HistoriaMedicamento historiaMedicamento) throws BusinessException;
}
