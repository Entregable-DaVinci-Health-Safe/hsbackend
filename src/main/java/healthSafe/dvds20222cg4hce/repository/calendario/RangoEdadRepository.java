package healthSafe.dvds20222cg4hce.repository.calendario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;

@Repository
public interface RangoEdadRepository extends JpaRepository<RangoEdad, Long>{
	List<RangoEdad> findRangoEdadesByTipoCalendariosId(String id);
}
