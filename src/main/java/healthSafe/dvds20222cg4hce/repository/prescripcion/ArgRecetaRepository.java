package healthSafe.dvds20222cg4hce.repository.prescripcion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;

@Repository
public interface ArgRecetaRepository extends JpaRepository<ArgReceta, Long>{

}
