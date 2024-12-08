package healthSafe.dvds20222cg4hce.controller.request.institucionsalud;

import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitucionSaludRequest {
	private String nombre;
	private DireccionRequest direccion;
	private Long historiaMedicaId;
}
