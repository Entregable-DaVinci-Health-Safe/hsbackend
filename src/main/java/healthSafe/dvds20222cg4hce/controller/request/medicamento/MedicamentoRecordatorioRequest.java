package healthSafe.dvds20222cg4hce.controller.request.medicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoRecordatorioRequest {
	private String fechaInicio;
	private String fechaFinal;
	private Boolean esCronico;
	private Integer frecuencia;
	private String tipoFrecuencia;
	private Integer dosis;
	private Integer reposicion;
	private String googleId;
	private Long historiaMedicamentoId;
}
