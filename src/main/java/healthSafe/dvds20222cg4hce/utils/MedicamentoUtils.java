package healthSafe.dvds20222cg4hce.utils;

import java.util.ArrayList;
import java.util.List;

import healthSafe.dvds20222cg4hce.controller.response.medicamento.HistoriaMedicamentoResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoProductoResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoRecordatorioResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoWithProductoResponse;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.Medicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoProducto;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoRecordatorio;

public final class MedicamentoUtils {
	private MedicamentoUtils() {}
	
	public static HistoriaMedicamento getHistoriaMedicamento(
			HistoriaMedicamento historiaMedicamento) {
		return HistoriaMedicamento.builder()
				.id(historiaMedicamento.getId())
				.cantidad(historiaMedicamento.getCantidad())
				.medicamento(historiaMedicamento.getMedicamento())
				.recordatorio(historiaMedicamento.getRecordatorio())
				.presentacion(historiaMedicamento.getPresentacion())
				.build();
		
	}
	
	public static MedicamentoResponse getMedicamentoResponse(
			Medicamento medicamento) {
		return MedicamentoResponse.builder()
				.id(medicamento.getId())
				.nombre(medicamento.getNombre())
				.productos(getListMedicamentoProductoResponse(medicamento.getProductos()))
				.build();
	}
	
	public static MedicamentoWithProductoResponse getMedicamentoWithProductoResponse(
			MedicamentoProducto medicamentoProducto) {
		return MedicamentoWithProductoResponse.builder()
				.id(medicamentoProducto.getMedicamento().getId())
				.nombre(medicamentoProducto.getMedicamento().getNombre())
				.producto(getMedicamentoProductoResponse(medicamentoProducto))
				.build();
	}
	
	public static MedicamentoProductoResponse getMedicamentoProductoResponse(
			MedicamentoProducto medicamentoProducto) {
		return MedicamentoProductoResponse.builder()
				.id(medicamentoProducto.getId())
				.nombre(medicamentoProducto.getNombre())
				.build();
	}
	
	public static HistoriaMedicamentoResponse getHistoriaMedicamentoResponse(
			HistoriaMedicamento historiaMedicamento) {
		MedicamentoRecordatorioResponse recordatorioResponse = null;
		if(historiaMedicamento.getRecordatorio() != null) {
			recordatorioResponse = getRecordatorioResponse(historiaMedicamento.getRecordatorio());
		}
		return HistoriaMedicamentoResponse.builder()
				.id(historiaMedicamento.getId())
				.comentarios(historiaMedicamento.getComentarios())
				.cantidad(historiaMedicamento.getCantidad())
				.presentacion(historiaMedicamento.getPresentacion().getDescripcion())
				.recordatorio(recordatorioResponse)
				.medicamento(getMedicamentoWithProductoResponse(historiaMedicamento.getMedicamento()))
				.build();
		
	}
	
	public static MedicamentoRecordatorioResponse getRecordatorioResponse(MedicamentoRecordatorio recordatorio) {
		String fechaFinal = null;
		if(recordatorio.getFechaFinal() != null) {
			fechaFinal = DateUtils.getStringDate(recordatorio.getFechaFinal());
		}
		return MedicamentoRecordatorioResponse.builder()
				.id(recordatorio.getId())
				.dosis(recordatorio.getDosis())
				.frecuencia(recordatorio.getFrecuencia())
				.esCronico(recordatorio.getEsCronico())
				.fechaInicio(DateUtils.getStringDate(recordatorio.getFechaInicio()))
				.fechaFinal(fechaFinal)
				.reposicion(recordatorio.getReposicion())
				.tipoFrecuencia(recordatorio.getTipoFrecuencia().getDescripcion())
				.googleId(recordatorio.getGoogleId())
				.build();
	}
	
	public static List<MedicamentoResponse> getListMedicamentoResponse(
			List<Medicamento> medicamentos){
		
		List<MedicamentoResponse> response = new ArrayList<MedicamentoResponse>();
		medicamentos.stream().forEach(
				mdc -> response.add(getMedicamentoResponse(mdc)));
		return response;
	}
	
	public static List<MedicamentoProductoResponse> getListMedicamentoProductoResponse(
			List<MedicamentoProducto> medicamentosProductos){
		
		List<MedicamentoProductoResponse> response = new ArrayList<MedicamentoProductoResponse>();
		medicamentosProductos.stream().forEach(
				mpt -> response.add(getMedicamentoProductoResponse(mpt)));
		return response;
	}
	
	public static List<HistoriaMedicamento> getListHistoriaMedicamentoProducto(
			List<HistoriaMedicamento> historiaMedicamentoRequest){
		
		List<HistoriaMedicamento> historiaMedicamentos= new ArrayList<HistoriaMedicamento>();
		historiaMedicamentoRequest.stream().forEach(
				htm -> historiaMedicamentos.add(getHistoriaMedicamento(htm)));
		return historiaMedicamentos;
	}
	
	public static List<HistoriaMedicamentoResponse> getListHistoriaMedicamentoProductoResponse(
			List<HistoriaMedicamento> historiaMedicamento){
		
		List<HistoriaMedicamentoResponse> response = new ArrayList<HistoriaMedicamentoResponse>();
		historiaMedicamento.stream().forEach(
				htm -> response.add(getHistoriaMedicamentoResponse(htm)));
		return response;
	}
}
