package healthSafe.dvds20222cg4hce.controller.request.autorizacion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AutorizacionComponentRequest {
	private String codigo;
	private String descripcion;
	
}
