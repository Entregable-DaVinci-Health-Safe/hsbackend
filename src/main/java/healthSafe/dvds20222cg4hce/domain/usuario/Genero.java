package healthSafe.dvds20222cg4hce.domain.usuario;

import java.util.LinkedList;
import java.util.List;

public enum Genero {
	MASCULINO("Masculino"),
	FEMENINO("Femenino"),
	SIN_ESPECIFICAR("SIN_ESPECIFICAR");
	
	private String descripcion;
	
	private Genero(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public static List<Genero> getGeneros(){
		List<Genero> generos = new LinkedList<>();
		generos.add(Genero.MASCULINO);
		generos.add(Genero.FEMENINO);
		generos.add(Genero.SIN_ESPECIFICAR);
		return generos;
	}
}
