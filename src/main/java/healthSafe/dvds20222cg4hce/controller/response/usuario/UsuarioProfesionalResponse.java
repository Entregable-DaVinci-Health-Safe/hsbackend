package healthSafe.dvds20222cg4hce.controller.response.usuario;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
public class UsuarioProfesionalResponse extends UsuarioResponse{
	private String matricula;
}
