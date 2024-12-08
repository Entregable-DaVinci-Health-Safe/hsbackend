package healthSafe.dvds20222cg4hce.controller.rest;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.controller.request.TurnoRequest;
import healthSafe.dvds20222cg4hce.controller.request.TurnoUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.response.TurnoResponse;
import healthSafe.dvds20222cg4hce.domain.historia.Turno;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.TurnoService;
import healthSafe.dvds20222cg4hce.utils.DateUtils;
import healthSafe.dvds20222cg4hce.utils.TurnoUtils;


@CrossOrigin("*")
@RestController
public class TurnoControllerRest extends HistorialMedicoAppRest {

    @Autowired
	private TurnoService service;

    @GetMapping(path = "/historiasMedicas/{id}/turnos")
	public ResponseEntity<Object> getTurnosByHistoriaMedica(@PathVariable Long id){
		List<Turno> turnos = null;
		List<TurnoResponse> response = null;
		
		try { turnos = service.getTurnosByHistoriaMedica(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {
			response = TurnoUtils.getListResponseFromListDomain(turnos);
			if(response.isEmpty()) return new ResponseEntity<>("No hay turnos asociadas a la historia medica", HttpStatus.OK);
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

    @PostMapping(path = "/turnos")
    public ResponseEntity<TurnoResponse> createTurno(@RequestBody TurnoRequest request){
		Turno turno = null;
		TurnoResponse response = null;
		
		try { turno = TurnoUtils.getTurnoFromRequest(request);}
		catch(Exception e) { e.printStackTrace(); }
		
		try { turno = service.save(turno);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { response = TurnoUtils.getResponseFromDomain(turno);} 
		catch(Exception e) {e.printStackTrace();}
	
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

    @PutMapping(path = "/turnos/{id}")
	public ResponseEntity<Object> updateTurno(@PathVariable Long id,
			@RequestBody TurnoUpdateRequest turnoUpdateRequest){
		Turno turno = null;
		TurnoResponse response = null;
		
		try {turno = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { 
            turno.setInstitucion(turnoUpdateRequest.getInstitucion());
            turno.setFechaInicio(DateUtils.getFechaTimestampFromDatetime(turnoUpdateRequest.getFechaInicio()));
            turno.setDireccion(turnoUpdateRequest.getDireccion());
            turno.setProfesional(turnoUpdateRequest.getProfesional());
			turno.setEspecialidad(turnoUpdateRequest.getEspecialidad());
            turno.setInstitucion(turnoUpdateRequest.getInstitucion());
            turno.setMotivo(turnoUpdateRequest.getMotivo());
            turno.setGoogleId(turnoUpdateRequest.getGoogleId());

        }catch(Exception e) { e.printStackTrace(); }
		
		try {turno = service.update(turno);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { response = TurnoUtils.getResponseFromDomain(turno);} 
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

    @PutMapping(path = "/turnos/{id}/activar")
	public ResponseEntity<Object> activarTurno(@PathVariable("id") Long id){
		Turno turno = null;
		TurnoResponse response = null;
		
		try { turno = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { turno = service.activarTurno(turno);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { response = TurnoUtils.getResponseFromDomain(turno);} 
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/turnos/{id}/desactivar")
	public ResponseEntity<Object> desactivarTurno(@PathVariable("id") Long id){
		Turno turno  = null;
		
		try { turno = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { service.desactivarTurno(turno);}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}

}
