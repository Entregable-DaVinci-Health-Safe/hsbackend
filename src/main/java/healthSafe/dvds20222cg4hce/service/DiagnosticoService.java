package healthSafe.dvds20222cg4hce.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface DiagnosticoService {
	
	public List<Diagnostico> list();
	public List<Diagnostico> listDiagnosticosLike(String nombre);
	public Page<Diagnostico> list(Pageable pageable);
	
	public Diagnostico findById(Long id) throws BusinessException;
}
