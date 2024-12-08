package healthSafe.dvds20222cg4hce.controller.request.prescripcion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecetaRequest {
	private String tipo;
	private String url;
	private String descripcion;
}
