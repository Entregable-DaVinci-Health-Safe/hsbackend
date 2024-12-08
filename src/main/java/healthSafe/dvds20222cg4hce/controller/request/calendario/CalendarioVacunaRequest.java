package healthSafe.dvds20222cg4hce.controller.request.calendario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalendarioVacunaRequest {
	private Long rangoEdadId;
	private Long vacunaId;
	private String fechaAplicada;
	private String aplicada;
	private Integer numeroDosis;
}
