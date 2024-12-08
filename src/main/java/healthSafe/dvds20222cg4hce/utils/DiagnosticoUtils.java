package healthSafe.dvds20222cg4hce.utils;

import healthSafe.dvds20222cg4hce.controller.response.DiagnosticoResponse;
import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;

public final class DiagnosticoUtils {
	private DiagnosticoUtils() {}
	
	public static DiagnosticoResponse getDiagnosticoResponse(Diagnostico diagnosticoRequest) {
		return DiagnosticoResponse.builder()
				.id(diagnosticoRequest.getId())
				.nombre(diagnosticoRequest.getNombre())
				.build();
	}
}
