package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.controller.request.calendario.VacunaRequest;
import healthSafe.dvds20222cg4hce.controller.response.calendario.VacunaResponse;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.service.calendario.VacunaService;
import healthSafe.dvds20222cg4hce.utils.CalendarioUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class VacunaControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private VacunaService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@GetMapping(path = "/vacunas")
	public List<VacunaResponse> getListAll(){
		List<VacunaResponse> vacunasResponse = CalendarioUtils.getListVacunaResponse(service.listVacunas());
		return vacunasResponse;
	}
	
	@PostMapping(path = "/vacunas")
	public ResponseEntity<VacunaResponse> createVacuna(@RequestBody VacunaRequest datosVacuna){
		Vacuna vacuna = null;
		VacunaResponse vacunaResponse = null;
		
		try { vacuna = mapper.map(datosVacuna, Vacuna.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try { vacuna = service.save(vacuna);}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { vacunaResponse = mapper.map(vacuna, VacunaResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(vacunaResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/vacunas/byTipoCalendario/{tipoCalendario}")
	public ResponseEntity<Object> getVacunasByTipoCalendario(@PathVariable String tipoCalendario){
		List<Vacuna> vacunas = null;
		List<VacunaResponse> vacunasResponse = null;
		
		try { vacunas = service.findAllByTipoCalendario(tipoCalendario);}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { vacunasResponse = CalendarioUtils.getListVacunaResponse(vacunas);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(vacunasResponse, HttpStatus.CREATED);
		
	}
	
	@GetMapping(path = "/vacunas/byRangoEdad/{id}")
	public ResponseEntity<Object> getVacunasByRangoEdad(@PathVariable Long id){
		List<Vacuna> vacunas = null;
		List<VacunaResponse> vacunasResponse = null;
		
		try { vacunas = service.findAllByRangoEdad(id);}
		catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { vacunasResponse = CalendarioUtils.getListVacunaResponse(vacunas);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(vacunasResponse, HttpStatus.CREATED);
		
	}
}
