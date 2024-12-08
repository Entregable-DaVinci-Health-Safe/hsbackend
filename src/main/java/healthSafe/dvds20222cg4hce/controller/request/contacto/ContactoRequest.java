package healthSafe.dvds20222cg4hce.controller.request.contacto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoRequest {
	private String telefono;
	private String mailAlternativo;
}
