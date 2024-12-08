package healthSafe.dvds20222cg4hce.service.grupofamiliar;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface GrupoFamiliarService {
	
	List<GrupoFamiliar> list();
	Page<GrupoFamiliar> list(Pageable pageable);
	
	GrupoFamiliar save(GrupoFamiliar grupoFamiliar) throws BusinessException;
	
	GrupoFamiliar update(GrupoFamiliar grupoFamiliar) throws BusinessException;

	GrupoFamiliar findById(Long id) throws BusinessException;
	GrupoFamiliar findByCodigo(String codigo) throws BusinessException;
	List<GrupoFamiliar> findByHistoriaMedicaId(Long historiaMedicaId) throws BusinessException;
	
	GrupoFamiliar addHistoriaMedica(Long usuarioId, GrupoFamiliar grupoFamiliar) throws BusinessException;
	GrupoFamiliar removeHistoriaMedica(Long usuarioId, GrupoFamiliar grupoFamiliar)throws BusinessException;;
	
	GrupoFamiliar addAdmin(String usuarioMail, GrupoFamiliar grupoFamiliar)throws BusinessException;
	GrupoFamiliar removeAdmin(String usuarioMail, GrupoFamiliar grupoFamiliar)throws BusinessException;
	
	GrupoFamiliar addUsuario(String usuarioMail, GrupoFamiliar grupoFamiliar)throws BusinessException;
	GrupoFamiliar removeUsuario(String usuarioMail, GrupoFamiliar grupoFamiliar)throws BusinessException;
	
	String compartirGrupoFamiliar(String codigo, String mail) throws Exception;
	
	Boolean existsGrupoByName(String nombre) throws BusinessException;
	Boolean esAdminEnGrupo(Usuario usuario, GrupoFamiliar grupoFamiliar);
	
	void delete(GrupoFamiliar grupoFamiliar);
	void delete(Long id);
	
	void activarGrupoFamiliar(GrupoFamiliar grupoFamiliar)  throws BusinessException;
	void desactivarGrupoFamiliar(GrupoFamiliar grupoFamiliar)  throws BusinessException;
	
	void sendNotificacion(GrupoNotificacion grupoNotificacion) throws BusinessException;
	GrupoFamiliar aceptarNotificacion(Long notificacionId, GrupoFamiliar grupoFamiliar) throws BusinessException;
	void deleteNotificacion(Long notificacionId) throws BusinessException;
	
	long count();
	
	Usuario getUsuarioByMail(String mail) throws BusinessException;
}
