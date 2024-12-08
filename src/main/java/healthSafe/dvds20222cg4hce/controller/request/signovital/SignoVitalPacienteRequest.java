package healthSafe.dvds20222cg4hce.controller.request.signovital;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignoVitalPacienteRequest {
	private BigDecimal valor;
	private BigDecimal segundoValor;
	private String comentario;
	private Long historiaMedicaId;
	private Long signoVitalCustomId;
}
