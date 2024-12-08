package healthSafe.dvds20222cg4hce.controller.response.medicamento;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicamentoRecordatorioResponse {
	private Long id;
	private String fechaInicio;
	private String fechaFinal;
	private Boolean esCronico;
	private Integer frecuencia;
	private String tipoFrecuencia;
	private Integer dosis;
	private Integer reposicion;
	private String googleId;
}
