package healthSafe.dvds20222cg4hce.controller.response.signovital;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignoVitalPacienteResponse {
	private Long id;
	private BigDecimal valor;
	private BigDecimal segundoValor;
	private String resultado;
	private String segundoResultado;
	private String fechaIngresado;
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal segundoMinimo;
	private BigDecimal segundoMaximo;
	private String comentario;
}
