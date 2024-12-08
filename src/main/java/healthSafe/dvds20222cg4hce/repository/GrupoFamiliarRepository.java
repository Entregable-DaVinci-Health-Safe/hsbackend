package healthSafe.dvds20222cg4hce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;

@Repository
public interface GrupoFamiliarRepository extends JpaRepository<GrupoFamiliar, Long>{
	Optional<GrupoFamiliar> findByCodigo(String codigo);
	
	List<GrupoFamiliar> findByAdmins(Usuario usuario);
	List<GrupoFamiliar> findByUsuarios(Usuario usuario);
	List<GrupoFamiliar> findByHistoriasId(Long historiaMedicaId);
	
	Boolean existsByNombre(String nombre);
}
