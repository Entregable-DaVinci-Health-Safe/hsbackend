package healthSafe.dvds20222cg4hce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;

@Repository
public interface GrupoNotificacionRepository extends JpaRepository<GrupoNotificacion, Long>{
	List<GrupoNotificacion> findByGrupoFamiliarId(Long grupoFamiliarId);
	Boolean existsByUsuarioId(Long usuarioId);
}
