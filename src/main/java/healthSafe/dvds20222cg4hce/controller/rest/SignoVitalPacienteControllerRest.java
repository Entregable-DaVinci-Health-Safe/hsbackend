package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.ArrayList;
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

import healthSafe.dvds20222cg4hce.controller.request.signovital.SignoVitalCustomRequest;
import healthSafe.dvds20222cg4hce.controller.request.signovital.SignoVitalCustomUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.signovital.SignoVitalPacienteRequest;
import healthSafe.dvds20222cg4hce.controller.request.signovital.SignoVitalPacienteUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalCustomResponse;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalPacienteResponse;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.domain.signovital.TipoSignoVital;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.signovital.SignoVitalCustomService;
import healthSafe.dvds20222cg4hce.service.signovital.SignoVitalPacienteService;
import healthSafe.dvds20222cg4hce.service.signovital.TipoSignoVitalService;
import healthSafe.dvds20222cg4hce.utils.SignoVitalUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class SignoVitalPacienteControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private SignoVitalPacienteService service;
	
	@Autowired
	private SignoVitalCustomService signoVitalCustomService;
	
	@Autowired
	private TipoSignoVitalService tipoSignoVitalService;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping("/tiposSignosVitales")
	public List<TipoSignoVital> getListTiposSignosVitales(){
		return tipoSignoVitalService.list();
	}
	
	@GetMapping("historiasMedicas/{id}/signosVitalesCustoms")
	public ResponseEntity<List<SignoVitalCustomResponse>> getSignosVitalesCustomsFromHistoriaMedica(@PathVariable Long id){
		List<SignoVitalCustom> signosVitalesCustoms = new ArrayList<SignoVitalCustom>();
		List<SignoVitalCustomResponse> response = new ArrayList<SignoVitalCustomResponse>();
		
		signosVitalesCustoms = signoVitalCustomService.listSignoVitalCustomByHistoriamedica(id);
		
		response = SignoVitalUtils.getListSignosVitalesCustomsResponse(signosVitalesCustoms);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("/signosVitalesCustoms")
	public ResponseEntity<Object> createSignoVitalCustom(
			@RequestBody SignoVitalCustomRequest signoVitalCustomData){
		
		SignoVitalCustom signoVitalCustom = null;
		SignoVitalCustomResponse signoVitalCustomResponse = null;
		
		try {signoVitalCustom = mapper.map(signoVitalCustomData, SignoVitalCustom.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try {signoVitalCustom = signoVitalCustomService.save(signoVitalCustom);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		};
		
		try {signoVitalCustomResponse = mapper.map(signoVitalCustom, SignoVitalCustomResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(signoVitalCustomResponse, HttpStatus.CREATED);
		
	}
	
	@PostMapping("/signosVitalesPacientes")
	public ResponseEntity<Object> createSignoVitalPaciente(
			@RequestBody SignoVitalPacienteRequest signoVitalPacienteData){
		
		SignoVitalPaciente signoVitalPaciente = null;
		SignoVitalPacienteResponse signoVitalPacienteResponse = null;
		
		try {signoVitalPaciente = mapper.map(signoVitalPacienteData, SignoVitalPaciente.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try {signoVitalPaciente = service.save(signoVitalPaciente);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		};
		
		try {signoVitalPacienteResponse = mapper.map(signoVitalPaciente, SignoVitalPacienteResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(signoVitalPacienteResponse, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/signosVitalesCustoms/{id}")
	public ResponseEntity<Object> updateSignoVitalCustom(@PathVariable Long id,
			@RequestBody SignoVitalCustomUpdateRequest updateData){
		SignoVitalCustom signoVitalCustomToUpdate = null;
		SignoVitalCustom newSignoVitalCustom = null;
		SignoVitalCustomResponse signoVitalCustomResponse = null;
		
		try {newSignoVitalCustom = mapper.map(updateData, SignoVitalCustom.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try {
			signoVitalCustomToUpdate = signoVitalCustomService.findById(id);
			signoVitalCustomToUpdate.setMinimo(newSignoVitalCustom.getMinimo());
			signoVitalCustomToUpdate.setMaximo(newSignoVitalCustom.getMaximo());
			
			signoVitalCustomToUpdate.setSegundoMinimo(newSignoVitalCustom.getSegundoMinimo());
			signoVitalCustomToUpdate.setSegundoMaximo(newSignoVitalCustom.getSegundoMaximo());
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {signoVitalCustomToUpdate = signoVitalCustomService.update(signoVitalCustomToUpdate);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		};
		
		try {signoVitalCustomResponse = mapper.map(signoVitalCustomToUpdate, SignoVitalCustomResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(signoVitalCustomResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/signosVitalesPacientes/{id}")
	public ResponseEntity<Object> updateSignoVitalPaciente(@PathVariable Long id,
			@RequestBody SignoVitalPacienteUpdateRequest updateData){
		SignoVitalPaciente signoVitalPacienteToUpdate = null;
		SignoVitalPaciente newSignoVitalPaciente = null;
		SignoVitalPacienteResponse signoVitalPacienteResponse = null;
		
		try {newSignoVitalPaciente = mapper.map(updateData, SignoVitalPaciente.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try {
			signoVitalPacienteToUpdate = service.findById(id);
			signoVitalPacienteToUpdate.setValor(newSignoVitalPaciente.getValor());
			signoVitalPacienteToUpdate.setSegundoValor(newSignoVitalPaciente.getSegundoValor());
			signoVitalPacienteToUpdate.setComentario(newSignoVitalPaciente.getComentario());
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {signoVitalPacienteToUpdate = service.update(signoVitalPacienteToUpdate);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		};
		
		try {signoVitalPacienteResponse = mapper.map(signoVitalPacienteToUpdate, SignoVitalPacienteResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(signoVitalPacienteResponse, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/signosVitalesPacientes/{id}")
	public ResponseEntity<HttpStatus> deleteSignoVitalPaciente(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
	}
	
	@DeleteMapping("/signosVitalesCustoms/{id}")
	public ResponseEntity<HttpStatus> deleteSignoVitalCustom(@PathVariable Long id){
		try {
			signoVitalCustomService.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
	}
}
