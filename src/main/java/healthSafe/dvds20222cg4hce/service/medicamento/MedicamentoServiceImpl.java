package healthSafe.dvds20222cg4hce.service.medicamento;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.medicamento.Medicamento;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.medicamento.MedicamentoRepository;

@Service
public class MedicamentoServiceImpl implements MedicamentoService{
	
	private MedicamentoRepository repository;
	private MedicamentoProductoService productoService;
	private final Pageable LIMIT_PAGE = PageRequest.ofSize(20);
	
	@Autowired
	public MedicamentoServiceImpl(final MedicamentoRepository repository,
			final MedicamentoProductoService productoService) {
		this.repository = repository;
		this.productoService = productoService;
	}
	
	@Override
	public List<Medicamento> listMedicamentos() {
		
		return repository.findAll();
	}
	
	@Override
	public List<Medicamento> listMedicamentosLike(String likeNombre) {
		
		return repository.findByNombreIgnoreCaseContainingOrderByNombreAsc(likeNombre);
	}

	@Override
	public Page<Medicamento> pageMedicamentos() {
		
		return repository.findAll(LIMIT_PAGE);
	}
	
	@Override
	public Page<Medicamento> pageWithNumberMedicamentos(Integer pageNumber) {
		
		return repository.findAll(LIMIT_PAGE.withPage(pageNumber));
	}
	
	@Override
	public Page<Medicamento> pageContainingMedicamentos(String nombre) {
		
		return repository.findByNombreIgnoreCaseContaining(LIMIT_PAGE, nombre);
	}
	
	@Override
	public Page<Medicamento> pageWithNumberContainingMedicamentos(Integer pageNumber, String nombre) {
		
		return repository.findByNombreIgnoreCaseContaining(LIMIT_PAGE.withPage(pageNumber), nombre);
	}
	
	@Override
	public Medicamento save(Medicamento medicamento) throws BusinessException {
		
		return null;
	}

	@Override
	public Medicamento update(Medicamento medicamento) throws BusinessException {
		
		return null;
	}

	@Override
	public Medicamento findById(Long id) throws BusinessException {
		
		Optional<Medicamento> medicamentoOptional = repository.findById(id);
		if(medicamentoOptional.isPresent()) return medicamentoOptional.get();
		throw new BusinessException("No se pudo encontrar el medicamento con el id: " + id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

}
