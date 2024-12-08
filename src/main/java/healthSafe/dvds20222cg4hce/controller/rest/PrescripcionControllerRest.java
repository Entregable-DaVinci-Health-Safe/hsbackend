package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.controller.request.prescripcion.EstudioDeleteRequest;
import healthSafe.dvds20222cg4hce.controller.request.prescripcion.EstudioRequest;
import healthSafe.dvds20222cg4hce.controller.request.prescripcion.PrescripcionRequest;
import healthSafe.dvds20222cg4hce.controller.request.prescripcion.RecetaRequest;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.PrescripcionResponse;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.prescripcion.PrescripcionService;
import healthSafe.dvds20222cg4hce.utils.PrescripcionUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class PrescripcionControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private PrescripcionService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/prescripciones/all")
	public List<Prescripcion> getListAll(){
		return service.list();
	}
	
	@GetMapping(path = "/prescripciones")
	public ResponseEntity<Page<PrescripcionResponse>> getList(Pageable pageable){
		
		Page<PrescripcionResponse> prescripcionResponse = null;
		Page<Prescripcion> prescripciones = null;
		
		try { prescripciones = service.list(pageable);}
		catch(Exception e) { e.printStackTrace(); }
		
		try { 
			prescripcionResponse = prescripciones.map(prescripcion -> mapper.map(prescripcion, PrescripcionResponse.class));
		}catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(prescripcionResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "historiasMedicas/{id}/prescripcionesWithDocuments")
	public ResponseEntity<List<PrescripcionResponse>> getPrescripcionesWithDocuments(@PathVariable Long id){
		
		List<PrescripcionResponse> prescripcionResponse = null;
		List<Prescripcion> prescripciones = null;
		
		try { prescripciones = service.getPrescripcionesWithDocumentsByHistoriaMedicaId(id);}
		catch(Exception e) { e.printStackTrace(); }
		
		try { 
			prescripcionResponse = PrescripcionUtils.getListPrescripcionesResponse(prescripciones);
		}catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(prescripcionResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/prescripciones")
	public ResponseEntity<PrescripcionResponse> createPrescricpion(@RequestBody PrescripcionRequest datosPrescripcion){
		Prescripcion prescripcion = null;
		PrescripcionResponse prescripcionResponse = null;
		
		try {prescripcion = mapper.map(datosPrescripcion, Prescripcion.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {prescripcion = service.save(prescripcion);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { 
			prescripcionResponse = mapper.map(prescripcion, PrescripcionResponse.class);
		} catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(prescripcionResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/prescripcion/{id}/crearEstudio")
	public ResponseEntity<PrescripcionResponse> addEstudio(@PathVariable Long id, @RequestBody EstudioRequest datosEstudio){
		Prescripcion prescripcion = null;
		Estudio estudio = null;
		PrescripcionResponse prescripcionResponse = null;
		
		try {prescripcion = service.findById(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { estudio = mapper.map(datosEstudio, ArgEstudio.class);
		}catch(Exception e) { e.printStackTrace();}
		
		try {prescripcion = service.createEstudio(prescripcion, estudio);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { 
			prescripcionResponse = mapper.map(prescripcion, PrescripcionResponse.class);
		} catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(prescripcionResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/prescripcion/{id}/crearReceta")
	public ResponseEntity<PrescripcionResponse> addReceta(@PathVariable Long id, @RequestBody RecetaRequest datosReceta){
		Prescripcion prescripcion = null;
		Receta receta = null;
		PrescripcionResponse prescripcionResponse = null;
		
		try {prescripcion = service.findById(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { receta = mapper.map(datosReceta, ArgReceta.class);
		}catch(Exception e) { e.printStackTrace();}
		
		try {prescripcion = service.createReceta(prescripcion, receta);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { 
			prescripcionResponse = mapper.map(prescripcion, PrescripcionResponse.class);
		} catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(prescripcionResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/prescripciones/{id}")
	public ResponseEntity<Object> getPrescripcionById(@PathVariable Long id){
		PrescripcionResponse response = null;
		Prescripcion prescripcion = null;
		
		try {prescripcion = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		 
		try {response = mapper.map(prescripcion, PrescripcionResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/prescripciones/{id}")
	public ResponseEntity<HttpStatus> deletePrescripciones(@PathVariable Long id){
		try {
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
	}
	
	@DeleteMapping("/prescripciones/{prescripcionId}/estudios/{estudioId}")
	public ResponseEntity<HttpStatus> deleteEstudio(@PathVariable Long prescripcionId, 
			@PathVariable Long estudioId){
		Prescripcion prescripcion = null;
		
		try {prescripcion = service.findById(prescripcionId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {
			service.deleteEstudio(prescripcion, estudioId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
	}
	
	@DeleteMapping("/prescripciones/{prescripcionId}/recetas/{recetaId}")
	public ResponseEntity<HttpStatus> deleteReceta(@PathVariable Long prescripcionId, 
			@PathVariable Long recetaId){
		Prescripcion prescripcion = null;
		
		try {prescripcion = service.findById(prescripcionId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {
			service.deleteReceta(prescripcion, recetaId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		catch(Exception e) {return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
	}	

}
