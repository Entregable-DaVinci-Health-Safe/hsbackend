package healthSafe.dvds20222cg4hce.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrupoNotificacionResponse {
	private Long id;
	private String fecha;
	private Boolean aceptada;
	private String usuarioNombre;
	private String usuarioMail;
}
