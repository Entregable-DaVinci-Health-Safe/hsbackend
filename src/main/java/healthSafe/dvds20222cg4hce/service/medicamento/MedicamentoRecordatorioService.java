package healthSafe.dvds20222cg4hce.service.medicamento;

import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoRecordatorio;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface MedicamentoRecordatorioService {
	MedicamentoRecordatorio save(MedicamentoRecordatorio recordatorio) throws BusinessException;
}
