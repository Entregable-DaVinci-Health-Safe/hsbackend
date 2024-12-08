package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.controller.request.visitamedica.VisitaMedicaRangoTiempo;
import healthSafe.dvds20222cg4hce.controller.request.visitamedica.VisitaMedicaRequest;
import healthSafe.dvds20222cg4hce.controller.response.visitamedica.VisitaMedicaResponse;
import healthSafe.dvds20222cg4hce.controller.response.visitamedica.VisitaMedicaWithDocumentsResponse;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.VisitaMedicaService;
import healthSafe.dvds20222cg4hce.utils.VisitaMedicaUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class VisitaMedicaControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private VisitaMedicaService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/visitasMedicas/all")
	public List<VisitaMedica> getListAll(){
		return service.list();
	}
	
	@GetMapping(path = "/historiasMedicas/{id}/visitasMedicas")
	public ResponseEntity<Object> getVisistasMedicasByHistoriaMedica(@PathVariable Long id){
		List<VisitaMedica> visitasMedicas = null;
		List<VisitaMedicaResponse> visitasMedicasResponse = null;
		
		try { visitasMedicas = service.getVisitasMedicasByHistoriaMedica(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {
			if(visitasMedicas == null) return new ResponseEntity<>("No hay visitas medicas asociadas a la historia medica", HttpStatus.OK);
			if(visitasMedicas.isEmpty()) return new ResponseEntity<>("No hay visitas medicas asociadas a la historia medica", HttpStatus.OK);
			visitasMedicasResponse = VisitaMedicaUtils.getListVisitaMedicaResponse(visitasMedicas);
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(visitasMedicasResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/historiasMedicas/{id}/visitasMedicasWithDocuments")
	public ResponseEntity<Object> getVisistasMedicasWithDocumentsByHistoriaMedica(@PathVariable Long id){
		List<VisitaMedica> visitasMedicas = null;
		List<VisitaMedicaWithDocumentsResponse> response = null;
		
		try { visitasMedicas = service.getVisitasMedicasWithDocumentsByHistoriaMedica(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = VisitaMedicaUtils.getListVisitaMedicaWithDocumentsResponse(visitasMedicas);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/visitasMedicas/timeRange")
	public ResponseEntity<Object> getVisitasMedicasByTimeRangeAndHistoriaMedica(
			@RequestParam(value="historiaMedicaId", required=true) Long historiaMedicaId, 
			@RequestParam(value="startDate", required=false) String startDate, 
			@RequestParam(value="lastDate", required=false) String lastDate,
			@RequestParam(value="giveLatest", required=true) Boolean giveLatest){
		
		List<VisitaMedica> visitasMedicas = null;
		List<VisitaMedicaResponse> visitasMedicasResponse = null;
		
		VisitaMedicaRangoTiempo rangoTiempo = VisitaMedicaRangoTiempo.builder()
												.historiaMedicaId(historiaMedicaId)
												.startDate(startDate)
												.lastDate(lastDate)
												.giveLatest(giveLatest)
												.build();
		
		try { visitasMedicas = service.getVisitasMedicasByHistoriaMedica(rangoTiempo.getHistoriaMedicaId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {
			visitasMedicas = service.filterVisitasMedicasByRangeTime(visitasMedicas, rangoTiempo);
			if(visitasMedicas.isEmpty()) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {}
		
		try {visitasMedicasResponse = VisitaMedicaUtils.getListVisitaMedicaResponse(visitasMedicas);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(visitasMedicasResponse, HttpStatus.CREATED);
			
	}
	
	@PostMapping(path = "/visitasMedicas")
	public ResponseEntity<VisitaMedicaResponse> createVisitaMedica(@RequestBody VisitaMedicaRequest datosVisita){
		VisitaMedica visitaMedica = null;
		VisitaMedicaResponse visitaResponse = null;
		
		try { visitaMedica = mapper.map(datosVisita, VisitaMedica.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try { 
			visitaMedica = service.save(visitaMedica);
			if(visitaMedica.getInstitucionSalud() != null) {
				visitaMedica.getInstitucionSalud().setProfesionales(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { visitaResponse = mapper.map(visitaMedica, VisitaMedicaResponse.class);} 
		catch(Exception e) {e.printStackTrace();}
	
		return new ResponseEntity<>(visitaResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/visitasMedicas/{id}")
	public ResponseEntity<Object> updateVisitaMedica(@PathVariable("id") Long visitaId, @RequestBody VisitaMedicaRequest datosVisita){
		VisitaMedica visitaMedica = null;
		VisitaMedica newVisitaMedica = null;
		VisitaMedicaResponse visitaResponse = null;
		
		try { visitaMedica = service.findById(visitaId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { newVisitaMedica = mapper.map(datosVisita, VisitaMedica.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try { 
			visitaMedica = service.update(visitaMedica, newVisitaMedica);
			if(visitaMedica.getInstitucionSalud() != null) {
				visitaMedica.getInstitucionSalud().setProfesionales(null);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { visitaResponse = mapper.map(visitaMedica, VisitaMedicaResponse.class);} 
		catch(Exception e) {e.printStackTrace();}
	
		return new ResponseEntity<>(visitaResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/visitasMedicas/{id}/activar")
	public ResponseEntity<Object> activarVisitaMedica(@PathVariable("id") Long visitaId){
		VisitaMedica visita  = null;
		VisitaMedicaResponse visitaResponse = null;
		
		try { visita = service.findById(visitaId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { visita = service.activarVisitaMedica(visita);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { visitaResponse = mapper.map(visita, VisitaMedicaResponse.class);} 
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(visitaResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/visitasMedicas/{id}/desactivar")
	public ResponseEntity<Object> desactivarVisitaMedica(@PathVariable("id") Long visitaId){
		VisitaMedica visita  = null;
		
		try { visita = service.findById(visitaId);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { service.desactivarVisitaMedica(visita);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	

}
