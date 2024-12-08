package healthSafe.dvds20222cg4hce.service.autorizacion;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Grupo;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Permiso;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Rol;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface AutorizacionComponentService {
	
	List<AutorizacionComponent> listAutorizacionComponent();
	List<Autorizacion> listAutorizacion();
	List<Permiso> listPermiso();
	List<Grupo> listGrupo();
	List<Rol> listRol();
	
	Page<AutorizacionComponent> listAutorizacionComponent(Pageable pageable);
	Page<Autorizacion> listAutorizacion(Pageable pageable);
	Page<Permiso> listPermiso(Pageable pageable);
	Page<Grupo> listGrupo(Pageable pageable);
	Page<Rol> listRol(Pageable pageable);
	
	Autorizacion save(Autorizacion autorizacion) throws BusinessException;
	Autorizacion update(Autorizacion autorizacion) throws BusinessException;
	
	Permiso save(Permiso permiso) throws BusinessException;
	Permiso update(Permiso permiso) throws BusinessException;
	
	Grupo save(Grupo grupo) throws BusinessException;
	Grupo update(Grupo grupo) throws BusinessException;
	
	Rol save(Rol rol) throws BusinessException;
	Rol update(Rol rol) throws BusinessException;
	
	AutorizacionComponent findById(Long id) throws BusinessException;
	AutorizacionComponent findByCodigo(String codigo) throws BusinessException;
	
	Rol addPermisos(Rol rol, List<Long> permisosIds) throws BusinessException;
	Grupo addPermisos(Grupo grupo, List<Long> permisosIds) throws BusinessException;
	Grupo addRoles(Grupo grupo, List<Long> permisosIds) throws BusinessException;
	Autorizacion addComponentes(Autorizacion autorizacion, List<Long> componentesIds)throws BusinessException;
	
	Rol removePermisos(Rol rol, List<Long> permisosIds) throws BusinessException;
	Grupo removePermisos(Grupo grupo, List<Long> permisosIds) throws BusinessException;
	Grupo removeRoles(Grupo grupo, List<Long> permisosIds) throws BusinessException;
	Autorizacion removeComponentes(Autorizacion autorizacion, List<Long> componentesIds)throws BusinessException;
	
	AutorizacionComponent getAutorizacionUsuarioBase()throws BusinessException;
	AutorizacionComponent getRolGrupoFamiliarAdmin()throws BusinessException;
	AutorizacionComponent getRolGrupoFamiliarUsuario()throws BusinessException;
	
	void delete(AutorizacionComponent component);
	void delete(Long id);
	
	long count();	
}
