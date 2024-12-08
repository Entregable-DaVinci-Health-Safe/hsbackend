package healthSafe.dvds20222cg4hce.domain.calendario.builders;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEmbarazada;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioInfante;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioPersonalSalud;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public class CalendarioDirector {
	
	private CalendarioBuilder builder;
	
	public CalendarioInfante constructCalendarioInfante(HistoriaMedica historiaMedica, List<RangoEdad> rangoEdades, List<Vacuna> vacunas) {
		builder = CalendarioInfanteBuilder.getInstance();
		builder.setHistoriaMedica(historiaMedica);
		builder.setRangoEdades(rangoEdades);
		return  (CalendarioInfante) builder.getCalendario();
	}
	
	public CalendarioAdulto constructCalendarioAdulto(HistoriaMedica historiaMedica, List<RangoEdad> rangoEdades, List<Vacuna> vacunas) {
		builder = CalendarioAdultoBuilder.getInstance();
		builder.setHistoriaMedica(historiaMedica);
		builder.setRangoEdades(rangoEdades);
		return (CalendarioAdulto) builder.getCalendario();
	}
	
	public CalendarioEmbarazada constructCalendarioEmbarazada(HistoriaMedica historiaMedica, List<RangoEdad> rangoEdades, List<Vacuna> vacunas) {
		builder = CalendarioEmbarazadaBuilder.getInstance();
		builder.setHistoriaMedica(historiaMedica);
		builder.setRangoEdades(rangoEdades);
		return (CalendarioEmbarazada) builder.getCalendario();
	}
	
	public CalendarioPersonalSalud constructCalendarioPersonalSalud(HistoriaMedica historiaMedica, List<RangoEdad> rangoEdades, List<Vacuna> vacunas) {
		builder = CalendarioPersonalSaludBuilder.getInstance();
		builder.setHistoriaMedica(historiaMedica);
		builder.setRangoEdades(rangoEdades);
		return (CalendarioPersonalSalud) builder.getCalendario();
	}
}
