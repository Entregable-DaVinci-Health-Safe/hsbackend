package healthSafe.dvds20222cg4hce.controller.response.prescripcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaResponse {
	private Long id;
	private String tipo;
	private String url;
	private String descripcion;
	private String fecha;
}
