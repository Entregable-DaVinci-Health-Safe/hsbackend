package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.GrupoFamiliarResponse;
import healthSafe.dvds20222cg4hce.controller.response.GrupoNotificacionResponse;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;

public final class GrupoFamiliarUtils {
	private GrupoFamiliarUtils() {}
	
	public static GrupoFamiliar getGrupoFamiliar(GrupoFamiliar grupoFamiliarRequest) {
		return GrupoFamiliar.builder()
				.id(grupoFamiliarRequest.getId())
				.codigo(grupoFamiliarRequest.getCodigo())
				.fechaCreado(grupoFamiliarRequest.getFechaCreado())
				.admins(UsuarioUtils.getListUsuarios(grupoFamiliarRequest.getAdmins()))
				.usuarios(UsuarioUtils.getListUsuarios(grupoFamiliarRequest.getUsuarios()))
				.historias(grupoFamiliarRequest.getHistorias())
				.build();
	}
	
	public static GrupoNotificacion getGrupoNotificacion(GrupoNotificacion grupoNotificacionRequest) {
		return GrupoNotificacion.builder()
				.id(grupoNotificacionRequest.getId())
				.aceptada(grupoNotificacionRequest.getAceptada())
				.fecha(grupoNotificacionRequest.getFecha())
				.build();
	}
	
	public static GrupoNotificacionResponse getGrupoNotificacionResponse(GrupoNotificacion grupoNotificacionRequest) {
		return GrupoNotificacionResponse.builder()
				.id(grupoNotificacionRequest.getId())
				.aceptada(grupoNotificacionRequest.getAceptada())
				.fecha(DateUtils.getStringDate(grupoNotificacionRequest.getFecha()))
				.usuarioNombre(grupoNotificacionRequest.getUsuario().getNombre())
				.usuarioMail(grupoNotificacionRequest.getUsuario().getMail())
				.build();
	}
	
	public static GrupoFamiliarResponse getGrupoFamiliarResponse(GrupoFamiliar grupoFamiliarRequest) {
		return GrupoFamiliarResponse.builder()
				.id(grupoFamiliarRequest.getId())
				.nombre(grupoFamiliarRequest.getNombre())
				.codigo(grupoFamiliarRequest.getCodigo())
				.notificaciones(getListGruposNotificacionesResponse(grupoFamiliarRequest.getGruposNotificaciones()))
				.fechaCreado(DateUtils.getStringDate(grupoFamiliarRequest.getFechaCreado()))
				.admins(UsuarioUtils.getListUsuariosResponse(grupoFamiliarRequest.getAdmins()))
				.usuarios(UsuarioUtils.getListUsuariosResponse(grupoFamiliarRequest.getUsuarios()))
				.historiasMedicasResponse(HistoriaMedicaUtils.getListHistoriaMedicaResponse(grupoFamiliarRequest.getHistorias()))
				.activo(grupoFamiliarRequest.getActivo())
				.build();
	}
	
	public static List<Long> getGruposFamiliaresIds(List<GrupoFamiliar> gruposFamiliaresRequest){
		List<Long> gruposFamiliaresIds = new ArrayList<Long>();
		gruposFamiliaresRequest.stream().forEach(gfm -> gruposFamiliaresIds.add(gfm.getId()));
		return gruposFamiliaresIds;
	}
	
	public static List<GrupoFamiliar> getListGruposFamiliares(List<GrupoFamiliar> gruposFamiliaresRequest) {
		List<GrupoFamiliar> gruposFamiliares = new ArrayList<GrupoFamiliar>();
		gruposFamiliaresRequest.stream().forEach(gfm -> gruposFamiliares.add(getGrupoFamiliar(gfm)));
		return gruposFamiliares;
	}
	
	public static List<GrupoFamiliarResponse> getListGruposFamiliaresResponse(List<GrupoFamiliar> gruposFamiliaresRequest) {
		List<GrupoFamiliarResponse> gruposFamiliaresResponse = new ArrayList<GrupoFamiliarResponse>();
		gruposFamiliaresRequest.stream().forEach(gfm -> gruposFamiliaresResponse.add(getGrupoFamiliarResponse(gfm)));
		return gruposFamiliaresResponse;
	}
	
	public static List<GrupoNotificacion> getListGruposNotificaciones(List<GrupoNotificacion> grupoNotificacionRequest) {
		List<GrupoNotificacion> gruposNotificaciones = new ArrayList<GrupoNotificacion>();
		grupoNotificacionRequest.stream().forEach(gnt -> gruposNotificaciones.add(getGrupoNotificacion(gnt)));
		return gruposNotificaciones;
	}
	
	public static List<GrupoNotificacionResponse> getListGruposNotificacionesResponse(List<GrupoNotificacion> grupoNotificacionRequest){
		List<GrupoNotificacionResponse> gruposNotificacionesResponse = new ArrayList<GrupoNotificacionResponse>();
		grupoNotificacionRequest.stream().forEach(gnt -> 
			gruposNotificacionesResponse.add(getGrupoNotificacionResponse(gnt)));
		return gruposNotificacionesResponse;
	}
}
