package healthSafe.dvds20222cg4hce.domain.medicamento;

import java.util.LinkedList;
import java.util.List;

public enum TipoFrecuencia {
	DIARIO("Diario"),
	SEMANAL("Semanal"),
	MENSUAL("Mensual"),
	ANUAL("Anual");
	
	private String descripcion;
	
	private TipoFrecuencia(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<TipoFrecuencia> getTipoFrecuencias(){
		List<TipoFrecuencia> tipoFrecuencias = new LinkedList<>();
		tipoFrecuencias.add(TipoFrecuencia.DIARIO);
		tipoFrecuencias.add(TipoFrecuencia.SEMANAL);
		tipoFrecuencias.add(TipoFrecuencia.MENSUAL);
		tipoFrecuencias.add(TipoFrecuencia.ANUAL);
		return tipoFrecuencias;
	}
}
