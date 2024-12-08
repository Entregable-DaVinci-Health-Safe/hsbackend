package healthSafe.dvds20222cg4hce.controller.response.calendario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacunaUsuarioResponse {
	private Long id;
	private String nombre;
	private String descripcion;
	private Integer numeroDosis;
	private String fechaAplicada;
	private String aplicada;
}
