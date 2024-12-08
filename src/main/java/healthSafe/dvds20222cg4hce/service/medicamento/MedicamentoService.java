package healthSafe.dvds20222cg4hce.service.medicamento;

import java.util.List;

import org.springframework.data.domain.Page;
import healthSafe.dvds20222cg4hce.domain.medicamento.Medicamento;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface MedicamentoService {
	List<Medicamento> listMedicamentos();
	List<Medicamento> listMedicamentosLike(String likeNombre);
	Page<Medicamento> pageMedicamentos();
	Page<Medicamento> pageWithNumberMedicamentos(Integer pageNumber);
	Page<Medicamento> pageContainingMedicamentos(String nombre);
	Page<Medicamento> pageWithNumberContainingMedicamentos(Integer pageNumber, String nombre);
	
	Medicamento save(Medicamento medicamento) throws BusinessException;
	Medicamento update(Medicamento medicamento) throws BusinessException;
	
	Medicamento findById(Long id) throws BusinessException;
	
	long count();
}
