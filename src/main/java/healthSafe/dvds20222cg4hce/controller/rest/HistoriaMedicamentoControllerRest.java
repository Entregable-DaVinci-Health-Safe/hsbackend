package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.controller.request.medicamento.HistoriaMedicamentoRequest;
import healthSafe.dvds20222cg4hce.controller.request.medicamento.HistoriaMedicamentoUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.HistoriaMedicamentoResponse;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.medicamento.HistoriaMedicamentoService;
import healthSafe.dvds20222cg4hce.utils.MedicamentoUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class HistoriaMedicamentoControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private HistoriaMedicamentoService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/historiasMedicas/{id}/medicamentos")
	public ResponseEntity<Object> getMedicamentosByHistoriaMedica(@PathVariable Long id){
		List<HistoriaMedicamento> historiaMedicamentos = null;
		List<HistoriaMedicamentoResponse> response = null;
		
		try { historiaMedicamentos = service.getHistoriaMedicamentosByHistoriaMedica(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = MedicamentoUtils.getListHistoriaMedicamentoProductoResponse(historiaMedicamentos);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/historiasMedicas/agregarMedicamentos")
	public ResponseEntity<HistoriaMedicamentoResponse> addMedicamentoInHistoria(
			@RequestBody HistoriaMedicamentoRequest datosHistoriaMedicamento){
		HistoriaMedicamento historiaMedicamento = null;
		HistoriaMedicamentoResponse response = null;
		
		try {historiaMedicamento = mapper.map(datosHistoriaMedicamento, HistoriaMedicamento.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try { historiaMedicamento = service.save(historiaMedicamento);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try {response = mapper.map(historiaMedicamento, HistoriaMedicamentoResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping(path = "/historiasMedicas/actualizarMedicamentos/{id}")
	public ResponseEntity<HistoriaMedicamentoResponse> updateMedicamentoInHistoria(@PathVariable Long id,
			@RequestBody HistoriaMedicamentoUpdateRequest datosHistoriaMedicamento){
		HistoriaMedicamento historiaMedicamento = null;
		HistoriaMedicamento newHistoriaMedicamento = null;
		HistoriaMedicamentoResponse response = null;
		
		try {historiaMedicamento = service.findById(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {newHistoriaMedicamento = mapper.map(datosHistoriaMedicamento, HistoriaMedicamento.class);}
		catch(Exception e) {e.printStackTrace();}
		
		historiaMedicamento.setComentarios(newHistoriaMedicamento.getComentarios());
		
		try { historiaMedicamento = service.update(historiaMedicamento);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try {response = mapper.map(historiaMedicamento, HistoriaMedicamentoResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping(path = "/historiasMedicas/eliminarMedicamentos/{id}")
	public ResponseEntity<Object> removeMedicamentoInHistoria(@PathVariable Long id){
		
		try { service.delete(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
		
	}
	
}
