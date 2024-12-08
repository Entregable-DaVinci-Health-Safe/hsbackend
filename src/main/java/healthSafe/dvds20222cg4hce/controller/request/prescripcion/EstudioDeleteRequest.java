package healthSafe.dvds20222cg4hce.controller.request.prescripcion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstudioDeleteRequest {
	private List<Long> estudios;
}
