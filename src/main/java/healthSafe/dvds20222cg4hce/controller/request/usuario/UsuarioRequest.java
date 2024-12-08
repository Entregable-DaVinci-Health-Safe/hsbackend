package healthSafe.dvds20222cg4hce.controller.request.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class UsuarioRequest {
	private Long documento;
	private String nombre;
	private String apellido;
	private String mail;
	private String fechaNacimiento;
	private String genero;
	private String password;
}
