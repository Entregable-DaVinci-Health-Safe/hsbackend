package healthSafe.dvds20222cg4hce.controller.response.autorizacion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class AutorizacionComponentResponse {
	private Long id;
	private String codigo;
	private String descripcion;
	private Boolean activo;
}
