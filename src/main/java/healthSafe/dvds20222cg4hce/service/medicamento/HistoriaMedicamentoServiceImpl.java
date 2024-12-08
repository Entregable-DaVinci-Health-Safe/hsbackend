package healthSafe.dvds20222cg4hce.service.medicamento;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoProducto;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.medicamento.HistoriaMedicamentoRepository;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;

@Service
public class HistoriaMedicamentoServiceImpl implements HistoriaMedicamentoService{
	
	private HistoriaMedicamentoRepository repository;
	private HistoriaMedicaService historiaMedicaService;
	private MedicamentoProductoService productoService;
	
	@Autowired
	public HistoriaMedicamentoServiceImpl(final HistoriaMedicamentoRepository repository,
			final HistoriaMedicaService historiaMedicaService,
			final MedicamentoProductoService productoService) {
		this.repository = repository;
		this.historiaMedicaService = historiaMedicaService;
		this.productoService = productoService;
	}

	@Override
	public HistoriaMedicamento save(HistoriaMedicamento historiaMedicamento) throws BusinessException {
		
		HistoriaMedica historia = null;
		if(historiaMedicamento.getHistoriaMedica().getId() != null) {
			historia = getHistoriaMedica(historiaMedicamento.getHistoriaMedica().getId());
		}else {
			throw new BusinessException("La Historia Medica es obligatoria");
		}
		
		MedicamentoProducto producto = null;
		if(historiaMedicamento.getMedicamento().getId() != null) {
			producto = getMedicamentoProducto(historiaMedicamento.getMedicamento().getId() );
		}else {
			throw new BusinessException("El producto es obligatorio");
		}
		
		historiaMedicamento = HistoriaMedicamento.builder()
				.historiaMedica(historia)
				.medicamento(producto)
				.cantidad(historiaMedicamento.getCantidad())
				.comentarios(historiaMedicamento.getComentarios())
				.presentacion(historiaMedicamento.getPresentacion())
				.recordatorio(null)
				.build();
		
		historiaMedicamento = repository.save(historiaMedicamento);
		
		historiaMedicaService.addMedicamento(historia.getId(), historiaMedicamento);
		productoService.addMedicamento(producto.getId(), historiaMedicamento);
		
		return historiaMedicamento;
	}

	@Override
	public HistoriaMedicamento update(HistoriaMedicamento historiaMedicamento) throws BusinessException {
		
		if(historiaMedicamento.getId() != null) return repository.save(historiaMedicamento);
		throw new BusinessException("No se puede actualizar un medicamento que no ha sido creado");
	}
	
	@Override
	public HistoriaMedicamento findById(Long id) throws BusinessException {
		
		Optional<HistoriaMedicamento> historiaMedicamentoOptional = repository.findById(id);
		if(historiaMedicamentoOptional.isPresent()) return historiaMedicamentoOptional.get();
		throw new BusinessException("No se pudo conseguir el medicamento con el id: " + id); 
	}
	
	@Override
	public List<HistoriaMedicamento> getHistoriaMedicamentosByHistoriaMedica(Long historiaMedicaId) throws BusinessException {
		
		List<HistoriaMedicamento> historiaMedicamentos = repository.findByHistoriaMedicaId(historiaMedicaId);
		if(!historiaMedicamentos.isEmpty()) return historiaMedicamentos; 
		throw new BusinessException("No hay medicamentos asociadas a la historia medica"); 
	}

	@Override
	public void delete(HistoriaMedicamento historiaMedicamento) throws BusinessException {
		
		repository.delete(historiaMedicamento);
	}

	@Override
	public void delete(Long historiaMedicamentoId) throws BusinessException {
		
		repository.deleteById(historiaMedicamentoId);
	}

	@Override
	public long count() {
		
		return repository.count();
	}
	
	private HistoriaMedica getHistoriaMedica(Long id) throws BusinessException{
		return historiaMedicaService.findById(id);
	}
	
	private MedicamentoProducto getMedicamentoProducto(Long id) throws BusinessException{
		return productoService.findById(id);
	}
	
	/*
	private Long calculateFechaFinalOfConsumo(HistoriaMedicamento historiaMedicamento) throws BusinessException{
		
		Integer addTime = historiaMedicamento.getCantidad() / historiaMedicamento.getIngerir();
		
		if(historiaMedicamento.getFrecuencia().equals(TipoFrecuencia.DIARIO)) {
			return DateUtils.addDaysToTimestamp(addTime, historiaMedicamento.getFechaInicio());
		}else if(historiaMedicamento.getFrecuencia().equals(TipoFrecuencia.SEMANAL)) {
			return DateUtils.addWeeksToTimestamp(addTime, historiaMedicamento.getFechaInicio());
		}else if(historiaMedicamento.getFrecuencia().equals(TipoFrecuencia.MENSUAL)) {
			return DateUtils.addMonthsToTimestamp(addTime, historiaMedicamento.getFechaInicio());
		}else if(historiaMedicamento.getFrecuencia().equals(TipoFrecuencia.ANUAL)) {
			return DateUtils.addYearsToTimestamp(addTime, historiaMedicamento.getFechaInicio());
		}
		throw new BusinessException("ERROR");
	}*/
	
}
