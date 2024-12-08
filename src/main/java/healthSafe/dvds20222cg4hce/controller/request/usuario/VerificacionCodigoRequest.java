package healthSafe.dvds20222cg4hce.controller.request.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificacionCodigoRequest {
	private String mail;
	private String codigo;
}
