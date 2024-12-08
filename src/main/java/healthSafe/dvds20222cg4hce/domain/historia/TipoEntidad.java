package healthSafe.dvds20222cg4hce.domain.historia;

import java.util.LinkedList;
import java.util.List;

public enum TipoEntidad {
	CENTRO_SALUD("CentroSalud"),
	USUARIO("Usuario"),
	PROFESIONAL("Profesional");
	
	private String descripcion;
	
	private TipoEntidad(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public static List<TipoEntidad> getTipoEntidad(){
		List<TipoEntidad> tipoEntidades = new LinkedList<>();
		tipoEntidades.add(TipoEntidad.CENTRO_SALUD);
		tipoEntidades.add(TipoEntidad.USUARIO);
		tipoEntidades.add(TipoEntidad.PROFESIONAL);
		return tipoEntidades;
	}
}
