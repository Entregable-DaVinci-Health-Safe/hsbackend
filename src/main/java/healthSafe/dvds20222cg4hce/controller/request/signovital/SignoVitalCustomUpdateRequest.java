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
public class SignoVitalCustomUpdateRequest {
	private BigDecimal minimo;
	private BigDecimal maximo;
	private BigDecimal segundoMinimo;
	private BigDecimal segundoMaximo;
}