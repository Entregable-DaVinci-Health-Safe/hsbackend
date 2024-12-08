package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionComponentResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.GrupoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.PermisoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.RolResponse;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Grupo;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Permiso;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Rol;

public final class AutorizacionUtils {
	private AutorizacionUtils() {}
	
	public static Permiso getPermiso(Permiso permisoRequest) {
		return Permiso.builder()
				.id(permisoRequest.getId())
				.codigo(permisoRequest.getCodigo())
				.descripcion(permisoRequest.getDescripcion())
				.activo(permisoRequest.getActivo())
				.build();
	}
	
	public static Rol getRol(Rol rolRequest) {
		return Rol.builder()
				.id(rolRequest.getId())
				.codigo(rolRequest.getCodigo())
				.descripcion(rolRequest.getDescripcion())
				.activo(rolRequest.getActivo())
				.permisos(getListPermisos(rolRequest.getPermisos()))
				.build();
	}
	
	public static Grupo getGrupo(Grupo grupoRequest) {
		return Grupo.builder()
				.id(grupoRequest.getId())
				.codigo(grupoRequest.getCodigo())
				.descripcion(grupoRequest.getDescripcion())
				.activo(grupoRequest.getActivo())
				.permisos(getListPermisos(grupoRequest.getPermisos()))
				.roles(getListRoles(grupoRequest.getRoles()))
				.build();
	}
	
	public static Autorizacion getAutorizacion(Autorizacion autorizacionRequest) {
		return Autorizacion.builder()
				.id(autorizacionRequest.getId())
				.codigo(autorizacionRequest.getCodigo())
				.descripcion(autorizacionRequest.getDescripcion())
				.activo(autorizacionRequest.getActivo())
				.componentes(null)
				.build();
	}
	
	public static AutorizacionComponent getAutorizacionComponent(
			AutorizacionComponent autorizacionComponent) {
		if(autorizacionComponent instanceof Permiso) {
			return getPermiso((Permiso)autorizacionComponent);
		}else if(autorizacionComponent instanceof Rol){
			return getRol((Rol)autorizacionComponent);
		}else if(autorizacionComponent instanceof Grupo) {
			return getGrupo((Grupo) autorizacionComponent);
		}else if(autorizacionComponent instanceof Autorizacion) {
			return getAutorizacion((Autorizacion) autorizacionComponent);
		}
		
		return null;
	}
	
	public static PermisoResponse getPermisoResponse(Permiso permisoRequest) {
		return PermisoResponse.builder()
				.id(permisoRequest.getId())
				.codigo(permisoRequest.getCodigo())
				.descripcion(permisoRequest.getDescripcion())
				.activo(permisoRequest.getActivo())
				.build();
	}
	
	public static RolResponse getRolResponse(Rol rolRequest) {
		return RolResponse.builder()
				.id(rolRequest.getId())
				.codigo(rolRequest.getCodigo())
				.descripcion(rolRequest.getDescripcion())
				.activo(rolRequest.getActivo())
				.permisos(getListPermisosResponse(rolRequest.getPermisos()))
				.build();
	}
	
	public static GrupoResponse getGrupoResponse(Grupo grupoRequest) {
		return GrupoResponse.builder()
				.id(grupoRequest.getId())
				.codigo(grupoRequest.getCodigo())
				.descripcion(grupoRequest.getDescripcion())
				.activo(grupoRequest.getActivo())
				.permisos(getListPermisosResponse(grupoRequest.getPermisos()))
				.roles(getListRolesResponse(grupoRequest.getRoles()))
				.build();
	}
	
	public static AutorizacionResponse getAutorizacionResponse(
			Autorizacion autorizacionRequest) {
		return AutorizacionResponse.builder()
				.id(autorizacionRequest.getId())
				.codigo(autorizacionRequest.getCodigo())
				.descripcion(autorizacionRequest.getDescripcion())
				.activo(autorizacionRequest.getActivo())
				.componentes(getListAutorizacionComponentResponse(autorizacionRequest.getComponentes()))
				.build();
	}
	
	public static AutorizacionComponentResponse getAutorizacionComponentResponse(
			AutorizacionComponent autorizacionComponent) {
		if(autorizacionComponent instanceof Permiso) {
			return getPermisoResponse((Permiso)autorizacionComponent);
		}else if(autorizacionComponent instanceof Rol){
			return getRolResponse((Rol)autorizacionComponent);
		}else if(autorizacionComponent instanceof Grupo) {
			return getGrupoResponse((Grupo) autorizacionComponent);
		}else if(autorizacionComponent instanceof Autorizacion) {
			return getAutorizacionResponse((Autorizacion) autorizacionComponent);
		}
		
		return null;
	}
	
	public static List<Permiso> getListPermisos(
			List<Permiso> permisosRequest){
		List<Permiso> permisos = new ArrayList<Permiso>();
		permisosRequest.stream().forEach(prm -> permisos.add(getPermiso(prm)));
		return permisos;
	}
	
	public static List<Rol> getListRoles(List<Rol> rolesRequest){
		List<Rol> roles = new ArrayList<Rol>();
		rolesRequest.stream().forEach(rol -> roles.add(getRol(rol)));
		return roles;
	}
	
	public static List<Grupo> getListGrupos(List<Grupo> gruposRequest){
		List<Grupo> grupos = new ArrayList<Grupo>();
		gruposRequest.stream().forEach(grp -> grupos.add(getGrupo(grp)));
		return grupos;
	}
	
	public static List<Autorizacion> getListAutorizacio(
			List<Autorizacion> autorizacionesRequest){
		List<Autorizacion> autorizaciones = new ArrayList<Autorizacion>();
		autorizacionesRequest.stream().forEach(aut -> 
			autorizaciones.add(getAutorizacion(aut)));
		return autorizaciones;
	}
	
	public static List<AutorizacionComponent> getListAutorizacionComponent(
			List<AutorizacionComponent> componentesRequest){
		List<AutorizacionComponent> componentes = new ArrayList<AutorizacionComponent>();
		componentesRequest.stream().forEach(acp -> 
			componentes.add(getAutorizacionComponent(acp)));
		return componentes;
	}
	
	public static List<PermisoResponse> getListPermisosResponse(
			List<Permiso> permisosRequest){
		List<PermisoResponse> permisosResponse = new ArrayList<PermisoResponse>();
		permisosRequest.stream().forEach(prm -> 
			permisosResponse.add(getPermisoResponse(prm)));
		return permisosResponse;
	}
	
	public static List<RolResponse> getListRolesResponse(List<Rol> rolesRequest){
		List<RolResponse> rolesResponse = new ArrayList<RolResponse>();
		rolesRequest.stream().forEach(rol -> rolesResponse.add(getRolResponse(rol)));
		return rolesResponse;
	}
	
	public static List<GrupoResponse> getListGruposResponse(List<Grupo> gruposRequest){
		List<GrupoResponse> gruposReponse = new ArrayList<GrupoResponse>();
		gruposRequest.stream().forEach(grp -> gruposReponse.add(getGrupoResponse(grp)));
		return gruposReponse;
	}
	
	public static List<AutorizacionResponse> getListAutorizacionResponse(
			List<Autorizacion> autorizacionesRequest){
		List<AutorizacionResponse> autorizacionesResponse = new ArrayList<AutorizacionResponse>();
		autorizacionesRequest.stream().forEach(aut -> 
			autorizacionesResponse.add(getAutorizacionResponse(aut)));
		return autorizacionesResponse;
	}
	
	public static List<AutorizacionComponentResponse> getListAutorizacionComponentResponse(
			List<AutorizacionComponent> componentesRequest){
		List<AutorizacionComponentResponse> componentesResponse = new ArrayList<AutorizacionComponentResponse>();
		if(componentesRequest != null) {
			componentesRequest.stream().forEach(acp -> 
			componentesResponse.add(getAutorizacionComponentResponse(acp)));
		}
		
		return componentesResponse;
	}
}
