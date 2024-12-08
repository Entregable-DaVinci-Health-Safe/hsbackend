package healthSafe.dvds20222cg4hce.repository.profesional;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;

@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long>{
	
	Page<Especialidad> findAllByOrderByNombreAsc(Pageable pageable);
	List<Especialidad> findAllByOrderByNombreAsc();
	List<Especialidad> findEspecialidadesByProfesionalesIdOrderByNombreAsc(Long profesionalId);
}
