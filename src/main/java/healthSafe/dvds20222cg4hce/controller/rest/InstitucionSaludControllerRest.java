package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;
import javax.validation.ConstraintViolationException;
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
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoIdRequest;
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoRequest;
import healthSafe.dvds20222cg4hce.controller.request.institucionsalud.InstitucionSaludProfesionalesRequest;
import healthSafe.dvds20222cg4hce.controller.request.institucionsalud.InstitucionSaludRequest;
import healthSafe.dvds20222cg4hce.controller.request.institucionsalud.InstitucionSaludUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionRequest;
import healthSafe.dvds20222cg4hce.controller.response.InstitucionSaludResponse;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.InstitucionSaludService;
import healthSafe.dvds20222cg4hce.utils.InstitucionSaludUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class InstitucionSaludControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private InstitucionSaludService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/institucionesSalud/all")
	public List<InstitucionSalud> getListAll(){
		return service.listInstitucionesSalud();
	}
	
	@GetMapping(path = "/historiasMedicas/{id}/institucionesSalud")
	public ResponseEntity<Object> getInstitucionesSaludByHistoriaMedica(@PathVariable Long id){
		List<InstitucionSalud> institucionesSalud = null;
		List<InstitucionSaludResponse> institucionesSaludResponse = null;
		
		try { institucionesSalud = service.getInstitucionesSaludByHistoriaMedica(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {institucionesSaludResponse = InstitucionSaludUtils.getListInstitucionesSaludActivosResponse(institucionesSalud);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(institucionesSaludResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/profesionales/{id}/institucionesSalud")
	public ResponseEntity<Object> getCentrosSaludByProfesional(@PathVariable Long id){
		List<InstitucionSalud> institucionesSalud = null;
		List<InstitucionSaludResponse> institucionesSaludResponse = null;
		
		try { institucionesSalud = service.getInstitucionesSaludByProfesionales(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {institucionesSaludResponse = InstitucionSaludUtils.getListInstitucionesSaludActivosResponse(institucionesSalud);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(institucionesSaludResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/institucionesSalud")
	public ResponseEntity<InstitucionSaludResponse> createCentroSalud(
			@RequestBody InstitucionSaludRequest centroSaludData){
		
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse= null;
		
		try { institucionSalud = mapper.map(centroSaludData, InstitucionSalud.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try { institucionSalud = service.save(institucionSalud);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		};
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}")
	public ResponseEntity<Object> updateInstitucionSalud(@PathVariable Long id,
			@RequestBody InstitucionSaludUpdateRequest centroSaludData){
		InstitucionSalud institucionSalud = null;
		InstitucionSalud centroSaludNuevo = null;
		InstitucionSaludResponse institucionSaludResponse= null;
		
		try {institucionSalud = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { centroSaludNuevo = mapper.map(centroSaludData, InstitucionSalud.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		institucionSalud.setNombre(centroSaludNuevo.getNombre());
		
		try {institucionSalud = service.update(institucionSalud);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	
	@PostMapping(path = "/institucionesSalud/{id}/nuevoContacto")
	public ResponseEntity<Object> addContacto(@PathVariable Long id, 
			@RequestBody ContactoRequest contactoData){
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse= null;
		ContactoInstitucionSalud contacto = null;
		
		try {institucionSalud = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {contacto = mapper.map(contactoData, ContactoInstitucionSalud.class);}
		catch(Exception e){ e.printStackTrace();}
		
		try { institucionSalud = service.createContacto(institucionSalud, contacto);}
		catch(ConstraintViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {e.printStackTrace();}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) {e.printStackTrace();}
	
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/actualizarContacto/{contactoId}")
	public ResponseEntity<Object> updateContacto(@PathVariable Long id, 
			@PathVariable Long contactoId, @RequestBody ContactoRequest contactoData){
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse= null;
		ContactoInstitucionSalud contacto = null;
		
		try {institucionSalud = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {contacto = mapper.map(contactoData, ContactoInstitucionSalud.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {institucionSalud = service.updateContacto(institucionSalud, contactoId, contacto);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		catch(ConstraintViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}catch(ClassCastException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Ingresa el id correspondiente", HttpStatus.EXPECTATION_FAILED);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/institucionesSalud/{id}/eliminarContacto")
	public ResponseEntity<Object> removeContacto(@PathVariable Long id, @RequestBody ContactoIdRequest contactoData){
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse= null;
		
		try {institucionSalud = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {institucionSalud = service.removeContacto(institucionSalud, contactoData.getContactoId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}

	
	@PutMapping(path = "/institucionesSalud/{id}/actualizarDireccion/{direccionId}")
	public ResponseEntity<Object> updateDireccion(@PathVariable Long id, 
			@PathVariable Long direccionId, @RequestBody DireccionRequest direccionData){
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse= null;
		DireccionInstitucionSalud direccion = null;
		
		try {institucionSalud = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {direccion = mapper.map(direccionData, DireccionInstitucionSalud.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {institucionSalud = service.updateDireccion(institucionSalud, direccionId, direccion);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(ClassCastException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Ingresa el id correspondiente", HttpStatus.EXPECTATION_FAILED);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/agregarProfesional/{profesionalId}")
	public ResponseEntity<InstitucionSaludResponse> addProfesionalInInstitucionSalud(
			@PathVariable("id") Long institucionSaludId, @PathVariable("profesionalId") Long profesionalId){
		
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse = null;
		
		try { institucionSalud = service.addProfesional(institucionSaludId, profesionalId);}
		catch(Exception e) { e.printStackTrace();}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/agregarProfesionales")
	public ResponseEntity<Object> addProfesionalesInInstitucionSalud(@PathVariable("id") Long institucionSaludId,
			@RequestBody InstitucionSaludProfesionalesRequest profesionalesIdsData){
		
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse = null;
		
		try { institucionSalud = service.findById(institucionSaludId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSalud = service.addProfesionales(institucionSalud, profesionalesIdsData.getProfesionalesIds());}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/removerProfesional/{profesionalId}")
	public ResponseEntity<InstitucionSaludResponse> removeProfesionalInInstitucionSalud(
			@PathVariable("id") Long institucionSaludId, @PathVariable("profesionalId") Long profesionalId){
		
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse = null;
		
		try { institucionSalud = service.removeProfesional(institucionSaludId, profesionalId);}
		catch(Exception e) { e.printStackTrace();}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/removerProfesionales")
	public ResponseEntity<Object> removeProfesionalesInInstitucionSalud(@PathVariable("id") Long institucionSaludId,
			@RequestBody InstitucionSaludProfesionalesRequest profesionalesIdsData){
		
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse = null;
		
		try { institucionSalud = service.findById(institucionSaludId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSalud = service.removeProfesionales(institucionSalud, profesionalesIdsData.getProfesionalesIds());}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/activar")
	public ResponseEntity<Object> activarInstitucionSalud(@PathVariable("id") Long institucionSaludId){
		
		InstitucionSalud institucionSalud = null;
		InstitucionSaludResponse institucionSaludResponse = null;
		
		try { institucionSalud = service.findById(institucionSaludId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSalud = service.activarInstitucionSalud(institucionSalud);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { institucionSaludResponse = mapper.map(institucionSalud, InstitucionSaludResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(institucionSaludResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/institucionesSalud/{id}/desactivar")
	public ResponseEntity<Object> desactivarInstitucionSalud(@PathVariable("id") Long institucionSaludId){
		
		InstitucionSalud institucionSalud = null;
		
		try { institucionSalud = service.findById(institucionSaludId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { service.desactivarInstitucionSalud(institucionSalud);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
}
