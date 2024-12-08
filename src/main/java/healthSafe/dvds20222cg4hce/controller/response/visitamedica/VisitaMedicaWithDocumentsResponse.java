package healthSafe.dvds20222cg4hce.controller.response.visitamedica;

import java.util.List;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.PrescripcionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitaMedicaWithDocumentsResponse {
	private Long id;
	private String fechaVisita;
	private List<PrescripcionResponse> prescripciones;
	private Boolean activo;
}
