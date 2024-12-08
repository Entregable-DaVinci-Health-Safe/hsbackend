package healthSafe.dvds20222cg4hce.controller.response;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InstitucionSaludResponse {
	private Long id;
	private String nombre;
	private List<ProfesionalResponse> profesionales;
	private DireccionResponse direccion;
	private List<ContactoResponse> contactos;
	private Boolean activo;
}
