package healthSafe.dvds20222cg4hce.controller.response.calendario;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RangoEdadVacunaResponse {
	private Long id;
	private String nombre;
	private List<VacunaUsuarioResponse> vacunasAplicadas;
}
