package healthSafe.dvds20222cg4hce.controller.request.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdatePasswordRequest {
	private String oldPassword;
	private String newPassword;
}