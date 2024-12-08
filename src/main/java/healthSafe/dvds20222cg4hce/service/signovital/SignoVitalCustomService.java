package healthSafe.dvds20222cg4hce.service.signovital;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface SignoVitalCustomService {
	
	List<SignoVitalCustom> list();
	List<SignoVitalCustom> listSignoVitalCustomByHistoriamedica(Long id);
	
	Page<SignoVitalCustom> list(Pageable pageable);
	
	SignoVitalCustom save(SignoVitalCustom signoVitalCustom) throws BusinessException;
	SignoVitalCustom update(SignoVitalCustom signoVitalCustom) throws BusinessException;
	
	SignoVitalCustom findById(Long id) throws BusinessException;
	
	void delete(SignoVitalCustom signoVitalCustom);
	void delete(Long id);
}
