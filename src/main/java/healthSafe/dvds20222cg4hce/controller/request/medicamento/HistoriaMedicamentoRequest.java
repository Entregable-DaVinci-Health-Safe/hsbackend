package healthSafe.dvds20222cg4hce.controller.request.medicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriaMedicamentoRequest {
	private Integer cantidad;
	private String comentarios;
	private String presentacion;
	private Long historiaMedicaId;
	private Long medicamentoProductoId;
}
