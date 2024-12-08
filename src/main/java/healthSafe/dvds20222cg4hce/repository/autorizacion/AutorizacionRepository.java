package healthSafe.dvds20222cg4hce.repository.autorizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;

@Repository
public interface AutorizacionRepository extends JpaRepository<Autorizacion, Long>{

}
