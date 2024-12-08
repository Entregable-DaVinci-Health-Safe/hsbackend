package healthSafe.dvds20222cg4hce.exception;

import java.util.LinkedList;
import java.util.List;


public enum TipoAlerta {
	ERROR("Error"),
	AVISO("Aviso"),
	ADVERTENCIA("Advertencia");
	
	private String descripcion;
	
	private TipoAlerta(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<TipoAlerta> getTipoAlertas(){
		List<TipoAlerta> tiposErrores = new LinkedList<>();
		tiposErrores.add(TipoAlerta.ERROR);
		tiposErrores.add(TipoAlerta.ADVERTENCIA);
		tiposErrores.add(TipoAlerta.AVISO);
		return tiposErrores;
	}
}
