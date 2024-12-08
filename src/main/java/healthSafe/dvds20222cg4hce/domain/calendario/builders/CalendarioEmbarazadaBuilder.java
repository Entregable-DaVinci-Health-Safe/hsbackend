package healthSafe.dvds20222cg4hce.domain.calendario.builders;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEmbarazada;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public final class CalendarioEmbarazadaBuilder implements CalendarioBuilder{
	
	private CalendarioEmbarazada calendarioEmbarazada;
	private static CalendarioEmbarazadaBuilder instance;
	
	private CalendarioEmbarazadaBuilder() {
		this.reset();
	}
	
	public static CalendarioEmbarazadaBuilder getInstance() {
		if(instance == null) {
			instance = new CalendarioEmbarazadaBuilder();
		}
		return instance;
	}
	
	@Override
	public void reset() {
		
		this.calendarioEmbarazada = CalendarioEmbarazada.builder().build();
	}
	
	@Override
	public void setHistoriaMedica(HistoriaMedica historiaMedica) {
		
		calendarioEmbarazada.setHistoriaMedica(historiaMedica);;
	}

	@Override
	public void setRangoEdades(List<RangoEdad> rangoEdades) {
		
		calendarioEmbarazada.addListRangoEdades(rangoEdades);
	}
	

	@Override
	public Calendario getCalendario() {
		
		CalendarioEmbarazada calendarioEmbarazadaProduct = CalendarioEmbarazada.builder()
					.id(calendarioEmbarazada.getId())
					.rangoEdades(calendarioEmbarazada.getRangoEdades())
					.historiaMedica(calendarioEmbarazada.getHistoriaMedica())
					.build();
		this.reset();
		return calendarioEmbarazadaProduct;
	}

}
