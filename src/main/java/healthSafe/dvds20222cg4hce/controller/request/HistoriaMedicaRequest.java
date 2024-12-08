package healthSafe.dvds20222cg4hce.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaMedicaRequest {
	private String mailPaciente;
}
