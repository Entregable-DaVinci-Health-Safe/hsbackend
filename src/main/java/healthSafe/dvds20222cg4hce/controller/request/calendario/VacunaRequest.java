package healthSafe.dvds20222cg4hce.controller.request.calendario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacunaRequest {
	private String nombre;
	private String descripcion;
	private Boolean obligatoria;
	private Integer cantidadDosis;
}
