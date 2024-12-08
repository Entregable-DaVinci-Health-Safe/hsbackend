package healthSafe.dvds20222cg4hce.controller.response.medicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoWithProductoResponse {
	private Long id;
	private String nombre;
	private MedicamentoProductoResponse producto;
}
