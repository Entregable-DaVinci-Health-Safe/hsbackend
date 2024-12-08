package healthSafe.dvds20222cg4hce.controller.request.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioUpdateRequest {
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
}
