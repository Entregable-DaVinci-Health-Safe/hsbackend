package healthSafe.dvds20222cg4hce.controller.request.profesional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesionalRequest {
	private String nombre;
	private String tipoMatricula;
	private Long matricula;
	private Long idHistoriaMedica;
}
