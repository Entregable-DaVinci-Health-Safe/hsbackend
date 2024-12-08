package healthSafe.dvds20222cg4hce.service.medicamento;

import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoRecordatorio;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.medicamento.MedicamentoRecordatorioRepository;

@Service
public class MedicamentoRecordatorioServiceImpl implements MedicamentoRecordatorioService{
	
	private MedicamentoRecordatorioRepository repository;
	private HistoriaMedicamentoService historiaMedicamentoService;
	
	public MedicamentoRecordatorioServiceImpl(final MedicamentoRecordatorioRepository repository,
											final HistoriaMedicamentoService historiaMedicamentoService) {
		this.repository = repository;
		this.historiaMedicamentoService = historiaMedicamentoService;
	}
	
	@Override
	public MedicamentoRecordatorio save(MedicamentoRecordatorio recordatorio) throws BusinessException {
		
		if(recordatorio.getHistoriaMedicamento().getId() == null) {
			throw new BusinessException("El recordatorio tiene que estar asociado a un medicamento");
		}
		HistoriaMedicamento medicamento = getHistoriaMedicamentoById(recordatorio.getHistoriaMedicamento().getId());
		recordatorio = MedicamentoRecordatorio.builder()
						.dosis(recordatorio.getDosis())
						.frecuencia(recordatorio.getFrecuencia())
						.esCronico(recordatorio.getEsCronico())
						.fechaInicio(recordatorio.getFechaInicio())
						.fechaFinal(recordatorio.getFechaFinal())
						.reposicion(recordatorio.getReposicion())
						.tipoFrecuencia(recordatorio.getTipoFrecuencia())
						.googleId(recordatorio.getGoogleId())
						.build();
		recordatorio = repository.save(recordatorio);
		medicamento.setRecordatorio(recordatorio);
		historiaMedicamentoService.update(medicamento);
		return recordatorio;
	}
	
	private HistoriaMedicamento getHistoriaMedicamentoById(Long id) throws BusinessException{
		return historiaMedicamentoService.findById(id);
	}

}
