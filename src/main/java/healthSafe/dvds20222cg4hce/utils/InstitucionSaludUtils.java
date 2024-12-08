package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.InstitucionSaludResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;

public final class InstitucionSaludUtils {
	private InstitucionSaludUtils() {}
	
	public static InstitucionSalud getInstitucionSalud(InstitucionSalud institucionSaludRequest) {
		return InstitucionSalud.builder()
				.id(institucionSaludRequest.getId())
				.nombre(institucionSaludRequest.getNombre())
				.direccion(DireccionUtils.getDireccionCentroSalud(institucionSaludRequest.getDireccion()))
				.contactos(ContactoUtils.getListContactosCentrosSalud(institucionSaludRequest.getContactos()))
				.profesionales(ProfesionalUtils.getListProfesionalesActivos(institucionSaludRequest.getProfesionales()))
				.activo(institucionSaludRequest.getActivo())
				.build();
	}
	
	
	public static InstitucionSaludResponse getInstitucionSaludResponse(InstitucionSalud institucionSaludRequest) {
		DireccionResponse direccionResponse = null;
		List<ProfesionalResponse> profesionalesResponse = null;
		
		if(institucionSaludRequest.getDireccion() != null) {
			direccionResponse =  DireccionUtils.getDireccionResponse(institucionSaludRequest.getDireccion());
		}
		if(institucionSaludRequest.getProfesionales() != null) {
			profesionalesResponse =  ProfesionalUtils.getListProfesionalesActivosResponse(institucionSaludRequest.getProfesionales());
		}
		return InstitucionSaludResponse.builder()
				.id(institucionSaludRequest.getId())
				.nombre(institucionSaludRequest.getNombre())
				.contactos(ContactoUtils.getListContactosCentrosSaludResponse(institucionSaludRequest.getContactos()))
				.direccion(direccionResponse)
				.profesionales(profesionalesResponse)
				.activo(institucionSaludRequest.getActivo())
				.build();
	}
	
	public static List<InstitucionSalud> getListInstitucionesSalud(List<InstitucionSalud> institucionSaludRequest){
		List<InstitucionSalud> institucionesSalud = new ArrayList<InstitucionSalud>();
		institucionSaludRequest.stream().forEach(itc -> 
			institucionesSalud.add(getInstitucionSalud(itc)));
		return institucionesSalud;
	}
	
	public static List<InstitucionSalud> getListInstitucionesSaludActivos(List<InstitucionSalud> institucionSaludRequest){
		List<InstitucionSalud> institucionesSalud = new ArrayList<InstitucionSalud>();
		institucionSaludRequest.stream().forEach(itc -> {
			if(itc.getActivo()) {institucionesSalud.add(getInstitucionSalud(itc)); }});
		return institucionesSalud;
	}
	
	public static List<InstitucionSaludResponse> getListInstitucionesSaludResponse(
			List<InstitucionSalud> institucionSaludRequest){
		
		List<InstitucionSaludResponse> institucionesSaludResponse = new ArrayList<InstitucionSaludResponse>();
		institucionSaludRequest.stream().forEach(itc -> 
			institucionesSaludResponse.add(getInstitucionSaludResponse(itc)));
		return institucionesSaludResponse;
	}
	
	public static List<InstitucionSaludResponse> getListInstitucionesSaludActivosResponse(
			List<InstitucionSalud> institucionSaludRequest){
		
		List<InstitucionSaludResponse> institucionesSaludResponse = new ArrayList<InstitucionSaludResponse>();
		institucionSaludRequest.stream().forEach(itc -> {
			if(itc.getActivo()) {institucionesSaludResponse.add(getInstitucionSaludResponse(itc));}});
		return institucionesSaludResponse;
	}
}
