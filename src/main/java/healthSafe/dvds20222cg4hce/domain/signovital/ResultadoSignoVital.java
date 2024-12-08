package healthSafe.dvds20222cg4hce.domain.signovital;

import java.util.LinkedList;
import java.util.List;

public enum ResultadoSignoVital {
	BAJO("Bajo"),
	NORMAL("Normal"),
	ALTO("Alto");
	
	private String descripcion;
	
	private ResultadoSignoVital(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public static List<ResultadoSignoVital> getListResultadoSignoVital(){
		List<ResultadoSignoVital> resultadosSignosVitales = new LinkedList<ResultadoSignoVital>();
		resultadosSignosVitales.add(ResultadoSignoVital.BAJO);
		resultadosSignosVitales.add(ResultadoSignoVital.NORMAL);
		resultadosSignosVitales.add(ResultadoSignoVital.ALTO);
		return resultadosSignosVitales;
	}

}
