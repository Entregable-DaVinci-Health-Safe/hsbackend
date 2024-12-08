package healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum;

import java.util.LinkedList;
import java.util.List;

public enum PaisPrescripcion {
	ARGENTINA("Argentina");
	
	private String descripcion;
	
	private PaisPrescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<PaisPrescripcion> getPaisPrescripciones(){
		List<PaisPrescripcion> paisPrescripciones = new LinkedList<>();
		paisPrescripciones.add(PaisPrescripcion.ARGENTINA);
		return paisPrescripciones;
	}
}
