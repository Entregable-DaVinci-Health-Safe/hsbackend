package healthSafe.dvds20222cg4hce.controller.request.institucionsalud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitucionSaludUpdateRequest {
	private String nombre;
}
