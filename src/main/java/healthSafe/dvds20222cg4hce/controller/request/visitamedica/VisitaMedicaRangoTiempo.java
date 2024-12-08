package healthSafe.dvds20222cg4hce.controller.request.visitamedica;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitaMedicaRangoTiempo {
	private Long historiaMedicaId;
	private String startDate;
	private String lastDate;
	private Boolean giveLatest;
}
