package healthSafe.dvds20222cg4hce.repository.calendario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;

@Repository
public interface CalendarioRepository extends JpaRepository<Calendario, Long>{
	List<Calendario> findByHistoriaMedicaId(Long historiaMedicaId);
}
