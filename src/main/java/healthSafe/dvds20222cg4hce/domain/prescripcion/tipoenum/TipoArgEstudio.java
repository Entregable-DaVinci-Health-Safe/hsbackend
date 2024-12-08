package healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum;

import java.util.LinkedList;
import java.util.List;

public enum TipoArgEstudio {
	ORDEN("Orden"),
	RESULTADO("Resultado");
	
	private String descripcion;
	
	private TipoArgEstudio(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public static List<TipoArgEstudio> getTipoArgEstudios(){
		List<TipoArgEstudio> tipoArgEstudios = new LinkedList<>();
		tipoArgEstudios.add(TipoArgEstudio.ORDEN);
		tipoArgEstudios.add(TipoArgEstudio.RESULTADO);
		return tipoArgEstudios;
	}
}
