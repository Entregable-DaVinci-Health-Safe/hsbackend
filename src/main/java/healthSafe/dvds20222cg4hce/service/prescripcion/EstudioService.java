package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.TipoArgEstudio;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface EstudioService {
	
	List<Estudio> listEstudio();
	List<ArgEstudio> listArgEstudio();
	
	Page<Estudio> listEstudio(Pageable pageable);
	Page<ArgEstudio> listArgEstudio(Pageable pageable);
	
	ArgEstudio save(ArgEstudio estudio) throws BusinessException;
	ArgEstudio update(ArgEstudio estudio) throws BusinessException;
	
	Estudio findById(Long id) throws BusinessException;
	
	void delete(Estudio estudio);
	void delete(Long id);
	
	long count();
	
}
