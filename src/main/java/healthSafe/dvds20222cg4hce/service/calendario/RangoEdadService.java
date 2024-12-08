package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.TipoCalendario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface RangoEdadService {
	List<RangoEdad> listRangoEdades();
	Page<RangoEdad> listRangoEdades(Pageable pageable);

	RangoEdad save(RangoEdad rangoEdad) throws BusinessException;
	RangoEdad update(RangoEdad rangoEdad) throws BusinessException;
	
	RangoEdad findById(Long id) throws BusinessException;
	List<RangoEdad> findAllByTipoCalendario(String tipoCalendario) throws BusinessException;
	
	RangoEdad addTipoCalendario(Long id, TipoCalendario tipoCalendario) throws BusinessException;
	
	long count();
}
