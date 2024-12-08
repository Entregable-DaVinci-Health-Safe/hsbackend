package healthSafe.dvds20222cg4hce.domain.calendario;

import java.util.LinkedList;
import java.util.List;

public enum VacunaAplicada {
	SIN_INFORMAR("Sin Informar"),
	APLICADA("Aplicada"),
	NO_APLICADA("No Aplicada");
	
	private String descripcion;
	
	private VacunaAplicada(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public static List<VacunaAplicada> getEstadosVavuna(){
		List<VacunaAplicada> aplicacionesVacuna = new LinkedList<VacunaAplicada>();
		aplicacionesVacuna.add(VacunaAplicada.APLICADA);
		aplicacionesVacuna.add(VacunaAplicada.NO_APLICADA);
		aplicacionesVacuna.add(VacunaAplicada.SIN_INFORMAR);
		
		return aplicacionesVacuna;
	}
}
