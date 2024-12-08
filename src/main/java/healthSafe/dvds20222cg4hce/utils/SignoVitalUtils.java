package healthSafe.dvds20222cg4hce.utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalCustomResponse;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalPacienteResponse;
import healthSafe.dvds20222cg4hce.domain.signovital.ResultadoSignoVital;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.domain.signovital.TipoSignoVital;

public final class SignoVitalUtils {
	private SignoVitalUtils() {}
	
	public static TipoSignoVital getTipoSignoVital(TipoSignoVital tipoSignoVitalRequest) {
		return TipoSignoVital.builder()
				.id(tipoSignoVitalRequest.getId())
				.nombre(tipoSignoVitalRequest.getNombre())
				.medida(tipoSignoVitalRequest.getMedida())
				.build();
	}
	
	public static SignoVitalCustom getSignoVitalCustom(SignoVitalCustom signoVitalCustomRequest) {
		return SignoVitalCustom.builder()
				.id(signoVitalCustomRequest.getId())
				.maximo(signoVitalCustomRequest.getMaximo())
				.minimo(signoVitalCustomRequest.getMinimo())
				.segundoMinimo(signoVitalCustomRequest.getSegundoMinimo())
				.segundoMaximo(signoVitalCustomRequest.getSegundoMaximo())
				.tipoSignoVital(getTipoSignoVital(signoVitalCustomRequest.getTipoSignoVital()))
				.build();
	}
	
	public static SignoVitalPaciente getSignoVitalPaciente(SignoVitalPaciente signoVitalPacienteRequest) {
		ResultadoSignoVital segundoResultado = null;
		
		if(signoVitalPacienteRequest.getSegundoValor() != null) {
			segundoResultado = getResultadoSignoVital(signoVitalPacienteRequest.getSegundoValor(),
					signoVitalPacienteRequest.getSignoVitalCustom().getSegundoMinimo(),
					signoVitalPacienteRequest.getSignoVitalCustom().getSegundoMaximo());
		}
		
		return SignoVitalPaciente.builder()
				.id(signoVitalPacienteRequest.getId())
				.valor(signoVitalPacienteRequest.getValor())
				.segundoValor(signoVitalPacienteRequest.getSegundoValor())
				.resultado(getResultadoSignoVital(signoVitalPacienteRequest.getValor(),
						signoVitalPacienteRequest.getSignoVitalCustom().getMinimo(),
						signoVitalPacienteRequest.getSignoVitalCustom().getMaximo()))
				.segundoResultado(segundoResultado)
				.fechaIngresado(signoVitalPacienteRequest.getFechaIngresado())
				.comentario(signoVitalPacienteRequest.getComentario())
				.minimo(signoVitalPacienteRequest.getMinimo())
				.maximo(signoVitalPacienteRequest.getMaximo())
				.segundoMinimo(signoVitalPacienteRequest.getSegundoMinimo())
				.segundoMaximo(signoVitalPacienteRequest.getSegundoMaximo())
				.signoVitalCustom(getSignoVitalCustom(signoVitalPacienteRequest.getSignoVitalCustom()))
				.build();
	}
	
	public static List<SignoVitalCustom> getListSignosVitalesCustoms(List<SignoVitalCustom> signosVitalesCustomsRequest){
		List<SignoVitalCustom> signosVitalesCustoms = new ArrayList<SignoVitalCustom>();
		signosVitalesCustomsRequest.stream().forEach(svc -> 
			signosVitalesCustoms.add(getSignoVitalCustom(svc)));
		return signosVitalesCustoms;
	}
	
	public static List<SignoVitalPaciente> getListSignosVitalesPaciente(List<SignoVitalPaciente> signosVitalesPacienteRequest){
		List<SignoVitalPaciente> signosVitalesPaciente = new ArrayList<SignoVitalPaciente>();
		signosVitalesPacienteRequest.stream().forEach(svp -> 
			signosVitalesPaciente.add(getSignoVitalPaciente(svp)));
		return signosVitalesPaciente;
	}
	
	public static SignoVitalCustomResponse getSignoVitalCustomResponse(SignoVitalCustom signoVitalCustomRequest) {
		List<SignoVitalPacienteResponse> signosVitalesPacienteResponse = new ArrayList<SignoVitalPacienteResponse>();
		if(signoVitalCustomRequest.getSignosVitalesPaciente() != null){
			signosVitalesPacienteResponse = getListSignosVitalesPacienteResponse(signoVitalCustomRequest.getSignosVitalesPaciente());
		}
		return SignoVitalCustomResponse.builder()
				.id(signoVitalCustomRequest.getId())
				.maximo(signoVitalCustomRequest.getMaximo())
				.minimo(signoVitalCustomRequest.getMinimo())
				.segundoMinimo(signoVitalCustomRequest.getSegundoMinimo())
				.segundoMaximo(signoVitalCustomRequest.getSegundoMaximo())
				.tipoSignoVital(signoVitalCustomRequest.getTipoSignoVital().getNombre())
				.medida(signoVitalCustomRequest.getTipoSignoVital().getMedida())
				.signosVitalesPaciente(signosVitalesPacienteResponse)
				.build();
	}
	
	public static SignoVitalPacienteResponse getSignoVitalPacienteResponse(SignoVitalPaciente signoVitalPacienteRequest) {
		String segundoResultado = null;
		
		if(signoVitalPacienteRequest.getSegundoValor() != null) {
			segundoResultado = getResultadoSignoVital(
					signoVitalPacienteRequest.getSegundoValor(),
					signoVitalPacienteRequest.getSignoVitalCustom().getSegundoMinimo(), 
					signoVitalPacienteRequest.getSignoVitalCustom().getSegundoMaximo())
					.getDescripcion();
		}
		
		return SignoVitalPacienteResponse.builder()
				.id(signoVitalPacienteRequest.getId())
				.valor(signoVitalPacienteRequest.getValor())
				.segundoValor(signoVitalPacienteRequest.getSegundoValor())
				.resultado(getResultadoSignoVital(signoVitalPacienteRequest.getValor(),
						signoVitalPacienteRequest.getSignoVitalCustom().getMinimo(),
						signoVitalPacienteRequest.getSignoVitalCustom().getMaximo())
						.getDescripcion())
				.segundoResultado(segundoResultado)
				.fechaIngresado(DateUtils.getStringDateTime(signoVitalPacienteRequest.getFechaIngresado()))
				.comentario(signoVitalPacienteRequest.getComentario())
				.minimo(signoVitalPacienteRequest.getMinimo())
				.maximo(signoVitalPacienteRequest.getMaximo())
				.segundoMinimo(signoVitalPacienteRequest.getSegundoMinimo())
				.segundoMaximo(signoVitalPacienteRequest.getSegundoMaximo())
				.build();
	}
	
	public static List<SignoVitalCustomResponse> getListSignosVitalesCustomsResponse(List<SignoVitalCustom> signosVitalesCustomsRequest){
		List<SignoVitalCustomResponse> signosVitalesCustomsResponse = new ArrayList<SignoVitalCustomResponse>();
		signosVitalesCustomsRequest.stream().forEach(svc -> 
			signosVitalesCustomsResponse.add(getSignoVitalCustomResponse(svc)));
		return signosVitalesCustomsResponse;
	}
	
	public static List<SignoVitalPacienteResponse> getListSignosVitalesPacienteResponse(List<SignoVitalPaciente> signosVitalesPacienteRequest){
		List<SignoVitalPacienteResponse> signosVitalesPacienteResponse = new ArrayList<SignoVitalPacienteResponse>();
		signosVitalesPacienteRequest.stream().forEach(svp -> 
			signosVitalesPacienteResponse.add(getSignoVitalPacienteResponse(svp)));
		return signosVitalesPacienteResponse;
	}
	

	public static ResultadoSignoVital getResultadoSignoVital(BigDecimal valor, BigDecimal minimo, BigDecimal maximo) {
		Integer compareValorWithMinimo = valor.compareTo(minimo);
		Integer compareValorWithMaximo = valor.compareTo(maximo);
		
		if(compareValorWithMinimo >= 0 && compareValorWithMaximo <= 0) {
			return ResultadoSignoVital.NORMAL;
		}else if(compareValorWithMaximo > 0) {
			return ResultadoSignoVital.ALTO;
		}else if(compareValorWithMinimo < 0) {
			return ResultadoSignoVital.BAJO;
		}
		return null;
	}
	 
}
