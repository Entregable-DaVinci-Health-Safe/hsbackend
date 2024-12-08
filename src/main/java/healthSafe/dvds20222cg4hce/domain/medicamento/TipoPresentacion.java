package healthSafe.dvds20222cg4hce.domain.medicamento;

import java.util.LinkedList;
import java.util.List;

public enum TipoPresentacion {
	TEST("Test");
	
	private String descripcion;
	
	private TipoPresentacion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<TipoPresentacion> getTipoPresentaciones(){
		List<TipoPresentacion> tipoPresentaciones = new LinkedList<>();
		tipoPresentaciones.add(TipoPresentacion.TEST);
		return tipoPresentaciones;
	}

}
