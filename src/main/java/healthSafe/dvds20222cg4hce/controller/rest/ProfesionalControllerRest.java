package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;
import javax.validation.ConstraintViolationException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoIdRequest;
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoRequest;
import healthSafe.dvds20222cg4hce.controller.request.profesional.EspecialidadRequest;
import healthSafe.dvds20222cg4hce.controller.request.profesional.ProfesionalEspecialidadesRequest;
import healthSafe.dvds20222cg4hce.controller.request.profesional.ProfesionalRequest;
import healthSafe.dvds20222cg4hce.controller.request.profesional.ProfesionalUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionIdRequest;
import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionRequest;
import healthSafe.dvds20222cg4hce.controller.response.ErrorResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.EspecialidadResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.domain.usuario.TipoMatricula;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.profesional.EspecialidadService;
import healthSafe.dvds20222cg4hce.service.profesional.ProfesionalService;
import healthSafe.dvds20222cg4hce.utils.ExceptionUtils;
import healthSafe.dvds20222cg4hce.utils.ProfesionalUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class ProfesionalControllerRest extends HistorialMedicoAppRest{

	@Autowired
	private ProfesionalService service;
	
	@Autowired
	private EspecialidadService especialidadService;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/profesionales/all")
	public List<Profesional> getListProfesionales(){
		return service.listProfesionales();
	}
	
	@GetMapping(path = "/profesionales")
	public ResponseEntity<Page<ProfesionalResponse>> getList(Pageable pageable){
		Page<ProfesionalResponse> profesionalResponse = null;
		Page<Profesional> profesionales = null;
		
		try {profesionales = service.listProfesionales(pageable);}
		catch(Exception e) { e.printStackTrace();}
		
		try {
			profesionalResponse = profesionales.map(profesional -> mapper.map(profesional, ProfesionalResponse.class));
		}
		catch(Exception e) { e.printStackTrace(); }
				
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/profesionales/{id}/especialidades")
	public ResponseEntity<Object> getEspecialidadesByProfesional(@PathVariable Long id){
		
		List<Especialidad> especialidades = null;
		List<EspecialidadResponse> especialidadesResponse = null;
		
		try { especialidades = especialidadService.findEspecialidadesByProfesionalId(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {especialidadesResponse = ProfesionalUtils.getListEspecialidadesResponse(especialidades);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(especialidadesResponse, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping(path = "/especialidades/all")
	public List<Especialidad> getListEspecialidades(){
		return especialidadService.list();
	}
	
	@GetMapping(path = "/historiasMedicas/{id}/profesionales")
	public ResponseEntity<Object> getProfesionalesByHistoriaMedica(@PathVariable Long id){
		List<Profesional> profesionales = null;
		List<ProfesionalResponse> profesionalesResponse = null;
		
		try { profesionales = service.getProfesionalesByHistoriaMedica(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {profesionalesResponse = ProfesionalUtils.getListProfesionalesActivosResponse(profesionales);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(profesionalesResponse, HttpStatus.ACCEPTED);
	}
	
	@GetMapping(path = "/centrosSalud/{id}/profesionales")
	public ResponseEntity<Object> getProfesionalesByCentro(@PathVariable Long id){
		List<Profesional> profesionales = null;
		List<ProfesionalResponse> profesionalesResponse = null;
		
		try { profesionales = service.getProfesionalesByCentrosSalud(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {profesionalesResponse = ProfesionalUtils.getListProfesionalesActivosResponse(profesionales);}
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(profesionalesResponse, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = "/especialidades")
	public ResponseEntity<EspecialidadResponse> createEspecialidad(@RequestBody EspecialidadRequest datosEspecialidad){
		Especialidad especialidad = null;
		EspecialidadResponse especialidadResponse = null;
		
		try {especialidad = mapper.map(datosEspecialidad, Especialidad.class);}
		catch(Exception e) { e.printStackTrace();}
		
		try { especialidad = especialidadService.save(especialidad);}
		catch(Exception e) {}
		
		try { especialidadResponse = mapper.map(especialidad, EspecialidadResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(especialidadResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/profesionales")
	public ResponseEntity<Object> createProfesional(@RequestBody ProfesionalRequest datosProfesional){
		Profesional profesional = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = mapper.map(datosProfesional, Profesional.class);}
		catch(Exception e){ e.printStackTrace();}
		
		try {profesional = service.save(profesional);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ExceptionUtils.getExceptionResponse(e), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try {
			profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
		
	}
	
	@PostMapping(path = "/profesionales/{id}/nuevaDireccion")
	public ResponseEntity<Object> addDireccion(@PathVariable Long id, @RequestBody DireccionRequest datosDireccion){
		Profesional profesional = null;
		DireccionProfesional direccion = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			ErrorResponse error = ErrorResponse.builder().code("P10").message(e.getMessage()).build();
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		try {direccion = mapper.map(datosDireccion, DireccionProfesional.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {profesional = service.createDireccion(profesional, direccion);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/actualizarDireccion/{direccionId}")
	public ResponseEntity<Object> updateDireccion(@PathVariable Long id, 
			@PathVariable Long direccionId, @RequestBody DireccionRequest direccionData){
		Profesional profesional = null;
		DireccionProfesional direccion = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {direccion = mapper.map(direccionData, DireccionProfesional.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {profesional = service.updateDireccion(profesional, direccionId, direccion);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(ClassCastException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Ingresa el id correspondiente", HttpStatus.EXPECTATION_FAILED);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/profesionales/{id}/eliminarDireccion")
	public ResponseEntity<Object> removeDireccion(@PathVariable Long id, @RequestBody DireccionIdRequest direccionData){
		Profesional profesional = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {profesional = service.removeDireccion(profesional, direccionData.getDireccionId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/profesionales/{id}/nuevoContacto")
	public ResponseEntity<Object> addContacto(@PathVariable Long id, @RequestBody ContactoRequest datosContacto){
		Profesional profesional = null;
		ContactoProfesional contacto = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {contacto = mapper.map(datosContacto, ContactoProfesional.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {profesional = service.createContacto(profesional, contacto);}
		catch(ConstraintViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/actualizarContacto/{contactoId}")
	public ResponseEntity<Object> updateContacto(@PathVariable Long id, 
			@PathVariable Long contactoId, @RequestBody ContactoRequest contactoData){
		Profesional profesional = null;
		ContactoProfesional contacto = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {contacto = mapper.map(contactoData, ContactoProfesional.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {profesional = service.updateContacto(profesional, contactoId, contacto);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		catch(ConstraintViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/profesionales/{id}/eliminarContacto")
	public ResponseEntity<Object> removeContacto(@PathVariable Long id, @RequestBody ContactoIdRequest contactoData){
		Profesional profesional = null;
		ProfesionalResponse profesionalResponse = null;
		
		try {profesional = service.findById(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {profesional = service.removeContacto(profesional, contactoData.getContactoId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/agregarEspecialidad/{especialidadId}")
	public ResponseEntity<ProfesionalResponse> addEspecialidadInProfesional(@PathVariable("id") Long profesionalId,
			@PathVariable("especialidadId") Long especialidadId){
		
		ProfesionalResponse profesionalResponse = null;
		Profesional profesional = null;
		
		try { profesional = service.addEspecialidad(profesionalId, especialidadId);
		}catch(Exception e) { e.printStackTrace();}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/agregarEspecialidades")
	public ResponseEntity<Object> addEspecialidadesInProfesional(@PathVariable("id") Long profesionalId,
			@RequestBody ProfesionalEspecialidadesRequest especialidadesIdsData){
		
		ProfesionalResponse profesionalResponse = null;
		Profesional profesional = null;
		
		try { profesional = service.findById(profesionalId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { profesional = service.addEspecialidades(profesional, especialidadesIdsData.getEspecialidadesIds());}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/profesionales/{id}/removerEspecialidad/{especialidadId}")
	public ResponseEntity<ProfesionalResponse> removeEspecialidadInProfesional(@PathVariable("id") Long profesionalId,
			@PathVariable("especialidadId") Long especialidadId){
		
		ProfesionalResponse profesionalResponse = null;
		Profesional profesional = null;
		
		try {
			profesional = service.removeEspecialidad(profesionalId, especialidadId);
		}
		catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try {
			profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);
		}
		catch(Exception e){ e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/removerEspecialidades")
	public ResponseEntity<Object> removeEspecialidadesInProfesional(@PathVariable("id") Long profesionalId,
			@RequestBody ProfesionalEspecialidadesRequest especialidadesIdsData){
		
		ProfesionalResponse profesionalResponse = null;
		Profesional profesional = null;
		
		try { profesional = service.findById(profesionalId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { profesional = service.removeEspecialidades(profesional, especialidadesIdsData.getEspecialidadesIds());}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}")
	public ResponseEntity<Object> updateProfesional(@PathVariable("id") Long profesionalId,
			@RequestBody ProfesionalUpdateRequest profesionalData){
		Profesional profesional = null;
		ProfesionalResponse profesionalResponse = null;
		
		try { profesional = service.findById(profesionalId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		TipoMatricula tipoMatricula = TipoMatricula.valueOf(profesionalData.getTipoMatricula().toUpperCase());
		profesional.setNombre(profesionalData.getNombre());
		profesional.setTipoMatricula(tipoMatricula);
		profesional.setMatricula(profesionalData.getMatricula());
		
		try {profesional = service.update(profesional);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/activar")
	public ResponseEntity<Object> activarProfesional(@PathVariable("id") Long profesionalId){
		
		Profesional profesional = null;
		ProfesionalResponse profesionalResponse = null;
		
		try { profesional = service.findById(profesionalId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { profesional = service.activarProfesional(profesional);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { profesionalResponse = mapper.map(profesional, ProfesionalResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/profesionales/{id}/desactivar")
	public ResponseEntity<Object> desactivarProfesional(@PathVariable("id") Long profesionalId){
		
		Profesional profesional = null;
		
		try { profesional = service.findById(profesionalId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { service.desactivarProfesional(profesional);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
