package healthSafe.dvds20222cg4hce.repository.calendario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;

@Repository
public interface VacunaRepository extends JpaRepository<Vacuna, Long>{
	List<Vacuna> findVacunasByTipoCalendariosId(String id);
	List<Vacuna> findVacunasByRangoEdadesId(Long id);
}
