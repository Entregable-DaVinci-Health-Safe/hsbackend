package healthSafe.dvds20222cg4hce.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioLoginRequest;
import healthSafe.dvds20222cg4hce.controller.response.JwtResponse;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.jwtutils.TokenManager;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;
import healthSafe.dvds20222cg4hce.service.usuario.UsuarioService;
import ma.glasnost.orika.MapperFacade;

@RestController
@CrossOrigin("*")
public class JwtControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private HistoriaMedicaService historiaMedicaService;
	
	@Autowired
	private TokenManager tokenManager;
	
	@PostMapping("/login")
	public ResponseEntity<Object> createAuthenticationToken(@RequestBody UsuarioLoginRequest usuarioData){
		Usuario usuario = null;
		HistoriaMedica historiaMedica = null;
		JwtResponse tokenResponse = null;
		String jwtToken = null;
		
		try{
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							usuarioData.getMail(),
							usuarioData.getPassword()
					));
		}catch(DisabledException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}catch(BadCredentialsException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		
		try {
			usuario = usuarioService.findByMail(usuarioData.getMail());
			if(!usuario.getActivo()) throw new BusinessException("El usuario no esta activo");
			jwtToken = tokenManager.generateToken(usuario);
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) { e.printStackTrace(); }
		
		try { historiaMedica = historiaMedicaService.findByPacienteId(usuario.getId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {

			tokenResponse = JwtResponse.builder()
					.historiaMedicaId(historiaMedica.getId())
					.token(jwtToken)
					.build();
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<Object> expireToken(HttpServletRequest request){
		String authToken = request.getHeader("Authorization");
        final String token = authToken.substring(7);
        
        try {
        	
            return new ResponseEntity <>(null, HttpStatus.OK);
        }
        catch(Exception e) {e.printStackTrace();}
		return null;

	}
	
}
