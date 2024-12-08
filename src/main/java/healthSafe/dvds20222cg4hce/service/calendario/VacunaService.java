package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface VacunaService {
	List<Vacuna> listVacunas();
	Page<Vacuna> listVacunas(Pageable pageable);
	
	Vacuna save(Vacuna vacuna) throws BusinessException;
	Vacuna update(Vacuna vacuna) throws BusinessException;
	
	Vacuna findById(Long id) throws BusinessException;
	List<Vacuna> findAllByTipoCalendario(String tipoCalendario)throws BusinessException;
	List<Vacuna> findAllByRangoEdad(Long rangoEdadId)throws BusinessException;
	Boolean thisVacunaIsForThisCalendario(Vacuna vacuna, String tipoCalendario)throws BusinessException;
	
	long count();
}
