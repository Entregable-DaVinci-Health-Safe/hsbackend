package healthSafe.dvds20222cg4hce.controller.response;

import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrupoFamiliarResponse {
	private Long id;
	private String nombre;
	private String codigo;
	private String fechaCreado;
	private List<HistoriaMedicaResponse> historiasMedicasResponse;
	private List<UsuarioResponse> admins;
	private List<UsuarioResponse> usuarios;
	private List<GrupoNotificacionResponse> notificaciones;
	private Boolean activo;
}
