package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.calendario.VacunaUsuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface VacunaUsuarioService {
	
	List<VacunaUsuario> listVacunasUsuarios();
	Page<VacunaUsuario> listVacunasUsuario(Pageable pageable);
	
	VacunaUsuario save(VacunaUsuario vacunaUsuario) throws BusinessException;
	VacunaUsuario update(VacunaUsuario vacunaUsuario) throws BusinessException;
	
	VacunaUsuario findById(Long id) throws BusinessException;

	long count();
}
