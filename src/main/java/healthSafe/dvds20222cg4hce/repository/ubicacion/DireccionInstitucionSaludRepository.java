package healthSafe.dvds20222cg4hce.repository.ubicacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;

@Repository
public interface DireccionInstitucionSaludRepository extends JpaRepository<DireccionInstitucionSalud, Long>{

}
