package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.HistoriaMedicaResponse;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public final class HistoriaMedicaUtils {
	private HistoriaMedicaUtils() {}
	
	public static HistoriaMedica getHistoriaMedica(HistoriaMedica historiaMedicaRequest) {
		return HistoriaMedica.builder()
				.id(historiaMedicaRequest.getId())
				.paciente(UsuarioUtils.getUsuarioPaciente(historiaMedicaRequest.getPaciente()))
				.visitasMedicas(VisitaMedicaUtils.getListVisitasMedicas(historiaMedicaRequest.getVisitasMedicas()))
				.profesionales(ProfesionalUtils.getListProfesionalesActivos(historiaMedicaRequest.getProfesionales()))
				.institucionesSalud(InstitucionSaludUtils.getListInstitucionesSalud(historiaMedicaRequest.getInstitucionesSalud()))
				.calendarios(CalendarioUtils.getListCalendarios(historiaMedicaRequest.getCalendarios()))
				.historiaMedicamentos(MedicamentoUtils.getListHistoriaMedicamentoProducto(historiaMedicaRequest.getHistoriaMedicamentos()))
				.signosVitalesCustoms(SignoVitalUtils.getListSignosVitalesCustoms(historiaMedicaRequest.getSignosVitalesCustoms()))
				.signosVitalesPaciente(SignoVitalUtils.getListSignosVitalesPaciente(historiaMedicaRequest.getSignosVitalesPaciente()))
				.gruposFamiliares(GrupoFamiliarUtils.getListGruposFamiliares(historiaMedicaRequest.getGruposFamiliares()))
				.turnos(TurnoUtils.getListTurnoFromListDomain(historiaMedicaRequest.getTurnos()))
				.build();
	}
	
	public static HistoriaMedicaResponse getHistoriaMedicaResponse(HistoriaMedica historiaMedicaRequest) {
		return HistoriaMedicaResponse.builder()
				.id(historiaMedicaRequest.getId())
				.paciente(UsuarioUtils.getUsuarioPacienteResponse(historiaMedicaRequest.getPaciente()))
				.visitasMedicas(VisitaMedicaUtils.getListVisitaMedicaResponse(historiaMedicaRequest.getVisitasMedicas()))
				.profesionales(ProfesionalUtils.getListProfesionalesActivosResponse(historiaMedicaRequest.getProfesionales()))
				.institucionesSalud(InstitucionSaludUtils.getListInstitucionesSaludResponse(historiaMedicaRequest.getInstitucionesSalud()))
				.calendarios(CalendarioUtils.getListCalendariosResponse(historiaMedicaRequest.getCalendarios()))
				.medicamentos(MedicamentoUtils.getListHistoriaMedicamentoProductoResponse(historiaMedicaRequest.getHistoriaMedicamentos()))
				.signosVitalesCustoms(SignoVitalUtils.getListSignosVitalesCustomsResponse(historiaMedicaRequest.getSignosVitalesCustoms()))
				.signosVitalesPaciente(SignoVitalUtils.getListSignosVitalesPacienteResponse(historiaMedicaRequest.getSignosVitalesPaciente()))
				.gruposFamiliaresIds(GrupoFamiliarUtils.getGruposFamiliaresIds(historiaMedicaRequest.getGruposFamiliares()))
				.turnos(TurnoUtils.getListResponseFromListDomain(historiaMedicaRequest.getTurnos()))
				.build();
	}
	
	
	public static List<HistoriaMedica> getListHistoriasMedicas(
			List<HistoriaMedica> historiasRequest){
		
		List<HistoriaMedica> historiasMedicas = new ArrayList<HistoriaMedica>();
		historiasRequest.stream().forEach(hta -> 
			historiasMedicas.add(getHistoriaMedica(hta)));
		return historiasMedicas;
	}
	
	public static List<HistoriaMedicaResponse> getListHistoriaMedicaResponse(
			List<HistoriaMedica> historiasRequest){
		
		List<HistoriaMedicaResponse> historiasMedicasResponse = new ArrayList<HistoriaMedicaResponse>();
		historiasRequest.stream().forEach(hta -> 
			historiasMedicasResponse.add(getHistoriaMedicaResponse(hta)));
		return historiasMedicasResponse;
	}

}
