package healthSafe.dvds20222cg4hce.repository.calendario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;

@Repository
public interface CalendarioEdadVacunaLinkRepository extends JpaRepository<CalendarioEdadVacunaLink, Long>{
	Optional<CalendarioEdadVacunaLink> findByCalendarioIdAndRangoEdadIdAndVacunaUsuarioId(
			Long calendarioId, Long rangoEdadId, Long vacunaUsuarioId);
}
