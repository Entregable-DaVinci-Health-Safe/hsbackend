package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface CalendarioEdadVacunaLinkService {
	
	List<CalendarioEdadVacunaLink> listAll();
	Page<CalendarioEdadVacunaLink> list(Pageable pageable);
	
	CalendarioEdadVacunaLink save(CalendarioEdadVacunaLink link)throws BusinessException;
	CalendarioEdadVacunaLink update(CalendarioEdadVacunaLink link) throws BusinessException;
	
	CalendarioEdadVacunaLink findById(Long linkId) throws BusinessException;
	CalendarioEdadVacunaLink findByCalendarioIdAndRangoEdadIdAndVacunaUsuarioId(
			Long calendarioId, Long rangoEdadId, Long vacunaUsuarioId) throws BusinessException;;
	
	void delete(CalendarioEdadVacunaLink link) throws BusinessException;
	void delete(Long linkId)throws BusinessException;
	
	long count();
}
