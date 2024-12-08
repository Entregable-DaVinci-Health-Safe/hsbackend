package healthSafe.dvds20222cg4hce.controller.response.calendario;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class CalendarioResponse {
	private Long id;
	private String tipo;
	private List<RangoEdadVacunaResponse> rangoEdades;
}
