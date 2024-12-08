package healthSafe.dvds20222cg4hce.controller.request.ubicacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionRequest {
	private String direccion;
	private String localidad;
	private String provincia;
	private String barrio;
	private String piso;
	private String departamento;
	private String referencia;
}
