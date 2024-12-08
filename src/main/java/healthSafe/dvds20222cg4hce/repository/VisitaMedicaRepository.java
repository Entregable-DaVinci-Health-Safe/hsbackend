package healthSafe.dvds20222cg4hce.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;

@Repository
public interface VisitaMedicaRepository extends JpaRepository<VisitaMedica, Long>{
	public List<VisitaMedica> findByHistoriaMedicaId(Long historiaMedicaId);
}
