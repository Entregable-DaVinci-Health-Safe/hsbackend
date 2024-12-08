package healthSafe.dvds20222cg4hce.controller.response.medicamento;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoResponse {
	private Long id;
	private String nombre;
	private List<MedicamentoProductoResponse> productos;
}
