package healthSafe.dvds20222cg4hce.repository.calendario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;

@Repository
public interface CalendarioAdultoRepository extends JpaRepository<CalendarioAdulto, Long>{

}
