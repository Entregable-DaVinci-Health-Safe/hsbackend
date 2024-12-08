package healthSafe.dvds20222cg4hce.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;

@Repository
public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long>{
	List<Diagnostico> findByNombreIgnoreCaseContainingOrderByNombreAsc(String nombre);
	List<Diagnostico> findAllByOrderByNombreAsc();
	Page<Diagnostico> findAllByOrderByNombreAsc(Pageable pageable);
}
