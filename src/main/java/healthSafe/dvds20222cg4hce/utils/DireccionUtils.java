package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;

public final class DireccionUtils {
	private DireccionUtils() {}
	
	public static DireccionUsuario getDireccionUsuario(DireccionUsuario direccionUsuarioRequest) {
		return DireccionUsuario.builder()
				.id(direccionUsuarioRequest.getId())
				.provincia(direccionUsuarioRequest.getProvincia())
				.localidad(direccionUsuarioRequest.getLocalidad())
				.barrio(direccionUsuarioRequest.getBarrio())
				.departamento(direccionUsuarioRequest.getDepartamento())
				.direccion(direccionUsuarioRequest.getDireccion())
				.piso(direccionUsuarioRequest.getPiso())
				.referencia(direccionUsuarioRequest.getReferencia())
				.build();
	}
	
	public static DireccionProfesional getDireccionProfesional(DireccionProfesional direccionProfesionalRequest) {
		return DireccionProfesional.builder()
				.id(direccionProfesionalRequest.getId())
				.provincia(direccionProfesionalRequest.getProvincia())
				.localidad(direccionProfesionalRequest.getLocalidad())
				.barrio(direccionProfesionalRequest.getBarrio())
				.departamento(direccionProfesionalRequest.getDepartamento())
				.direccion(direccionProfesionalRequest.getDireccion())
				.piso(direccionProfesionalRequest.getPiso())
				.referencia(direccionProfesionalRequest.getReferencia())
				.build();
	}
	
	public static DireccionInstitucionSalud getDireccionCentroSalud(DireccionInstitucionSalud direccionCentroSaludRequest) {
		return DireccionInstitucionSalud.builder()
				.id(direccionCentroSaludRequest.getId())
				.provincia(direccionCentroSaludRequest.getProvincia())
				.localidad(direccionCentroSaludRequest.getLocalidad())
				.barrio(direccionCentroSaludRequest.getBarrio())
				.departamento(direccionCentroSaludRequest.getDepartamento())
				.direccion(direccionCentroSaludRequest.getDireccion())
				.piso(direccionCentroSaludRequest.getPiso())
				.referencia(direccionCentroSaludRequest.getReferencia())
				.build();
	}
	
	
	public static DireccionResponse getDireccionResponse(Direccion direccionRequest) {
		return DireccionResponse.builder()
				.id(direccionRequest.getId())
				.direccion(direccionRequest.getDireccion())
				.provincia(direccionRequest.getProvincia())
				.localidad(direccionRequest.getLocalidad())
				.barrio(direccionRequest.getBarrio())
				.departamento(direccionRequest.getDepartamento())
				.piso(direccionRequest.getPiso())
				.referencia(direccionRequest.getReferencia())
				.build();
	}
	
	public static List<DireccionUsuario> getListDireccionesUsuarios(List<DireccionUsuario> direccionesUsuarioRequest){
		List<DireccionUsuario> direcciones = new ArrayList<DireccionUsuario>();
		direccionesUsuarioRequest.stream().forEach(dir ->
			direcciones.add(getDireccionUsuario(dir)));
		return direcciones;
	}
	
	public static List<DireccionProfesional> getListDireccionesProfesionales(List<DireccionProfesional> direccionesProfesionalRequest){
		List<DireccionProfesional> direcciones = new ArrayList<DireccionProfesional>();
		direccionesProfesionalRequest.stream().forEach(dir ->
			direcciones.add(getDireccionProfesional(dir)));
		return direcciones;
	}
	
	public static List<DireccionInstitucionSalud> getListDireccionesCentrosSalud(List<DireccionInstitucionSalud> direccionesCentroSaludRequest){
		List<DireccionInstitucionSalud> direcciones = new ArrayList<DireccionInstitucionSalud>();
		direccionesCentroSaludRequest.stream().forEach(dir ->
			direcciones.add(getDireccionCentroSalud(dir)));
		return direcciones;
	}
	
	public static List<DireccionResponse> getListDireccionResponse(
			List<Direccion> direccionesRequest){
		List<DireccionResponse> direccionesResponse = new ArrayList<DireccionResponse>();
		direccionesRequest.stream().forEach(dir ->
			direccionesResponse.add(getDireccionResponse(dir)));
		return direccionesResponse;
	}
	
	public static List<DireccionResponse> getListDireccionProfesionalResponse(
			List<DireccionProfesional> direccionesRequest){
		List<DireccionResponse> direccionesResponse = new ArrayList<DireccionResponse>();
		direccionesRequest.stream().forEach(dir ->
			direccionesResponse.add(getDireccionResponse(dir)));
		return direccionesResponse;
	}
	
	public static List<DireccionResponse> getListDireccionUsuarioResponse(
			List<DireccionUsuario> direccionesRequest){
		List<DireccionResponse> direccionesResponse = new ArrayList<DireccionResponse>();
		direccionesRequest.stream().forEach(dir ->
			direccionesResponse.add(getDireccionResponse(dir)));
		return direccionesResponse;
	}
}
