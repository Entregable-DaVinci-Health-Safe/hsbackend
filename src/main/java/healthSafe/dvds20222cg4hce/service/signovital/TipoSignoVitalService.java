package healthSafe.dvds20222cg4hce.service.signovital;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.signovital.TipoSignoVital;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface TipoSignoVitalService {
	
	List<TipoSignoVital> list();
	Page<TipoSignoVital> list(Pageable pageable);
	
	TipoSignoVital findById(Long id) throws BusinessException;
}
