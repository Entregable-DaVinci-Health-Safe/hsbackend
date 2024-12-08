package healthSafe.dvds20222cg4hce.domain.calendario.builders;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioPersonalSalud;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public class CalendarioPersonalSaludBuilder implements CalendarioBuilder{
	
	private CalendarioPersonalSalud calendarioPersonalSalud;
	private static CalendarioPersonalSaludBuilder instance;
	
	private CalendarioPersonalSaludBuilder() {
		this.reset();
	}
	
	public static CalendarioPersonalSaludBuilder getInstance() {
		if(instance == null) {
			instance = new CalendarioPersonalSaludBuilder();
		}
		return instance;
	}
	
	@Override
	public void reset() {
		
		this.calendarioPersonalSalud = CalendarioPersonalSalud.builder().build();
	}

	@Override
	public void setHistoriaMedica(HistoriaMedica historiaMedica) {
		
		calendarioPersonalSalud.setHistoriaMedica(historiaMedica);
	}

	@Override
	public void setRangoEdades(List<RangoEdad> rangoEdades) {
		
		calendarioPersonalSalud.addListRangoEdades(rangoEdades);
	}

	@Override
	public Calendario getCalendario() {
		
		CalendarioPersonalSalud calendarioPersonalSaludProduct = CalendarioPersonalSalud.builder()
				.id(calendarioPersonalSalud.getId())
				.rangoEdades(calendarioPersonalSalud.getRangoEdades())
				.historiaMedica(calendarioPersonalSalud.getHistoriaMedica())
				.build();
		this.reset();
		return calendarioPersonalSaludProduct;
	}

}
