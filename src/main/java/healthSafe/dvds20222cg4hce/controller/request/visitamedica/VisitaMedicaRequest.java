package healthSafe.dvds20222cg4hce.controller.request.visitamedica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitaMedicaRequest {
	private Boolean atencionVirtual;
	private String fechaVisita;
	private String indicaciones;
	private Long historiaMedicaId;
	private Long diagnosticoId;
	private Long profesionalId;
	private Long especialidadId;
	private Long institucionSaludId;
}
