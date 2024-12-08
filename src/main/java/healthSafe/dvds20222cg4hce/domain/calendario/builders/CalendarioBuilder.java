package healthSafe.dvds20222cg4hce.domain.calendario.builders;

import java.util.List;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

public interface CalendarioBuilder {
	void reset();
	void setHistoriaMedica(HistoriaMedica historiaMedica);
	void setRangoEdades(List<RangoEdad> rangoEdades);
	Calendario getCalendario();
}
