package healthSafe.dvds20222cg4hce.controller.rest;

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
import org.springframework.web.bind.annotation.RestController;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarAdminRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarCompartirRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarIngresarRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarRemoveUsuarioRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.response.GrupoFamiliarResponse;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.jwtutils.JwtFilter;
import healthSafe.dvds20222cg4hce.service.grupofamiliar.GrupoFamiliarService;
import healthSafe.dvds20222cg4hce.utils.GrupoFamiliarUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class GrupoFamiliarControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private GrupoFamiliarService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@GetMapping(path = "/gruposFamiliares/{id}")
	public ResponseEntity<Object> getGrupoFamilir(@PathVariable Long id){
		
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/historiasMedicas/{id}/gruposFamiliares")
	public ResponseEntity<Object> getGruposFamiliaresByHistoriaMedica(@PathVariable Long id){
		List<GrupoFamiliar> gruposFamiliares = null;
		List<GrupoFamiliarResponse> response = null;
		
		try { gruposFamiliares = service.findByHistoriaMedicaId(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {response = GrupoFamiliarUtils.getListGruposFamiliaresResponse(gruposFamiliares);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PostMapping(path = "/gruposFamiliares")
	public ResponseEntity<Object> createGrupoFamiliar(HttpServletRequest httpServlet, @RequestBody GrupoFamiliarRequest grupoFamiliarData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { grupoFamiliar = mapper.map(grupoFamiliarData, GrupoFamiliar.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		try { grupoFamiliar = service.save(grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try { grupoFamiliar = service.addUsuario(jwtFilter.getMailFromToken(token), grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try { grupoFamiliar = service.addAdmin(jwtFilter.getMailFromToken(token), grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/gruposFamiliares/{id}")
	public ResponseEntity<Object> updateGrupoFamiliar(@PathVariable Long id, @RequestBody GrupoFamiliarUpdateRequest grupoFamiliarData){
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { 
			grupoFamiliar = service.findById(id); 
			grupoFamiliar.setNombre(grupoFamiliarData.getNombre());
			if(service.existsGrupoByName(grupoFamiliar.getNombre())) {
				throw new BusinessException("Ya existe un grupo con este nombre");
			}
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { 
			grupoFamiliar = service.update(grupoFamiliar);
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.CREATED);
	}
	
	@GetMapping(path = "/gruposFamiliares/compartirCodigo")
	public ResponseEntity<Object> shareCodigo(@RequestBody GrupoFamiliarCompartirRequest compartirData){
		
		String response = null;
		
		try { response = service.compartirGrupoFamiliar(compartirData.getCodigo(), compartirData.getMail());}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = "/gruposFamiliares/sendNotificacion")
	public ResponseEntity<Object> sendNotificacion(@RequestBody GrupoFamiliarIngresarRequest ingresarData){
		
		GrupoNotificacion grupoNotificacion = null;
		
		try { grupoNotificacion = mapper.map(ingresarData, GrupoNotificacion.class);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		try { service.sendNotificacion(grupoNotificacion); }
		catch(BusinessException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = "/gruposFamiliares/sendInvitacion")
	public ResponseEntity<Object> sendInvitacion(@RequestBody GrupoFamiliarIngresarRequest request){
		
		try { service.compartirGrupoFamiliar(request.getCodigo(), request.getUsuarioMail()); }
		catch(BusinessException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@PostMapping(path = "/gruposFamiliares/{id}/aceptarNotificacion/{notificacionId}")
	public ResponseEntity<Object> aceptarNotificacion(@PathVariable Long id, @PathVariable Long notificacionId){
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {grupoFamiliar = service.aceptarNotificacion(notificacionId, grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/gruposFamiliares/deleteNotificacion/{id}")
	public ResponseEntity<Object> deleteNotificacion(@PathVariable Long id){
		
		try { service.deleteNotificacion(id);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/gruposFamiliares/{id}/agregarAdmin")
	public ResponseEntity<Object> agregarAdmin(@PathVariable Long id,
			@RequestBody GrupoFamiliarAdminRequest agregarAdminData){
		
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupoFamiliar = service.addAdmin(agregarAdminData.getUsuarioMail(), grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/gruposFamiliares/{id}/removerAdmin")
	public ResponseEntity<Object> removerAdmin(@PathVariable Long id,
			@RequestBody GrupoFamiliarAdminRequest removerAdminData){
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupoFamiliar = service.removeAdmin(removerAdminData.getUsuarioMail(), grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/gruposFamiliares/{id}/removerUsuario")
	public ResponseEntity<Object> salirGrupoFamiliar(@PathVariable Long id, 
			@RequestBody GrupoFamiliarRemoveUsuarioRequest removerUsuarioData){
		GrupoFamiliar grupoFamiliar = null;
		GrupoFamiliarResponse grupoFamiliarResponse = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { grupoFamiliar = service.removeUsuario(removerUsuarioData.getUsuarioMail(), grupoFamiliar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {grupoFamiliarResponse = mapper.map(grupoFamiliar, GrupoFamiliarResponse.class); }
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(grupoFamiliarResponse, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/gruposFamiliares/{id}/activar")
	public ResponseEntity<Object> activarGrupoFamiliar(@PathVariable Long id){
		GrupoFamiliar grupoFamiliar = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { service.activarGrupoFamiliar(grupoFamiliar);
		} catch (BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@PutMapping(path = "/gruposFamiliares/{id}/desactivar")
	public ResponseEntity<Object> desactivarGrupoFamiliar(HttpServletRequest httpServlet, @PathVariable Long id){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		GrupoFamiliar grupoFamiliar = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { usuario = service.getUsuarioByMail(jwtFilter.getMailFromToken(token));}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) { e.printStackTrace();}
		
		try { 
			if(!service.esAdminEnGrupo(usuario, grupoFamiliar)) {
				throw new BusinessException("No eres admin del grupo familiar");
			}
			service.desactivarGrupoFamiliar(grupoFamiliar);
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path = "/gruposFamiliares/{id}/eliminar")
	public ResponseEntity<Object> eliminarGrupoFamiliar(HttpServletRequest httpServlet, @PathVariable Long id){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		GrupoFamiliar grupoFamiliar = null;
		
		try { grupoFamiliar = service.findById(id); }
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { usuario = service.getUsuarioByMail(jwtFilter.getMailFromToken(token));}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) { e.printStackTrace();}
		
		try { 
			if(!service.esAdminEnGrupo(usuario, grupoFamiliar)) {
				throw new BusinessException("No eres admin del grupo familiar");
			}
			service.delete(grupoFamiliar);
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
	}
	
	
}
