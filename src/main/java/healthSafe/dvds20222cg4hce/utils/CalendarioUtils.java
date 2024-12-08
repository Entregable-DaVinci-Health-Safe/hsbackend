package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioAdultoResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioEmbarazadaResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioInfanteResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioPersonalSaludResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.RangoEdadVacunaResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.VacunaUsuarioResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.VacunaResponse;
import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEmbarazada;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioInfante;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioPersonalSalud;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.domain.calendario.VacunaUsuario;

public final class CalendarioUtils {
	private CalendarioUtils() {}
	
	public static CalendarioInfante getCalendarioInfante(
			CalendarioInfante calendarioInfante) {
		return CalendarioInfante.builder()
				.id(calendarioInfante.getId())
				.rangoEdades(calendarioInfante.getRangoEdades())
				.build();
	}
	
	public static CalendarioAdulto getCalendarioAdulto(
			CalendarioAdulto calendarioAdulto) {
		return CalendarioAdulto.builder()
				.id(calendarioAdulto.getId())
				.rangoEdades(calendarioAdulto.getRangoEdades())
				.build();
	}
	
	public static CalendarioEmbarazada getCalendarioEmbarazada(
			CalendarioEmbarazada calendarioEmbarazada) {
		return CalendarioEmbarazada.builder()
				.id(calendarioEmbarazada.getId())
				.rangoEdades(calendarioEmbarazada.getRangoEdades())
				.build();
	}
	
	public static CalendarioPersonalSalud getCalendarioPersonalSalud(
			CalendarioPersonalSalud calendarioPersonalSalud) {
		return CalendarioPersonalSalud.builder()
				.id(calendarioPersonalSalud.getId())
				.rangoEdades(calendarioPersonalSalud.getRangoEdades())
				.build();
	}
	
	public static Calendario getCalendario(Calendario requestCalendario){
		if(requestCalendario instanceof CalendarioInfante) {
			return getCalendarioInfante((CalendarioInfante) requestCalendario);
		}else if(requestCalendario instanceof CalendarioAdulto) {
			return getCalendarioAdulto((CalendarioAdulto) requestCalendario);
		}else if(requestCalendario instanceof CalendarioEmbarazada) {
			return getCalendarioEmbarazada((CalendarioEmbarazada) requestCalendario);
		}else if(requestCalendario instanceof CalendarioPersonalSalud) {
			return getCalendarioPersonalSalud((CalendarioPersonalSalud) requestCalendario);
		}
		return null;
	}
	
	public static VacunaUsuario getVacunaUsuario(CalendarioEdadVacunaLink calendarioEdadVacuna) {
		return VacunaUsuario.builder()
				.id(calendarioEdadVacuna.getVacunaUsuario().getId())
				.numeroDosis(calendarioEdadVacuna.getVacunaUsuario().getNumeroDosis())
				.fecha(calendarioEdadVacuna.getVacunaUsuario().getFecha())
				.aplicada(calendarioEdadVacuna.getVacunaUsuario().getAplicada())
				.vacuna(calendarioEdadVacuna.getVacunaUsuario().getVacuna())
				.build();
	}
	
	public static CalendarioInfanteResponse getCalendarioInfanteResponse(
			CalendarioInfante calendarioInfante) {
		return CalendarioInfanteResponse.builder()
				.id(calendarioInfante.getId())
				.tipo("INFANTE")
				.rangoEdades(getListRangoEdadResponse(calendarioInfante.getRangoEdades(), 
							calendarioInfante.getCalendarioEdadVacunaLinks()))
				.build();
	}
	
	public static CalendarioAdultoResponse getCalendarioAdultoResponse(
			CalendarioAdulto calendarioAdulto) {
		return CalendarioAdultoResponse.builder()
				.id(calendarioAdulto.getId())
				.tipo("ADULTO")
				.rangoEdades(getListRangoEdadResponse(calendarioAdulto.getRangoEdades(),
							calendarioAdulto.getCalendarioEdadVacunaLinks()))
				.build();
	}
	
	public static CalendarioEmbarazadaResponse getCalendarioEmbarazadaResponse(
			CalendarioEmbarazada calendarioEmbarazada) {
		return CalendarioEmbarazadaResponse.builder()
				.id(calendarioEmbarazada.getId())
				.tipo("EMBARAZADA")
				.rangoEdades(getListRangoEdadResponse(calendarioEmbarazada.getRangoEdades(), 
						calendarioEmbarazada.getCalendarioEdadVacunaLinks()))
				.build();
	}
	
	public static CalendarioPersonalSaludResponse getCalendarioPersonalSaludResponse(
			CalendarioPersonalSalud calendarioPersonalSalud) {
		return CalendarioPersonalSaludResponse.builder()
				.id(calendarioPersonalSalud.getId())
				.tipo("PERSONAL_SALUD")
				.rangoEdades(getListRangoEdadResponse(calendarioPersonalSalud.getRangoEdades(), 
						calendarioPersonalSalud.getCalendarioEdadVacunaLinks()))
				.build();
	}
	
	public static CalendarioResponse getCalendarioResponse(Calendario requestCalendario){
		if(requestCalendario instanceof CalendarioInfante) {
			return getCalendarioInfanteResponse((CalendarioInfante) requestCalendario);
		}else if(requestCalendario instanceof CalendarioAdulto) {
			return getCalendarioAdultoResponse((CalendarioAdulto) requestCalendario);
		}else if(requestCalendario instanceof CalendarioEmbarazada) {
			return getCalendarioEmbarazadaResponse((CalendarioEmbarazada) requestCalendario);
		}else if(requestCalendario instanceof CalendarioPersonalSalud) {
			return getCalendarioPersonalSaludResponse((CalendarioPersonalSalud) requestCalendario);
		}
		return null;
	}
	
	public static RangoEdadVacunaResponse getRangoEdadVacunaResponse(RangoEdad rangoEdad, 
			List<CalendarioEdadVacunaLink> requestLink) {
		return RangoEdadVacunaResponse.builder()
				.id(rangoEdad.getId())
				.nombre(rangoEdad.getNombre())
				.vacunasAplicadas(getListVacunaAplicadaResponseByRangoEdadLink(rangoEdad.getId(), requestLink))
				.build();
	}
	
	public static VacunaResponse getVacunaResponse(Vacuna requestVacuna) {
		return VacunaResponse.builder()
				.id(requestVacuna.getId())
				.nombre(requestVacuna.getNombre())
				.descripcion(requestVacuna.getDescripcion())
				.obligatoria(requestVacuna.getObligatoria())
				.cantidadDosis(requestVacuna.getCantidadDosis())
				.build();
	}
	
	
	public static VacunaUsuarioResponse getVacunaAplicadaResponse(VacunaUsuario requestVacuna) {
		return VacunaUsuarioResponse.builder()
				.id(requestVacuna.getId())
				.nombre(requestVacuna.getVacuna().getNombre())
				.descripcion(requestVacuna.getVacuna().getDescripcion())
				.numeroDosis(requestVacuna.getNumeroDosis())
				.fechaAplicada(DateUtils.getStringDate(requestVacuna.getFecha()))
				.aplicada(requestVacuna.getAplicada().getDescripcion())
				.build();
	}
	
	public static List<Calendario> getListCalendarios(
			List<Calendario> requestCalendarios){
		
		List<Calendario> calendarios = new ArrayList<Calendario>();
		requestCalendarios.stream().forEach(cld ->
			calendarios.add(getCalendario(cld)));
		return calendarios;
	}
	
 	public static List<CalendarioResponse> getListCalendariosResponse(
			List<Calendario> requestCalendarios){
		
		List<CalendarioResponse> calendariosResponse = new ArrayList<CalendarioResponse>();
		requestCalendarios.stream().forEach(cld ->
			calendariosResponse.add(getCalendarioResponse(cld)));
		return calendariosResponse;
	}
	
	public static List<RangoEdadVacunaResponse> getListRangoEdadResponse(List<RangoEdad> requestRangoEdad,
			List<CalendarioEdadVacunaLink> requestLink){
		
		List<RangoEdadVacunaResponse> rangoEdadesResponse = new ArrayList<RangoEdadVacunaResponse>();
		requestRangoEdad.stream().forEach(ede ->
			rangoEdadesResponse.add(getRangoEdadVacunaResponse(ede, requestLink)));
		return rangoEdadesResponse;
	}
	
	public static List<VacunaUsuarioResponse> getListVacunaResponseByRangoEdadLink(Long requestEdadId, 
			List<CalendarioEdadVacunaLink> requestLink){
		List<VacunaUsuario> vacunasUsuario = new ArrayList<VacunaUsuario>();
		requestLink.stream()
		.filter(r -> r.getRangoEdad().getId().equals(requestEdadId))
		.forEach(v -> vacunasUsuario.add(v.getVacunaUsuario()));
		
		return getListVacunaAplicadaResponse(vacunasUsuario);
	}
	
	public static List<VacunaUsuarioResponse> getListVacunaAplicadaResponseByRangoEdadLink(Long requestEdadId, 
			List<CalendarioEdadVacunaLink> requestLink){
		List<VacunaUsuario> vacunasAplicadas = new ArrayList<VacunaUsuario>();
		requestLink.stream()
		.filter(r -> r.getRangoEdad().getId().equals(requestEdadId))
		.forEach(r -> vacunasAplicadas.add(getVacunaUsuario(r)));
		
		return getListVacunaAplicadaResponse(vacunasAplicadas);
	}
	
	
	public static List<VacunaResponse> getListVacunaResponse(List<Vacuna> requestVacunas) {
		List<VacunaResponse> vacunasResponse = new ArrayList<VacunaResponse>();
		requestVacunas.stream().forEach(vcn -> vacunasResponse.add(getVacunaResponse(vcn)));
		return vacunasResponse;
	}
	
	public static List<VacunaUsuarioResponse> getListVacunaAplicadaResponse(List<VacunaUsuario> requestVacunas) {
		List<VacunaUsuarioResponse> vacunasAplicadasResponse = new ArrayList<VacunaUsuarioResponse>();
		requestVacunas.stream().forEach(vcn -> vacunasAplicadasResponse.add(getVacunaAplicadaResponse(vcn)));
		return vacunasAplicadasResponse;
	}
	
	public static String getTipoCalendario(Calendario calendario) {
		if(calendario instanceof CalendarioInfante) {
			return "INFANTE";
		}else if(calendario instanceof CalendarioAdulto) {
			return "ADULTO";
		}else if(calendario instanceof CalendarioEmbarazada) {
			return "EMBARAZADA";
		}else if(calendario instanceof CalendarioPersonalSalud) {
			return "PERSONAL_SALUD";
		}
		return null;
	}
}
