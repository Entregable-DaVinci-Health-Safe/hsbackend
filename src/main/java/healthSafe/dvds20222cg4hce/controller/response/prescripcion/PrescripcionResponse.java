package healthSafe.dvds20222cg4hce.controller.response.prescripcion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescripcionResponse {
	private Long id;
	private String pais;
	private List<EstudioResponse> estudios;
	private List<RecetaResponse> recetas;
}
