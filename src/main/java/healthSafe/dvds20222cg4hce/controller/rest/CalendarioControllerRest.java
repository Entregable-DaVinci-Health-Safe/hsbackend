package healthSafe.dvds20222cg4hce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.controller.request.calendario.CalendarioVacunaRequest;
import healthSafe.dvds20222cg4hce.controller.request.calendario.RemoveVacunaUsuarioRequest;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioResponse;
import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.service.calendario.CalendarioService;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class CalendarioControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private CalendarioService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@PostMapping(path = "/calendarios/{id}/vacunas")
	public ResponseEntity<Object> addVacunaInCalendario(@PathVariable Long id,
			@RequestBody CalendarioVacunaRequest datosCalendarioVacuna){
		
		Calendario calendario = null;
		CalendarioEdadVacunaLink calendarioEdadVacuna = null;
		CalendarioResponse calendarioResponse = null;
		
		try { calendarioEdadVacuna = mapper.map(datosCalendarioVacuna, CalendarioEdadVacunaLink.class);
		}catch(Exception e) {e.printStackTrace();}
		
		try { calendario = service.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { 
			calendario = service.addVacuna(calendario, calendarioEdadVacuna);
		}catch(Exception e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {calendarioResponse = mapper.map(calendario, CalendarioResponse.class); }
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(calendarioResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "calendarios/{id}/vacunas")
	public ResponseEntity<CalendarioResponse> removeVacunaAplicadaInCalendario(@PathVariable Long id,
			@RequestBody RemoveVacunaUsuarioRequest datosCalendarioVacuna){
		
		Calendario calendario = null;
		CalendarioResponse calendarioResponse = null;
		
		try { calendario = service.findById(id);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { 
			calendario = service.removeVacuna(calendario, 
					datosCalendarioVacuna.getRangoEdadId(),
					datosCalendarioVacuna.getVacunaAplicadaId());
		}catch(Exception e) { e.printStackTrace();}
		
		try {calendarioResponse = mapper.map(calendario, CalendarioResponse.class); }
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(calendarioResponse, HttpStatus.CREATED);
	}
	
}
