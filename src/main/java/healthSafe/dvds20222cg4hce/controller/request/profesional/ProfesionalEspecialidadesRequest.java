package healthSafe.dvds20222cg4hce.controller.request.profesional;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfesionalEspecialidadesRequest {
	private List<Long> especialidadesIds;
}
