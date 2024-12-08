package healthSafe.dvds20222cg4hce.repository.prescripcion;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;

@Repository
public interface PrescripcionRepository extends JpaRepository<Prescripcion, Long>{
	List<Prescripcion> findByVisitaMedica_HistoriaMedica_Id(Long historiaMedicaId);
}
