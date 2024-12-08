package healthSafe.dvds20222cg4hce.controller.response.signovital;

import java.util.List;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignoVitalCustomResponse {
	private Long id;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal segundoMinimo;
	private BigDecimal segundoMaximo;
	private String tipoSignoVital;
	private String medida;
	private List<SignoVitalPacienteResponse> signosVitalesPaciente;
}
