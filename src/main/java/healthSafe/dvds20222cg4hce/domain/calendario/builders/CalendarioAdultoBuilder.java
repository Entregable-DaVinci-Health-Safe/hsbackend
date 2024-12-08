package healthSafe.dvds20222cg4hce.domain.calendario.builders;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public final class CalendarioAdultoBuilder implements CalendarioBuilder{
	
	private CalendarioAdulto calendarioAdulto;
	private static CalendarioAdultoBuilder instance;
	
	private CalendarioAdultoBuilder() {
		this.reset();
	}
	
	public static CalendarioAdultoBuilder getInstance() {
		if(instance == null) {
			instance = new CalendarioAdultoBuilder();
		}
		return instance;
	}
	
	@Override
	public void reset() {
		
		this.calendarioAdulto = CalendarioAdulto.builder().build();
	}
	
	@Override
	public void setHistoriaMedica(HistoriaMedica historiaMedica) {
		
		calendarioAdulto.setHistoriaMedica(historiaMedica);
	}

	@Override
	public void setRangoEdades(List<RangoEdad> rangoEdades) {
		
		calendarioAdulto.addListRangoEdades(rangoEdades);
	}
	
	@Override
	public Calendario getCalendario() {
		
		CalendarioAdulto calendarioAdultoProduct = CalendarioAdulto.builder()
				.id(calendarioAdulto.getId())
				.rangoEdades(calendarioAdulto.getRangoEdades())
				.historiaMedica(calendarioAdulto.getHistoriaMedica())
				.build();
		this.reset();
		return calendarioAdultoProduct;
	}

}
