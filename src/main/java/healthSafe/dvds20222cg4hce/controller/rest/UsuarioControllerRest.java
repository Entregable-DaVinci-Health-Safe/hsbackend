package healthSafe.dvds20222cg4hce.controller.rest;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
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

import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioMailRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioPacienteRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioProfesionalRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioResetPasswordRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioUpdateGoogleRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioUpdateImagenRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioUpdatePasswordRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.VerificacionCodigoRequest;
import healthSafe.dvds20222cg4hce.controller.request.HistoriaMedicaRequest;
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoIdRequest;
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoRequest;
import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionIdRequest;
import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionRequest;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioImagenPerfilResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioPacienteResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioResponse;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import healthSafe.dvds20222cg4hce.domain.usuario.Genero;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.exception.InactiveUserException;
import healthSafe.dvds20222cg4hce.jwtutils.JwtFilter;
import healthSafe.dvds20222cg4hce.service.usuario.UsuarioService;
import healthSafe.dvds20222cg4hce.utils.DateUtils;
import healthSafe.dvds20222cg4hce.utils.ExceptionUtils;
import healthSafe.dvds20222cg4hce.utils.UsuarioUtils;
import ma.glasnost.orika.MapperFacade;

@CrossOrigin("*")
@RestController
public class UsuarioControllerRest extends HistorialMedicoAppRest{
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private MapperFacade mapper;
	
	@Autowired
	private HistoriaMedicaControllerRest historiaMedicaControllerRest;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@GetMapping(path = "/usuarios/all")
	public List<Usuario> getList(){
		return service.listUsuario();
	}
	
	@GetMapping(path = "/usuarios/pacientes/all")
	public List<UsuarioPaciente> getPacientes(){
		return service.listPaciente();
	}
	
	@GetMapping(path = "/usuarios/profesionales/all")
	public List<UsuarioProfesional> getProfesional(){
		return service.listProfesional();
	}
	
	@GetMapping(path = "/usuarios/pageable")
	public ResponseEntity<Page<UsuarioResponse>> getList(Pageable pageable){
		Page<UsuarioResponse> usuariosResponse = null;
		Page<Usuario> usuarios = null;
		
		try { usuarios = service.listUsuario(pageable); }
		catch(Exception e) { e.printStackTrace(); }
		
		try {
			usuariosResponse = usuarios.map(
					usuario -> mapper.map(usuario, UsuarioResponse.class)
			);
		}catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(usuariosResponse, HttpStatus.OK);	
	}
	
	@GetMapping(path = "/usuarios/pacientes")
	public ResponseEntity<Page<UsuarioPacienteResponse>> getListPaciente(Pageable pageable){
		Page<UsuarioPacienteResponse> pacientesResponse = null;
		Page<UsuarioPaciente> pacientes = null;
		
		try {pacientes = service.listPaciente(pageable);}
		catch(Exception e){e.printStackTrace();}
		
		try {
			pacientesResponse = pacientes.map(
					paciente -> mapper.map(paciente, UsuarioPacienteResponse.class)
			);
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(pacientesResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/usuarios/profesionales")
	public ResponseEntity<Page<UsuarioProfesionalResponse>> getListProfesionales(Pageable pageable){
		Page<UsuarioProfesionalResponse> profesionalesResponse = null;
		Page<UsuarioProfesional> profesionales = null;
		
		try {profesionales = service.listProfesional(pageable);}
		catch(Exception e){e.printStackTrace();}
		
		try {
			profesionalesResponse = profesionales.map(
					profesional -> mapper.map(profesional, UsuarioProfesionalResponse.class)
			);
		}catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(profesionalesResponse, HttpStatus.OK);
	}
	
	@GetMapping(path = "/usuarios")
	public ResponseEntity<Object> getUsuario(HttpServletRequest httpServlet){
		String token = httpServlet.getHeader("Authorization").substring(7);
		UsuarioResponse response = null;
		Usuario usuario = null;
		
		try { usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) { e.printStackTrace();}
		
		try { response = mapper.map(usuario, UsuarioResponse.class);
		}catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/generos")
	public List<Genero> getGeneros(){
		return service.listGeneros();
	}
	
	@GetMapping(path = "/usuarios/imagenPerfil")
	public ResponseEntity<Object> getImagenPerfil(HttpServletRequest httpServlet){
		String token = httpServlet.getHeader("Authorization").substring(7);
		UsuarioImagenPerfilResponse usuarioImagenResponse = null;
		Usuario usuario = null;
		
		try { usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) { e.printStackTrace();}
		
		usuarioImagenResponse = UsuarioImagenPerfilResponse.builder()
				.imgPerfil(usuario.getImgPerfil())
				.build();
		
		return new ResponseEntity<>(usuarioImagenResponse, HttpStatus.OK);
	}
	
	@PostMapping(path = "/register/pacientes")
	public ResponseEntity<Object> createPaciente(@RequestBody UsuarioPacienteRequest pacienteData){
		UsuarioPaciente paciente = null;
		UsuarioPacienteResponse response = null;
		
		try { paciente = mapper.map(pacienteData, UsuarioPaciente.class); }
		catch(Exception e) { e.printStackTrace(); }
		
		try { 
			paciente = service.save(paciente);	 
		}catch(BusinessException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}catch(InactiveUserException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}catch(ConstraintViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(ExceptionUtils.getContstraintViolationMessage(e), HttpStatus.BAD_REQUEST);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { response = mapper.map(paciente, UsuarioPacienteResponse.class); }
		catch(Exception e) { e.printStackTrace(); }
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/register/profesionales")
	public ResponseEntity<Object> createProfesional(@RequestBody UsuarioProfesionalRequest profesionalData){
		UsuarioProfesional profesional = null;
		UsuarioProfesionalResponse response = null;
		
		try {profesional = mapper.map(profesionalData, UsuarioProfesional.class);}
		catch(Exception e) {e.printStackTrace();}
		
		try {
			if(!service.existMail(profesional.getMail())) profesional = service.save(profesional);
		}catch(BusinessException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try {response = mapper.map(profesional, UsuarioProfesionalResponse.class);}
		catch(Exception e) {e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/usuarios/nuevaDireccion")
	public ResponseEntity<UsuarioResponse> addDireccion(HttpServletRequest httpServlet, @RequestBody DireccionRequest direccionData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		DireccionUsuario direccion = null;
		UsuarioResponse usuarioResponse = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {direccion = mapper.map(direccionData, DireccionUsuario.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {usuario = service.createDireccion(usuario, direccion);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/actualizarDireccion/{direccionId}")
	public ResponseEntity<Object> updateDireccion(HttpServletRequest httpServlet, 
			@PathVariable Long direccionId, @RequestBody DireccionRequest direccionData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		DireccionUsuario direccion = null;
		UsuarioResponse usuarioResponse = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {direccion = mapper.map(direccionData, DireccionUsuario.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {usuario = service.updateDireccion(usuario, direccionId, direccion);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(ClassCastException e) {
			e.printStackTrace();
			return new ResponseEntity<>("Ingresa el id correspondiente", HttpStatus.EXPECTATION_FAILED);
		}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping(path = "/usuarios/eliminarDireccion")
	public ResponseEntity<Object> removeDireccion(HttpServletRequest httpServlet, @RequestBody DireccionIdRequest direccionData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		UsuarioResponse usuarioResponse = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {usuario = service.removeDireccion(usuario, direccionData.getDireccionId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/usuarios/nuevoContacto")
	public ResponseEntity<UsuarioResponse> addContacto(HttpServletRequest httpServlet, @RequestBody ContactoRequest contactoData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		ContactoUsuario contacto = null;
		UsuarioResponse usuarioResponse = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {contacto = mapper.map(contactoData, ContactoUsuario.class);}
		catch(Exception e) { e.printStackTrace(); }
		
		try {usuario = service.createContacto(usuario, contacto);}
		catch(ConstraintViolationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping(path = "/usuarios/eliminarContacto")
	public ResponseEntity<Object> removeContacto(HttpServletRequest httpServlet, @RequestBody ContactoIdRequest contactoData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		UsuarioResponse usuarioResponse = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try {usuario = service.removeContacto(usuario, contactoData.getContactoId());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/pacientes")
	public ResponseEntity<UsuarioResponse> updatePaciente(HttpServletRequest httpServlet, 
									@RequestBody UsuarioUpdateRequest pacienteData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		UsuarioPaciente newPaciente = null;
		UsuarioPaciente pacienteModificar = null;
		UsuarioPacienteResponse response = null;
		
		try {pacienteModificar = (UsuarioPaciente) service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { newPaciente = mapper.map(pacienteData, UsuarioPaciente.class);}
		catch(Exception e) {e.printStackTrace();}
		
		pacienteModificar = UsuarioUtils.mergeUsuariosPacientes(pacienteModificar, newPaciente);
		
		try {newPaciente = service.update(pacienteModificar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { response = mapper.map(newPaciente, UsuarioPacienteResponse.class);		
		}catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/actualizarImagen")
	public ResponseEntity<UsuarioResponse> updateUsuarioImagen(HttpServletRequest httpServlet, 
			@RequestBody UsuarioUpdateImagenRequest pacienteData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		UsuarioResponse response = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		usuario.setImgPerfil(pacienteData.getImgPerfil());
		
		try {usuario = service.update(usuario);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { response = mapper.map(usuario, UsuarioResponse.class);		
		}catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/googleUpdate")
	public ResponseEntity<Object> updateUsuarioGoogleRequest(HttpServletRequest httpServlet, 
			@RequestBody UsuarioUpdateGoogleRequest request){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		UsuarioResponse response = null;
		
		try {
			usuario = service.findByMail(jwtFilter.getMailFromToken(token));
			usuario.setDocumento(request.getDocumento());
			usuario.setFechaNacimiento(DateUtils.getFechaTimestamp(request.getFechaNacimiento()));
			usuario.setGenero(Genero.valueOf(request.getGenero()));
		}catch(BusinessException e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);	
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try {usuario = service.update(usuario);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

		try {
			
			historiaMedicaControllerRest.createHistoriaMedica(HistoriaMedicaRequest.builder().mailPaciente(usuario.getMail()).build());
		}
		catch(Exception e) {e.printStackTrace();}
		
		try { response = mapper.map(usuario, UsuarioResponse.class);		
		}catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping(path = "/usuarios/profesionales/{id}")
	public ResponseEntity<UsuarioResponse> updateProfesional(@PathVariable Long id, 
									@RequestBody UsuarioUpdateRequest profesionalData){

		UsuarioProfesional newProfesional = null;
		UsuarioProfesional profesionalModificar = null;
		UsuarioProfesionalResponse response = null;
		
		try {profesionalModificar = (UsuarioProfesional) service.findById(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { newProfesional = mapper.map(profesionalData, UsuarioProfesional.class);}
		catch(Exception e) {e.printStackTrace();}
		
		profesionalModificar = UsuarioUtils.mergeUsuariosProfesionales(profesionalModificar, newProfesional);

		try {newProfesional = service.update(profesionalModificar);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
		
		try { response = mapper.map(newProfesional, UsuarioProfesionalResponse.class);		
		}catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/{id}/desactivar")
	public ResponseEntity<HttpStatus> updateEstadoUsuario(@PathVariable Long id){
		Usuario usuarioModificar = null;
		try {usuarioModificar = service.findById(id);}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		try { service.desactivarUsuario(usuarioModificar);
		}catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);			
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}

		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(path = "/usuarios/recuperarCuenta")
	public ResponseEntity<Object> getNewPassword(@RequestBody UsuarioMailRequest usuarioData){
		Usuario usuario = null;
		
		try {usuario = service.findByMail(usuarioData.getMail());}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { service.createVerificacion(usuario);}
		catch(BusinessException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		return new ResponseEntity<>("Mail con el codigo de verificacion enviado...", HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/resetPassword")
	public ResponseEntity<Object> resettingPassword(@RequestBody UsuarioResetPasswordRequest usuarioData){
		Usuario usuario = null;
		VerificacionCuenta verificacion = null;
		UsuarioResponse response = null;
		
		try {usuario = service.findByMail(usuarioData.getMail());}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { verificacion = service.getVerificacionByCodigoAndUsuarioMail(usuarioData.getCodigo().toUpperCase(), usuarioData.getMail());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try { service.setVerificacionValidacion(verificacion);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try { usuario = service.updatePassword(usuarioData.getPassword(), usuario);}
		catch(BusinessException e){
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try { response = mapper.map(usuario, UsuarioResponse.class);		
		}catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/usuarios/verificarCuenta")
	public ResponseEntity<Object> setVerificacion(
			@RequestBody VerificacionCodigoRequest verificacionData){
		
		Usuario usuario = null;
		UsuarioResponse usuarioResponse = null;
		VerificacionCuenta verificacion = null;
		
		try {usuario = service.findByMail(verificacionData.getMail());}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { verificacion = service.getVerificacionByCodigoAndUsuarioMail(verificacionData.getCodigo().toUpperCase(), verificacionData.getMail());}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try { service.setVerificacionValidacion(verificacion);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.EXPECTATION_FAILED);
		}
		
		try {usuario = service.activarUsuario(usuario);}
		catch(BusinessException e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {
			
			historiaMedicaControllerRest.createHistoriaMedica(HistoriaMedicaRequest.builder().mailPaciente(usuario.getMail()).build());
		}
		catch(Exception e) {e.printStackTrace();}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/usuarios/cambiarPassword")
	public ResponseEntity<Object> updatePassword(HttpServletRequest httpServlet, 
			@RequestBody UsuarioUpdatePasswordRequest usuarioData){
		String token = httpServlet.getHeader("Authorization").substring(7);
		Usuario usuario = null;
		UsuarioResponse usuarioResponse = null;
		
		try {usuario = service.findByMail(jwtFilter.getMailFromToken(token));}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try {
			if(service.comparePassword(usuarioData.getOldPassword(), usuario.getPassword())) {
				usuario = service.updatePassword(usuarioData.getNewPassword(), usuario);
			}else {
				throw new BusinessException("La password anterior no coincide");
			}
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
		try { usuarioResponse = mapper.map(usuario, UsuarioResponse.class);}
		catch(Exception e) { e.printStackTrace();}
		
		return new ResponseEntity<>(usuarioResponse, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/usuarios/{id}/delete")
	public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable Long id){
		try { 
			service.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) { return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);}
	}
	

}
