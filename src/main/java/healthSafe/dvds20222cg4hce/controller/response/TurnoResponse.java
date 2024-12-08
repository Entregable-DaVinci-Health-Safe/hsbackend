package healthSafe.dvds20222cg4hce.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoResponse {
    private Long id;
    private String fechaInicio;
	private String direccion;
	private String profesional;
	private String especialidad;
	private String institucion;
	private String motivo;
	private String googleId;
    private Boolean activo;
}
