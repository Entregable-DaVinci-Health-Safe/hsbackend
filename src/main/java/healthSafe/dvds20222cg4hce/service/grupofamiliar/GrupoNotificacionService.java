package healthSafe.dvds20222cg4hce.service.grupofamiliar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface GrupoNotificacionService {
	
	List<GrupoNotificacion> list();
	List<GrupoNotificacion> getNotificacionesFromGrupoFamiliarId(Long grupoFamiliarId);
	
	Page<GrupoNotificacion> list(Pageable pageable);
	
	GrupoNotificacion save(GrupoNotificacion grupoNotificacion) throws BusinessException;
	GrupoNotificacion update(GrupoNotificacion grupoNotificacion) throws BusinessException;
	
	GrupoNotificacion findById(Long id) throws BusinessException;
	Boolean existsByUsuarioId(Long usuarioId)throws BusinessException;
	
	void delete(GrupoNotificacion grupoNotificacion);
	void delete(Long id);
	
	long count();
}
