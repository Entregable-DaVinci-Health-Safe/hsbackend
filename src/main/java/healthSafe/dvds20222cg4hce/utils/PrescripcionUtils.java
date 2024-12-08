package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.prescripcion.EstudioResponse;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.PrescripcionResponse;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.RecetaResponse;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;

public final class PrescripcionUtils {
	private PrescripcionUtils() {}
	
	public static Prescripcion getPrescripcion(Prescripcion prescripcionRequest) {
		return Prescripcion.builder()
				.id(prescripcionRequest.getId())
				.paisPrescripcion(prescripcionRequest.getPaisPrescripcion())
				.estudios(getListEstudios(prescripcionRequest.getEstudios()))
				.recetas(getListRecetas(prescripcionRequest.getRecetas()))
				.build();
	}
	
	public static Receta getReceta(Receta recetaRequest) {
		Receta receta = null;
		
		if(recetaRequest instanceof ArgReceta) {
			receta = ArgReceta.builder()
					.tipoReceta(((ArgReceta) recetaRequest).getTipoReceta())
					.build();	
		}
		return receta;
	}
	
	public static Estudio getEstudio(Estudio estudioRequest) {
		Estudio estudio = null;
		
		if(estudioRequest instanceof ArgEstudio) {
			estudio = ArgEstudio.builder()
					.tipoEstudio(((ArgEstudio) estudioRequest).getTipoEstudio())
					.build();
		}
		return estudio;
	} 
	
	
	public static PrescripcionResponse getPrescripcionResponse(Prescripcion prescripcionRequest) {
		return PrescripcionResponse.builder()
				.id(prescripcionRequest.getId())
				.pais(prescripcionRequest.getPaisPrescripcion().getDescripcion())
				.estudios(getListEstudiosResponse(prescripcionRequest.getEstudios()))
				.recetas(getListRecetasResponse(prescripcionRequest.getRecetas()))
				.build();
	}
	
	public static EstudioResponse getEstudioResponse(Estudio estudio) {
		EstudioResponse estudioResponse = null;
		if(estudio instanceof ArgEstudio) {
			ArgEstudio argEstudio = (ArgEstudio) estudio;
			estudioResponse = EstudioResponse.builder()
					.id(argEstudio.getId())
					.tipo(argEstudio.getTipoEstudio().getDescripcion())
					.url(argEstudio.getUrl())
					.descripcion(argEstudio.getDescripcion())
					.fecha(DateUtils.getStringDate(argEstudio.getFechaCreado()))
					.build();		
		}
		return estudioResponse;
		
	}
	
	public static RecetaResponse getRecetaResponse(Receta receta) {
		RecetaResponse recetaResponse = null;
		if(receta instanceof ArgReceta) {
			ArgReceta argReceta = (ArgReceta) receta;
			recetaResponse = RecetaResponse.builder()
					.id(argReceta.getId())
					.tipo(argReceta.getTipoReceta().getDescripcion())
					.url(argReceta.getUrl())
					.descripcion(argReceta.getDescripcion())
					.fecha(DateUtils.getStringDate(argReceta.getFechaCreado()))
					.build();
		}
		return recetaResponse;
	}
	
	public static List<Prescripcion> getListPrescripciones(List<Prescripcion> prescripcionesRequest){
		List<Prescripcion> prescripciones = new ArrayList<Prescripcion>();
		prescripcionesRequest.stream().forEach(pcp -> 
			prescripciones.add(getPrescripcion(pcp)));
		return prescripciones;
	}
	
	public static List<Estudio> getListEstudios(List<Estudio> estudiosRequest){
		List<Estudio> estudios = new ArrayList<Estudio>();
		estudiosRequest.stream().forEach(esd -> estudios.add(getEstudio(esd)));
		return estudios;
	}
	
	public static List<Receta> getListRecetas(List<Receta> recetasRequest){
		List<Receta> recetas = new ArrayList<Receta>();
		recetasRequest.stream().forEach(rta -> recetas.add(getReceta(rta)));
		return recetas;
	}
	
	public static List<PrescripcionResponse> getListPrescripcionesResponse(
			List<Prescripcion> prescripcionesRequest){
		
		List<PrescripcionResponse> prescripcionesResponse = new ArrayList<PrescripcionResponse>();
		prescripcionesRequest.stream().forEach(pcp -> 
			prescripcionesResponse.add(getPrescripcionResponse(pcp)));
		return prescripcionesResponse;
	}
	
	public static List<EstudioResponse> getListEstudiosResponse(List<Estudio> estudiosRequest){
		List<EstudioResponse> estudiosResponse = new ArrayList<EstudioResponse>();
		estudiosRequest.stream().forEach(esd ->
			estudiosResponse.add(getEstudioResponse(esd)));
		return estudiosResponse;
	}
	
	public static List<RecetaResponse> getListRecetasResponse(List<Receta> recetasRequest){
		List<RecetaResponse> recetasResponse = new ArrayList<RecetaResponse>();
		recetasRequest.stream().forEach(rta ->
			recetasResponse.add(getRecetaResponse(rta)));
		return recetasResponse;
	}
}
