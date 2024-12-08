package healthSafe.dvds20222cg4hce.service.profesional;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface EspecialidadService {
	List<Especialidad> list();
	Page<Especialidad> list(Pageable pageable);
	
	Especialidad save(Especialidad especialidad) throws BusinessException;
	Especialidad findById(Long id) throws BusinessException;
	List<Especialidad> findEspecialidadesByProfesionalId(Long profesionalId) throws BusinessException;
	
	long count();
}
