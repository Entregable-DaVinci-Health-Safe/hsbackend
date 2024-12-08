package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Adjunto;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface AdjuntoService {
	
	List<Adjunto> list();
	Page<Adjunto> list(Pageable pageable);
	
	Adjunto save(Adjunto adjunto) throws BusinessException;
	
	Adjunto findById(Long id) throws BusinessException;

	void delete(Long id);
	void delete(Adjunto adjunto);
	
	long count();
}
