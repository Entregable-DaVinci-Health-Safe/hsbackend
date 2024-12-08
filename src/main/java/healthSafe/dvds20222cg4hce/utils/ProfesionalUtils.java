package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.profesional.EspecialidadResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;

public final class ProfesionalUtils {
	private ProfesionalUtils() {}
	
	public static Profesional getProfesional(Profesional profesionalRequest) {
		return Profesional.builder()
				.id(profesionalRequest.getId())
				.nombre(profesionalRequest.getNombre())
				.tipoMatricula(profesionalRequest.getTipoMatricula())
				.matricula(profesionalRequest.getMatricula())
				.contactos(ContactoUtils.getListContactosProfesionales(profesionalRequest.getContactos()))
				.direcciones(DireccionUtils.getListDireccionesProfesionales(profesionalRequest.getDirecciones()))
				.especialidades(getListEspecialidades(profesionalRequest.getEspecialidades()))
				.activo(profesionalRequest.getActivo())
				.build();	
	}
	
	public static Especialidad getEspecialidad(Especialidad especialidadRequest) {
		return Especialidad.builder()
				.id(especialidadRequest.getId())
				.nombre(especialidadRequest.getNombre())
				.build();
	}
	
	
	public static ProfesionalResponse getProfesionalResponse(Profesional profesionalRequest) {
		return ProfesionalResponse.builder()
				.id(profesionalRequest.getId())
				.nombre(profesionalRequest.getNombre())
				.tipoMatricula(profesionalRequest.getTipoMatricula().getDescripcion())
				.matricula(profesionalRequest.getMatricula())
				.direcciones(DireccionUtils.getListDireccionProfesionalResponse(profesionalRequest.getDirecciones()))
				.contactos(ContactoUtils.getListContactosProfesionalesResponse(profesionalRequest.getContactos()))
				.especialidades(getListEspecialidadesResponse(profesionalRequest.getEspecialidades()))
				.activo(profesionalRequest.getActivo())
				.build();
	}
	
	public static EspecialidadResponse getEspecialidadResponse(Especialidad especialidadRequest) {
		return EspecialidadResponse.builder()
				.id(especialidadRequest.getId())
				.nombre(especialidadRequest.getNombre())
				.build();
	}
	
	public static List<Profesional> getListProfesionales(List<Profesional> profesionalesRequest){
		List<Profesional> profesionales = new ArrayList<Profesional>();
		profesionalesRequest.stream().forEach(pfl ->
			profesionales.add(getProfesional(pfl)));
		return profesionales;
	}
	
	public static List<Profesional> getListProfesionalesActivos(List<Profesional> profesionalesRequest){
		List<Profesional> profesionales = new ArrayList<Profesional>();
		profesionalesRequest.stream().forEach(pfl -> {
			if(pfl.getActivo()) { profesionales.add(getProfesional(pfl)); }});
		return profesionales;
	}
	
	public static List<Especialidad> getListEspecialidades(List<Especialidad> especialidadesRequest){
		List<Especialidad> especialidades = new ArrayList<Especialidad>();
		especialidadesRequest.stream().forEach(epc -> especialidades.add(getEspecialidad(epc)));
		return especialidades;
	}
	
	public static List<ProfesionalResponse> getListProfesionalesResponse(
			List<Profesional> profesionalesRequest){
		
		List<ProfesionalResponse> profesionalesResponse = new ArrayList<ProfesionalResponse>();
		profesionalesRequest.stream().forEach(pfl ->
			profesionalesResponse.add(getProfesionalResponse(pfl)));
		return profesionalesResponse;
	}
	
	public static List<ProfesionalResponse> getListProfesionalesActivosResponse(
			List<Profesional> profesionalesRequest){
		
		List<ProfesionalResponse> profesionalesResponse = new ArrayList<ProfesionalResponse>();
		profesionalesRequest.stream().forEach(pfl -> {
			if(pfl.getActivo()) {profesionalesResponse.add(getProfesionalResponse(pfl)); }});
		return profesionalesResponse;
	}
	
	public static List<EspecialidadResponse> getListEspecialidadesResponse(
			List<Especialidad> especialidadesRequest){
		
		List<EspecialidadResponse> especialidadesResponse = new ArrayList<EspecialidadResponse>();
		especialidadesRequest.stream().forEach(epc -> 
			especialidadesResponse.add(getEspecialidadResponse(epc)));
		return especialidadesResponse;
	}
}
