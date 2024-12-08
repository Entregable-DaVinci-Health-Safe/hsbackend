package healthSafe.dvds20222cg4hce.repository.signovital;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;

@Repository
public interface SignoVitalCustomRepository extends JpaRepository<SignoVitalCustom, Long>{
	List<SignoVitalCustom> findByHistoriaMedicaId(Long historiaMedicaId);
}
