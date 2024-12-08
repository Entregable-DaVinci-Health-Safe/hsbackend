package healthSafe.dvds20222cg4hce.controller.rest;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import healthSafe.dvds20222cg4hce.service.GoogleService;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.calendar.model.Event;
import healthSafe.dvds20222cg4hce.controller.request.GoogleCalendarioGetListRequest;
import healthSafe.dvds20222cg4hce.controller.request.GoogleCalendarioPostRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioGoogleLoginRequest;
import healthSafe.dvds20222cg4hce.controller.response.JwtResponse;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;

@RestController
@CrossOrigin("*")
public class GoogleControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
    private GoogleService service;
	
	
    @GetMapping("/login/google")
    public RedirectView googleConnectionStatusHandler(HttpServletRequest request) throws Exception {
        return service.googleConnectionStatus(request);
    }

    @GetMapping(value = "/login/google", params = "code")
    public ResponseEntity<Object> oauth2Callback(@RequestParam("code") String code) {
    	
    	TokenResponse tokenResponse = null;
		try { tokenResponse = service.oauth2Callback(code);} 
		catch (GeneralSecurityException | IOException e) { e.printStackTrace();}
		catch(BusinessException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

	@PostMapping("/login/google")
	public ResponseEntity<Object> postLoginGoogle(@RequestBody UsuarioGoogleLoginRequest request) {
		Long historiaMedicaId = null;
		JwtResponse response = null;
		try{
			historiaMedicaId = service.saveUserFromGoogleToken(request.getTokenId());
			response = JwtResponse.builder()
									.token(request.getTokenId())
									.historiaMedicaId(historiaMedicaId)
									.build();
		}catch(BusinessException e){
			response = JwtResponse.builder()
									.token(request.getTokenId())
									.historiaMedicaId(Long.valueOf(0))
									.build();
			e.printStackTrace();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	


    @GetMapping("/getListEventos")
    public ResponseEntity<List<Event>> getListCalendarEventsHandler(
    		@RequestBody GoogleCalendarioGetListRequest datosListEventos){

        List<Event> eventos = null;
		try {
			eventos = service.getListCalendarEvents(datosListEventos);
		} catch (IOException e) {
			e.printStackTrace();
		}

        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }
    
    @GetMapping("/getEventos/{eventoId}")
    public ResponseEntity<Object> getCalendarEventHandler(
    		@PathVariable String eventoId){

        Event evento = null;
		try {
			evento = service.getCalendarEvent(eventoId);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @PostMapping("/addEventos")
    public ResponseEntity<Object> createGoogleMeetingHandler(
    		@RequestBody GoogleCalendarioPostRequest datosEvento){
    	Event evento = null;
    	
    	try { evento = service.createCalenderEvent(datosEvento);
        }catch(IOException e) {	
        }catch(BusinessException e) {
        	e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
        }
        
    	return new ResponseEntity<>(evento, HttpStatus.CREATED);

    }
    
    @PutMapping("/updateEventos/{eventoId}")
    public ResponseEntity<Object> updateGoogleMeetingHandler(
    		@PathVariable String eventoId, @RequestBody GoogleCalendarioPostRequest datosEvento) {
    	
    	Event evento = null;
    	
    	try { evento = service.updateGoogleMeeting(eventoId, datosEvento);}
    	catch(IOException e) {
    		e.printStackTrace();
        	return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
    	}
    	
    	
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEventos/{eventoId}")
    public ResponseEntity<Event> deleteGoogleMeetingHandler(@PathVariable String eventoId){
        
    	try {service.deleteGoogleMeeting(eventoId);}
    	catch(IOException e) { e.printStackTrace(); }
    	
    	return new ResponseEntity<>(null , HttpStatus.OK);
    }
  
}
