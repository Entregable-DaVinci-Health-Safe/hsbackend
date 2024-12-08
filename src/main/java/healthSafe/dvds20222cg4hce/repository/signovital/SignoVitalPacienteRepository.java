package healthSafe.dvds20222cg4hce.repository.signovital;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;

@Repository
public interface SignoVitalPacienteRepository extends JpaRepository<SignoVitalPaciente, Long>{

}
