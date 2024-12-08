package healthSafe.dvds20222cg4hce.controller.response.usuario;

import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.ContactoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionComponentResponse;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class UsuarioResponse {
	
	private String imgPerfil;
	private Long documento;
	private String nombre;
	private String apellido;
	private String fechaNacimiento;
	private String mail;
	private String genero;
	private Boolean activo;
	private List<DireccionResponse> direcciones;
	private List<ContactoResponse> contactos;
	private List<AutorizacionComponentResponse> autorizacionesComponentes;
}
