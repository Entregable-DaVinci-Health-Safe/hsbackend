package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.TipoArgReceta;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface RecetaService {
	
	List<Receta> listReceta();
	List<ArgReceta> listArgReceta();
	
	Page<Receta> listReceta(Pageable pageable);
	Page<ArgReceta> listArgReceta(Pageable pageable);
	
	ArgReceta save(ArgReceta receta) throws BusinessException;
	ArgReceta update(ArgReceta receta) throws BusinessException;
	
	Receta findById(Long id) throws BusinessException;
	
	void delete(ArgReceta receta);
	void delete(Long id);
	
	long count();

}
