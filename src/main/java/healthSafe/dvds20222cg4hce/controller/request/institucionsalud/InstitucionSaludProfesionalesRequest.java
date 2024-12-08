package healthSafe.dvds20222cg4hce.controller.request.institucionsalud;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstitucionSaludProfesionalesRequest {
	private List<Long> profesionalesIds;

}
