package healthSafe.dvds20222cg4hce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Adjunto;

@Repository
public interface AdjuntoRepository extends JpaRepository<Adjunto, Long>{

}
