package healthSafe.dvds20222cg4hce.repository.profesional;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.domain.usuario.TipoMatricula;

@Repository
public interface ProfesionalRepository extends JpaRepository<Profesional, Long>{
	
	Page<Profesional> findAllByOrderByNombreAsc(Pageable pageable);
	List<Profesional> findAllByOrderByNombreAsc();
	List<Profesional> findByHistoriaMedicaId(Long historiaMedicaId);
	List<Profesional> findProfesionalesByInstitucionesSaludIdOrderByNombreAsc(Long centroId);
	public Boolean existsByIdAndActivoFalse(Long id);
	public Boolean existsByMatriculaAndTipoMatriculaAndHistoriaMedicaIdAndActivoTrue(Long matricula, TipoMatricula tipoMatricula, Long historiaMedicaId);
}
