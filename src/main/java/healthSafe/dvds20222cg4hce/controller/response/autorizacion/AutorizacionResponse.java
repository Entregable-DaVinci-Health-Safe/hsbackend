package healthSafe.dvds20222cg4hce.controller.response.autorizacion;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor

@SuperBuilder
public class AutorizacionResponse extends AutorizacionComponentResponse{
	private List<AutorizacionComponentResponse> componentes;
}
