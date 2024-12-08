package healthSafe.dvds20222cg4hce.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactoResponse {
	private Long id;
	private String mailAlternativo;
	private String telefono;
}
