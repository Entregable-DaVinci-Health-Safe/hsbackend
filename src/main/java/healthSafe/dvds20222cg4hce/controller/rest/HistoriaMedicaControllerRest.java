package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.jwtutils.JwtFilter;
import healthSafe.dvds20222cg4hce.controller.request.HistoriaMedicaRequest;
import healthSafe.dvds20222cg4hce.controller.response.HistoriaMedicaResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioResponse;
import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;
import healthSafe.dvds20222cg4hce.utils.CalendarioUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class HistoriaMedicaControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private HistoriaMedicaService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@GetMapping(path = "/historiasMedicas/all")
	public List<HistoriaMedica> getListAll(){
		return service.list();
	}
	
	@GetMapping(path = "/historiasMedicas/{id}")
	public ResponseEntity<Object> getHistoriaMedicaById(@PathVariable Long id){
		HistoriaMedica historia = null;
		HistoriaMedicaResponse historiaResponse = null;
		
		try { historia = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { historiaResponse = mapper.map(historia, HistoriaMedicaResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(historiaResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/historiasMedicas/{id}/calendarios")
	public ResponseEntity<Object> getCalendariosByHistoriaMedica(@PathVariable Long id){
		List<Calendario> calendarios = null;
		List<CalendarioResponse> calendariosResponse = null;
		
		try { calendarios = service.getCalendariosByHistoriaMedicaId(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		calendariosResponse = CalendarioUtils.getListCalendariosResponse(calendarios);
		return new ResponseEntity<>(calendariosResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/historiasMedicas/usuarios/")
	public ResponseEntity<Object> getHistoriaMedicaByPaciente(
			HttpServletRequest httpServlet){
		String token = httpServlet.getHeader("Authorization").substring(7);
		HistoriaMedica historia = null;
		HistoriaMedicaResponse response = null;
		
		try { historia = service.findByPacienteMail(jwtFilter.getMailFromToken(token));}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { response = mapper.map(historia, HistoriaMedicaResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/historiasMedicas")
	public ResponseEntity<Object> createHistoriaMedica(@RequestBody HistoriaMedicaRequest historiaData){
		HistoriaMedica historiaMedica = null;
		HistoriaMedicaResponse historiaResponse = null;
		
		try { historiaMedica = mapper.map(historiaData, HistoriaMedica.class);}
		catch(Exception e) { e.printStackTrace();}
		
		try { historiaMedica = service.save(historiaMedica);} 
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { historiaResponse = mapper.map(historiaMedica, HistoriaMedicaResponse.class);} 
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(historiaResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/historiasMedicas/{id}/calendarios/personalSalud")
	public ResponseEntity<Object> createCalendarioPersonalSalud(@PathVariable Long id){
		HistoriaMedica historia = null;
		HistoriaMedicaResponse historiaResponse = null;
		
		try { historia = service.findById(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { historia = service.createCalendarioPersonalSalud(historia);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { historiaResponse = mapper.map(historia, HistoriaMedicaResponse.class);} 
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(historiaResponse, HttpStatus.CREATED);
	}
	
}
