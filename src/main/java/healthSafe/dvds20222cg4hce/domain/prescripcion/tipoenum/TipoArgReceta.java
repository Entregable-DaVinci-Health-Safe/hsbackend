package healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum;

import java.util.LinkedList;
import java.util.List;

public enum TipoArgReceta {
	RECETA("Receta");
	
	private String descripcion;
	
	private TipoArgReceta(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<TipoArgReceta> getTipoArgRecetas(){
		List<TipoArgReceta> tipoArgRecetas = new LinkedList<>();
		tipoArgRecetas.add(TipoArgReceta.RECETA);
		return tipoArgRecetas;
	}
}
