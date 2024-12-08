package healthSafe.dvds20222cg4hce.controller.response.profesional;

import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.ContactoResponse;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesionalResponse {
	private Long id;
	private String nombre;
	private String tipoMatricula;
	private Long matricula;
	private List<DireccionResponse> direcciones;
	private List<ContactoResponse> contactos;
	private List<EspecialidadResponse> especialidades;
	private Boolean activo;
}
