package healthSafe.dvds20222cg4hce.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import healthSafe.dvds20222cg4hce.domain.historia.MailSmtpDetails;
import healthSafe.dvds20222cg4hce.service.MailSmtpService;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class MailControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private MailSmtpService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@PostMapping(path = "/sendMail")
	public ResponseEntity<String> sendMail(@RequestBody MailSmtpDetails mailDetails){
		
		String response = null;
		
		try { response = service.sendMail(mailDetails);} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error al enviar el mail", HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
}
