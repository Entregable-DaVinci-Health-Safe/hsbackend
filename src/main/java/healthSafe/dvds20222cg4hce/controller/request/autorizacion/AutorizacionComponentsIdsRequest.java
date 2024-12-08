package healthSafe.dvds20222cg4hce.controller.request.autorizacion;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorizacionComponentsIdsRequest {
	private List<Long> componentsIds;
}
