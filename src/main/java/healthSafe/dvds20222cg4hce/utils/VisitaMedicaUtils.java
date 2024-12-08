package healthSafe.dvds20222cg4hce.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.Constantes;
import healthSafe.dvds20222cg4hce.controller.response.InstitucionSaludResponse;
import healthSafe.dvds20222cg4hce.controller.response.DiagnosticoResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.EspecialidadResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.visitamedica.VisitaMedicaResponse;
import healthSafe.dvds20222cg4hce.controller.response.visitamedica.VisitaMedicaWithDocumentsResponse;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;

public final class VisitaMedicaUtils {
	private static DateFormat fechaFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA);
	
	private VisitaMedicaUtils() {}
	
	public static VisitaMedica getVisitaMedica(VisitaMedica visitaRequest) {
		return VisitaMedica.builder()
				.id(visitaRequest.getId())
				.atencionVirtual(visitaRequest.getAtencionVirtual())
				.indicaciones(visitaRequest.getIndicaciones())
				.prescripciones(visitaRequest.getPrescripciones())
				.fechaVisita(visitaRequest.getFechaVisita())
				.activo(visitaRequest.getActivo())
				.build();
	}
	
	
	public static VisitaMedicaResponse getVisitaMedicaResponse(VisitaMedica visitaRequest) {
		String fechaStr = fechaFormat.format(visitaRequest.getFechaVisita());
		
		InstitucionSaludResponse institucionSaludResponse = null;
		if(visitaRequest.getInstitucionSalud() != null) {
			visitaRequest.getInstitucionSalud().setProfesionales(null);
			institucionSaludResponse = InstitucionSaludUtils.getInstitucionSaludResponse(visitaRequest.getInstitucionSalud());
		}
		
		ProfesionalResponse profesionalResponse = null;
		if(visitaRequest.getProfesional() != null) {
			profesionalResponse = ProfesionalUtils.getProfesionalResponse(visitaRequest.getProfesional());
		}
		
		EspecialidadResponse especialidadResponse = null;
		if(visitaRequest.getProfesional() != null) {
			especialidadResponse = ProfesionalUtils.getEspecialidadResponse(visitaRequest.getEspecialidad());
		}
		
		DiagnosticoResponse diagnosticoResponse = null;
		if(visitaRequest.getDiagnostico() != null) {
			diagnosticoResponse = DiagnosticoUtils.getDiagnosticoResponse(visitaRequest.getDiagnostico());
		}
		
		return VisitaMedicaResponse.builder()
				.id(visitaRequest.getId())
				.fechaVisita(fechaStr)
				.atencionVirtual(visitaRequest.getAtencionVirtual())
				.indicaciones(visitaRequest.getIndicaciones())
				.prescripciones(PrescripcionUtils.getListPrescripcionesResponse(visitaRequest.getPrescripciones()))
				.institucionSalud(institucionSaludResponse)
				.profesional(profesionalResponse)
				.especialidad(especialidadResponse)
				.diagnostico(diagnosticoResponse)
				.activo(visitaRequest.getActivo())
				.build();
	}
	
	public static VisitaMedicaWithDocumentsResponse getVisitaMedicaWithDocumentsResponse(VisitaMedica visitaRequest) {
		String fechaStr = fechaFormat.format(visitaRequest.getFechaVisita());
		
		return VisitaMedicaWithDocumentsResponse.builder()
				.id(visitaRequest.getId())
				.fechaVisita(fechaStr)
				.prescripciones(PrescripcionUtils.getListPrescripcionesResponse(visitaRequest.getPrescripciones()))
				.activo(visitaRequest.getActivo())
				.build();
	}
	
	public static List<VisitaMedica> getListVisitasMedicas(List<VisitaMedica> visitasRequest){
		List<VisitaMedica> visitas = new ArrayList<VisitaMedica>();
		visitasRequest.stream().forEach(vta -> visitas.add(getVisitaMedica(vta)));
		return visitas;
	}
	
	public static List<VisitaMedicaResponse> getListVisitaMedicaResponse(
			List<VisitaMedica> visitasRequest){
		
		List<VisitaMedicaResponse> visitasMedicasResponse = new ArrayList<VisitaMedicaResponse>();
		visitasRequest.stream().forEach(vta ->
			visitasMedicasResponse.add(getVisitaMedicaResponse(vta)));
		return visitasMedicasResponse;
	}
	
	public static List<VisitaMedicaWithDocumentsResponse> getListVisitaMedicaWithDocumentsResponse(
			List<VisitaMedica> visitasRequest){
		
		List<VisitaMedicaWithDocumentsResponse> visitasMedicasResponse = new ArrayList<VisitaMedicaWithDocumentsResponse>();
		visitasRequest.stream().forEach(vta ->
			visitasMedicasResponse.add(getVisitaMedicaWithDocumentsResponse(vta)));
		return visitasMedicasResponse;
	}
}
