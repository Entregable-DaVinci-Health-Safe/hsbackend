package healthSafe.dvds20222cg4hce.controller.response.profesional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesionalEspecialidadResponse {
	private Long id;
	private EspecialidadResponse especialidad;
	
}
