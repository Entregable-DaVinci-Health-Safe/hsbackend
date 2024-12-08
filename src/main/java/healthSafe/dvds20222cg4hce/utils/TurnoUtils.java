package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;
import healthSafe.dvds20222cg4hce.controller.request.TurnoRequest;
import healthSafe.dvds20222cg4hce.controller.response.TurnoResponse;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.Turno;


public final class TurnoUtils {
    private TurnoUtils(){}

    public static Turno getTurnoFromRequest(TurnoRequest request){
        return Turno.builder()
                .fechaInicio(DateUtils.getFechaTimestampFromDatetime(request.getFechaInicio()))
                .direccion(request.getDireccion())
                .profesional(request.getProfesional())
                .especialidad(request.getEspecialidad())
                .institucion(request.getInstitucion())
                .motivo(request.getMotivo())
                .googleId(request.getGoogleId())
                .activo(true)
                .historiaMedica(HistoriaMedica.builder().id(request.getHistoriaMedicaId()).build())
                .build();
    }

    public static Turno getTurnoFromDomain(Turno turno){
        return Turno.builder()
                .fechaInicio(turno.getFechaInicio())
                .direccion(turno.getDireccion())
                .profesional(turno.getProfesional())
                .especialidad(turno.getEspecialidad())
                .institucion(turno.getInstitucion())
                .motivo(turno.getMotivo())
                .googleId(turno.getGoogleId())
                .activo(turno.getActivo())
                .historiaMedica(turno.getHistoriaMedica())
                .build();
    }

    public static TurnoResponse getResponseFromDomain(Turno turno){
        return TurnoResponse.builder()
                .id(turno.getId())
                .fechaInicio(DateUtils.getStringDateTime(turno.getFechaInicio()))
                .direccion(turno.getDireccion())
                .profesional(turno.getProfesional())
                .especialidad(turno.getEspecialidad())
                .institucion(turno.getInstitucion())
                .motivo(turno.getMotivo())
                .googleId(turno.getGoogleId())
                .activo(turno.getActivo())
                .build();
    }

    public static List<Turno> getListTurnoFromListDomain(List<Turno> turnosParam){
		List<Turno> turnos = new ArrayList<Turno>();
        if(turnosParam == null) return turnos;
        if(turnosParam.isEmpty()) return turnos;
		turnosParam.stream().forEach(trn -> turnos.add(getTurnoFromDomain(trn)));
		return turnos;
	}

    public static List<TurnoResponse> getListResponseFromListDomain(List<Turno> turnos){
		List<TurnoResponse> response = new ArrayList<TurnoResponse>();
        if(turnos == null) return response;
        if(turnos.isEmpty()) return response;
		turnos.stream().forEach(trn -> response.add(getResponseFromDomain(trn)));
		return response;
	}


}
