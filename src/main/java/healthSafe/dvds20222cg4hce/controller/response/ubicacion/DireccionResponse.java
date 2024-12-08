package healthSafe.dvds20222cg4hce.controller.response.ubicacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DireccionResponse {
	private Long id;
	private String direccion;
	private String localidad;
	private String provincia;
	private String barrio;
	private String piso;
	private String departamento;
	private String referencia;
}
