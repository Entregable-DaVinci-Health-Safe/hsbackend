package healthSafe.dvds20222cg4hce.repository.autorizacion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;

@Repository
public interface AutorizacionComponentRepository extends JpaRepository<AutorizacionComponent, Long>{
	Optional<AutorizacionComponent> findByCodigo(String codigo);
}
