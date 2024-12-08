package healthSafe.dvds20222cg4hce.service.medicamento;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoProducto;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.medicamento.MedicamentoProductoRepository;

@Service
public class MedicamentoProductoServiceImpl implements MedicamentoProductoService{
	
	private MedicamentoProductoRepository repository;
	
	@Autowired
	public MedicamentoProductoServiceImpl(final MedicamentoProductoRepository repository){
		this.repository = repository;
	}
	
	@Override
	public MedicamentoProducto findById(Long id) throws BusinessException {
		
		Optional<MedicamentoProducto> productoOptional = repository.findById(id);
		if(productoOptional.isPresent()) return productoOptional.get();
		throw new BusinessException("No se pudo encontrar el producto con el id: " + id);
	}
	
	@Override
	public void addMedicamento(Long id, HistoriaMedicamento historiaMedicamento) throws BusinessException {
		
		MedicamentoProducto medicamentoProducto = findById(id);
		medicamentoProducto.addHistoriaMedicamento(historiaMedicamento);
	}

}
