package healthSafe.dvds20222cg4hce.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;


@Repository
public interface InstitucionSaludRepository extends JpaRepository<InstitucionSalud, Long>{
	
	Page<InstitucionSalud> findAllByOrderByNombreAsc(Pageable pageable);
	List<InstitucionSalud> findAllByOrderByNombreAsc();
	List<InstitucionSalud> findInstitucionesSaludByProfesionalesIdOrderByNombreAsc(Long profesionalId);
	public List<InstitucionSalud> findByNombreAndHistoriaMedica_IdAndDireccion_Direccion(String nombre, Long historiaMedicaId, String direccion);
	public List<InstitucionSalud> findByHistoriaMedicaId(Long historiaMedicaId);
}
