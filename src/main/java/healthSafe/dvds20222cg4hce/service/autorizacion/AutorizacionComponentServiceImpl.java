package healthSafe.dvds20222cg4hce.service.autorizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Grupo;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Permiso;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Rol;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.autorizacion.AutorizacionComponentRepository;
import healthSafe.dvds20222cg4hce.repository.autorizacion.AutorizacionRepository;
import healthSafe.dvds20222cg4hce.repository.autorizacion.GrupoRepository;
import healthSafe.dvds20222cg4hce.repository.autorizacion.PermisoRepository;
import healthSafe.dvds20222cg4hce.repository.autorizacion.RolRepository;
import healthSafe.dvds20222cg4hce.utils.AutorizacionUtils;

@Service
public class AutorizacionComponentServiceImpl implements AutorizacionComponentService{
	
	private AutorizacionComponentRepository componentRepository;
	private AutorizacionRepository autorizacionRepository;
	private PermisoRepository permisoRepository;
	private GrupoRepository grupoRepository;
	private RolRepository rolRepository;
	
	@Autowired
	public AutorizacionComponentServiceImpl(final AutorizacionComponentRepository componentRepository,
			final AutorizacionRepository autorizacionRepository,
			final PermisoRepository permisoRepository,
			final GrupoRepository grupoRepository,
			final RolRepository rolRepository) {
		
		this.componentRepository = componentRepository;
		this.autorizacionRepository = autorizacionRepository;
		this.permisoRepository = permisoRepository;
		this.grupoRepository = grupoRepository;
		this.rolRepository = rolRepository;
	}
	
	
	@Override
	public List<AutorizacionComponent> listAutorizacionComponent() {
		
		return componentRepository.findAll();
	}

	@Override
	public List<Autorizacion> listAutorizacion() {
		
		return autorizacionRepository.findAll();
	}

	@Override
	public List<Permiso> listPermiso() {
		
		return permisoRepository.findAll();
	}

	@Override
	public List<Grupo> listGrupo() {
		
		return grupoRepository.findAll();
	}

	@Override
	public List<Rol> listRol() {
		
		return rolRepository.findAll();
	}

	@Override
	public Page<AutorizacionComponent> listAutorizacionComponent(Pageable pageable) {
		
		return componentRepository.findAll(pageable);
	}

	@Override
	public Page<Autorizacion> listAutorizacion(Pageable pageable) {
		
		return autorizacionRepository.findAll(pageable);
	}

	@Override
	public Page<Permiso> listPermiso(Pageable pageable) {
		
		return permisoRepository.findAll(pageable);
	}

	@Override
	public Page<Grupo> listGrupo(Pageable pageable) {
		
		return grupoRepository.findAll(pageable);
	}

	@Override
	public Page<Rol> listRol(Pageable pageable) {
		
		return rolRepository.findAll(pageable);
	}

	@Override
	public Autorizacion save(Autorizacion autorizacion) throws BusinessException {
		
		
		List<AutorizacionComponent> componentes = new ArrayList<AutorizacionComponent>();
		if(autorizacion.getComponentes() != null) {
			componentes = AutorizacionUtils.getListAutorizacionComponent(autorizacion.getComponentes());
		}
		
		autorizacion = Autorizacion.builder()
				.id(autorizacion.getId())
				.codigo(autorizacion.getCodigo())
				.descripcion(autorizacion.getDescripcion())
				.activo(autorizacion.getActivo())
				.componentes(componentes)
				.build();
		
		return autorizacionRepository.save(autorizacion);
	}

	@Override
	public Autorizacion update(Autorizacion autorizacion) throws BusinessException {
		
		if(autorizacion.getId() != null) return autorizacionRepository.save(autorizacion);
		throw new BusinessException("No se puede actualizar una autorizcion que no ha sido creado");
	}

	@Override
	public Permiso save(Permiso permiso) throws BusinessException {
		
		permiso = Permiso.builder()
				.id(permiso.getId())
				.codigo(permiso.getCodigo())
				.descripcion(permiso.getDescripcion())
				.activo(permiso.getActivo())
				.build();
		
		return permisoRepository.save(permiso);
	}

	@Override
	public Permiso update(Permiso permiso) throws BusinessException {
		
		if(permiso.getId() != null) return permisoRepository.save(permiso);
		throw new BusinessException("No se puede actualizar un permiso que no ha sido creado");
	}

	@Override
	public Grupo save(Grupo grupo) throws BusinessException {
		
		List<Rol> roles = new ArrayList<Rol>();
		if(grupo.getRoles() != null) {
			roles = AutorizacionUtils.getListRoles(grupo.getRoles());
		}
		
		List<Permiso> permisos = new ArrayList<Permiso>();
		if(grupo.getPermisos() != null) {
			permisos = AutorizacionUtils.getListPermisos(grupo.getPermisos());
		}
		
		grupo = Grupo.builder()
				.id(grupo.getId())
				.codigo(grupo.getCodigo())
				.descripcion(grupo.getDescripcion())
				.activo(grupo.getActivo())
				.roles(roles)
				.permisos(permisos)
				.build();
		
		return grupoRepository.save(grupo);
	}

	@Override
	public Grupo update(Grupo grupo) throws BusinessException {
		
		if(grupo.getId() != null) return grupoRepository.save(grupo);
		throw new BusinessException("No se puede actualizar un grupo que no ha sido creado");
	}

	@Override
	public Rol save(Rol rol) throws BusinessException {
		
		
		List<Permiso> permisos = new ArrayList<Permiso>();
		if(rol.getPermisos() != null) {
			permisos = AutorizacionUtils.getListPermisos(rol.getPermisos());
		}
		
		rol = Rol.builder().id(rol.getId()).codigo(rol.getCodigo())
				.descripcion(rol.getDescripcion()).activo(rol.getActivo())
				.permisos(permisos).build();
		
		return rolRepository.save(rol);
	}

	@Override
	public Rol update(Rol rol) throws BusinessException {
		
		if(rol.getId() != null) return rolRepository.save(rol);
		throw new BusinessException("No se puede actualizar un rol que no ha sido creado");
	}

	@Override
	public AutorizacionComponent findById(Long id) throws BusinessException {
		
		Optional<AutorizacionComponent> componentOptional = componentRepository.findById(id);
		if(componentOptional.isPresent()) return componentOptional.get();
		throw new BusinessException("No se pudo encontrar el componente de la autorizacion con el id: " + id);
	}
	
	@Override
	public AutorizacionComponent findByCodigo(String codigo) throws BusinessException {
		
		Optional<AutorizacionComponent> componentOptional = componentRepository.findByCodigo(codigo);
		if(componentOptional.isPresent()) return componentOptional.get();
		throw new BusinessException("No se pudo encontrar el componente de la autorizacion con el codigo: " + codigo);
	}
	
	@Override
	public Rol addPermisos(Rol rol, List<Long> permisosIds) throws BusinessException {
		
		for(Long permisoId: permisosIds) { rol.addPermiso((Permiso) findById(permisoId));}
		return update(rol);
	}
	
	@Override
	public Grupo addPermisos(Grupo grupo, List<Long> permisosIds) throws BusinessException {
		
		for(Long permisoId: permisosIds) { grupo.addPermiso((Permiso) findById(permisoId));}
		return update(grupo);
	}


	@Override
	public Grupo addRoles(Grupo grupo, List<Long> rolesIds) throws BusinessException {
		
		for(Long rolId: rolesIds) { grupo.addRol((Rol) findById(rolId));}
		return update(grupo);
	}
	
	@Override
	public Autorizacion addComponentes(Autorizacion autorizacion, List<Long> componentesIds) throws BusinessException {
		
		for(Long componenteId: componentesIds) {autorizacion.addComponente(findById(componenteId));}
		return update(autorizacion);
	}

	
	@Override
	public Rol removePermisos(Rol rol, List<Long> permisosIds) throws BusinessException {
		
		for(Long permisoId: permisosIds) { rol.removePermiso((Permiso) findById(permisoId));}
		return update(rol);
	}
	
	@Override
	public Grupo removePermisos(Grupo grupo, List<Long> permisosIds) throws BusinessException {
		
		for(Long permisoId: permisosIds) { grupo.removePermiso((Permiso) findById(permisoId));}
		return update(grupo);
	}


	@Override
	public Grupo removeRoles(Grupo grupo, List<Long> rolesIds) throws BusinessException {
		
		for(Long rolId: rolesIds) {grupo.removeRol((Rol) findById(rolId));}
		return update(grupo);
	}
	
	@Override
	public Autorizacion removeComponentes(Autorizacion autorizacion, List<Long> componentesIds)
			throws BusinessException {
		
		for(Long componentId: componentesIds) { autorizacion.removeComponente(findById(componentId));}
		return update(autorizacion);
	}
	
	@Override
	public AutorizacionComponent getAutorizacionUsuarioBase() throws BusinessException {
		
		return findByCodigo("USER");
	}
	
	@Override
	public AutorizacionComponent getRolGrupoFamiliarAdmin() throws BusinessException {
		
		return findByCodigo("GF_ADMIN");
	}

	@Override
	public AutorizacionComponent getRolGrupoFamiliarUsuario() throws BusinessException {
		
		return findByCodigo("GF_USER");
	}

	@Override
	public void delete(AutorizacionComponent component) {
		
		componentRepository.delete(component);
	}

	@Override
	public void delete(Long id) {
		
		componentRepository.deleteById(id);
	}

	@Override
	public long count() {
		
		return componentRepository.count();
	}

}
