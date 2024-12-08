package healthSafe.dvds20222cg4hce.controller.request.calendario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemoveVacunaUsuarioRequest {
	private Long rangoEdadId;
	private Long vacunaAplicadaId;
}
