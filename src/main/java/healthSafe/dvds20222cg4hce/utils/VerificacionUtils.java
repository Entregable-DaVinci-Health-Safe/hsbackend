package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;

public final class VerificacionUtils {
	private VerificacionUtils() {}
	
	public static VerificacionCuenta getVerificacionCuenta(VerificacionCuenta verificacionRequest) {
		return VerificacionCuenta.builder()
				.id(verificacionRequest.getId())
				.codigo(verificacionRequest.getCodigo())
				.fechaGenerado(verificacionRequest.getFechaGenerado())
				.fechaValidado(verificacionRequest.getFechaValidado())
				.build();
	}
	
	public static List<VerificacionCuenta> getListVerificaciones(
			List<VerificacionCuenta> verificacionesRequest){
		List<VerificacionCuenta> verificaciones = new ArrayList<VerificacionCuenta>();
		verificacionesRequest.stream().forEach(vfc -> verificaciones.add(getVerificacionCuenta(vfc)));
		return verificaciones;
	}
}
