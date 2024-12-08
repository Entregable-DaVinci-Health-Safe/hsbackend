package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEmbarazada;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioInfante;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioPersonalSalud;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface CalendarioService {
	
	List<Calendario> listCalendario();
	List<CalendarioInfante> listInfante();
	List<CalendarioAdulto> listAdulto();
	List<CalendarioEmbarazada> listEmbarazada();
	
	Page<Calendario> listCalendario(Pageable pageable);
	Page<CalendarioInfante> listInfante(Pageable pageable);
	Page<CalendarioAdulto> listAdulto(Pageable pageable);
	Page<CalendarioEmbarazada> listEmbarazada(Pageable pageable);
	
	HistoriaMedica createCalendarios(HistoriaMedica historiaMedica) throws BusinessException;
	HistoriaMedica createCalendarioPersonalSalud(HistoriaMedica historiaMedica) throws BusinessException;
	
	CalendarioPersonalSalud save(CalendarioPersonalSalud personalSalud) throws BusinessException;
	CalendarioPersonalSalud update(CalendarioPersonalSalud personalSalud) throws BusinessException;
	
	CalendarioInfante save(CalendarioInfante infante)throws BusinessException;
	CalendarioInfante update(CalendarioInfante infante)throws BusinessException;

	CalendarioAdulto save(CalendarioAdulto adulto)throws BusinessException;
	CalendarioAdulto update(CalendarioAdulto adulto)throws BusinessException;
	
	CalendarioEmbarazada save(CalendarioEmbarazada embarazada)throws BusinessException;
	CalendarioEmbarazada update(CalendarioEmbarazada embarazada)throws BusinessException;
	
	Calendario findById(Long id) throws BusinessException;
	List<Calendario> findByHistoriaMedica(Long historiaId) throws BusinessException;
	
	Calendario addVacuna(Calendario calendario, CalendarioEdadVacunaLink calendarioEdadVacuna)  throws BusinessException;
	Calendario removeVacuna(Calendario calendario, Long rangoEdadId, Long vacuna)  throws BusinessException;

	void delete(Calendario calendario);
	void delete(Long id);
	
	long count();
}
