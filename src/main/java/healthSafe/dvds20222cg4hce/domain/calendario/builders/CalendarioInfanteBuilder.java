package healthSafe.dvds20222cg4hce.domain.calendario.builders;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioInfante;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public final class CalendarioInfanteBuilder implements CalendarioBuilder{
	
	private CalendarioInfante calendarioInfante;
	private static CalendarioInfanteBuilder instance;
	
	private CalendarioInfanteBuilder() {
		this.reset();
	}
	
	public static CalendarioInfanteBuilder getInstance() {
		if (instance == null) {
            instance = new CalendarioInfanteBuilder();
        }
        return instance;
	}
	
	@Override
	public void reset() {
		
		this.calendarioInfante = CalendarioInfante.builder().build();
	}
	
	@Override
	public void setHistoriaMedica(HistoriaMedica historiaMedica) {
		
		calendarioInfante.setHistoriaMedica(historiaMedica);;
	}

	@Override
	public void setRangoEdades(List<RangoEdad> rangoEdades) {
		
		calendarioInfante.addListRangoEdades(rangoEdades);
	}

	@Override
	public Calendario getCalendario() {
		
		CalendarioInfante calendarioInfanteProduct = CalendarioInfante.builder()
				.id(calendarioInfante.getId())
				.rangoEdades(calendarioInfante.getRangoEdades())
				.historiaMedica(calendarioInfante.getHistoriaMedica())
				.build();
		this.reset();
		return calendarioInfanteProduct;
	}

}
