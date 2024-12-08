package healthSafe.dvds20222cg4hce.domain.usuario;

import java.util.LinkedList;
import java.util.List;

public enum TipoMatricula {
	MP("Mp"),
	MN("Mn");
	
	private String descripcion;
	
	private TipoMatricula(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<TipoMatricula> getTipoMatriculas(){
		List<TipoMatricula> tipoMatricula = new LinkedList<>();
		tipoMatricula.add(TipoMatricula.MP);
		tipoMatricula.add(TipoMatricula.MN);
		return tipoMatricula;
	}
}
