package healthSafe.dvds20222cg4hce.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;

@Repository
public interface DireccionRepository extends JpaRepository<Direccion, Long>{

}
