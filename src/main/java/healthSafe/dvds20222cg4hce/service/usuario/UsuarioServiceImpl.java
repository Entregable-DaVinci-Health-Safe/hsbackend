package healthSafe.dvds20222cg4hce.service.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.historia.MailSmtpDetails;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import healthSafe.dvds20222cg4hce.domain.usuario.Genero;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.exception.InactiveUserException;
import healthSafe.dvds20222cg4hce.repository.usuario.UsuarioPacienteRepository;
import healthSafe.dvds20222cg4hce.repository.usuario.UsuarioProfesionalRepository;
import healthSafe.dvds20222cg4hce.repository.usuario.UsuarioRepository;
import healthSafe.dvds20222cg4hce.service.MailSmtpService;
import healthSafe.dvds20222cg4hce.service.autorizacion.AutorizacionComponentService;
import healthSafe.dvds20222cg4hce.utils.AutorizacionUtils;
import healthSafe.dvds20222cg4hce.utils.ContactoUtils;
import healthSafe.dvds20222cg4hce.utils.DireccionUtils;
import healthSafe.dvds20222cg4hce.utils.PasswordGenerator;
import healthSafe.dvds20222cg4hce.utils.VerificacionUtils;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	private UsuarioRepository usuarioRepository;
	private UsuarioPacienteRepository pacienteRepository;
	private UsuarioProfesionalRepository profesionalRepository;
	private DireccionService direccionService;
	private ContactoService contactoService;
	private PasswordEncoder bcryptEncoder;
	private AutorizacionComponentService autorizacionService;
	private VerificacionCuentaService verificacionService;
	private MailSmtpService mailService;
	
	@Autowired
	public UsuarioServiceImpl(final UsuarioRepository usuarioRepository,
								final UsuarioPacienteRepository pacienteRepository,
								final UsuarioProfesionalRepository profesionalRepository,
								final DireccionService direccionService,
								final ContactoService contactoService,
								final PasswordEncoder bcryptEncoder,
								final AutorizacionComponentService autorizacionService,
								final VerificacionCuentaService verificacionService,
								final MailSmtpService mailService) {
		
		this.usuarioRepository = usuarioRepository;
		this.pacienteRepository = pacienteRepository;
		this.profesionalRepository = profesionalRepository;
		this.direccionService = direccionService;
		this.contactoService = contactoService;
		this.bcryptEncoder = bcryptEncoder;
		this.autorizacionService = autorizacionService;
		this.verificacionService = verificacionService;
		this.mailService = mailService;
	}
	
	@Override
	public List<Usuario> listUsuario() {
		
		return usuarioRepository.findAll();
	}
	
	@Override
	public List<UsuarioPaciente> listPaciente(){
		return pacienteRepository.findAll();
	}
	
	@Override
	public List<UsuarioProfesional> listProfesional(){
		return profesionalRepository.findAll();
	}
	
	@Override
	public List<Genero> listGeneros(){
		return Genero.getGeneros();
	}

	@Override
	public Page<Usuario> listUsuario(Pageable pageable) {
		
		return usuarioRepository.findAll(pageable);
	}
	
	@Override
	public Page<UsuarioPaciente> listPaciente(Pageable pageable) {
		
		return pacienteRepository.findAll(pageable);
	}
	
	@Override
	public Page<UsuarioProfesional> listProfesional(Pageable pageable) {
		
		return profesionalRepository.findAll(pageable);
	}

	@Override
	public UsuarioPaciente save(UsuarioPaciente paciente) throws BusinessException, IllegalArgumentException, InactiveUserException, Exception {
		
		
		if(existMail(paciente.getMail())) { 
			
			if(isActive(paciente.getMail())) {
				throw new InactiveUserException("El usuario esta registrado pero no activo");
			}
			
			throw new BusinessException("El mail ya esta registrado");
		}
		
		List<AutorizacionComponent> autorizacionesComponentes = new ArrayList<AutorizacionComponent>();
		if(paciente.getAutorizacionesComponentes() != null) {
			autorizacionesComponentes = AutorizacionUtils.getListAutorizacionComponent(paciente.getAutorizacionesComponentes());
		}else {
			autorizacionesComponentes.add(autorizacionService.getAutorizacionUsuarioBase());
		}
		
		List<ContactoUsuario> contactos = new ArrayList<ContactoUsuario>();
		if(paciente.getContactos() != null) contactos = ContactoUtils.getListContactosUsuarios(paciente.getContactos());
		
		List<DireccionUsuario> direcciones = new ArrayList<DireccionUsuario>();
		if(paciente.getDirecciones() != null) direcciones = DireccionUtils.getListDireccionesUsuarios(paciente.getDirecciones());
		
		List<VerificacionCuenta> verificaciones = new ArrayList<VerificacionCuenta>();
		if(paciente.getVerificaciones() != null) verificaciones = VerificacionUtils.getListVerificaciones(paciente.getVerificaciones());
		
		List<GrupoNotificacion> grupoNotificaciones = new ArrayList<GrupoNotificacion>();
		if(paciente.getGruposNotificaciones() != null) {
			grupoNotificaciones = paciente.getGruposNotificaciones();
		}
		
		paciente = UsuarioPaciente.builder()
				.imgPerfil(paciente.getImgPerfil())
				.documento(paciente.getDocumento())
				.nombre(paciente.getNombre())
				.apellido(paciente.getApellido())
				.fechaNacimiento(paciente.getFechaNacimiento())
				.mail(paciente.getMail())
				.password(bcryptEncoder.encode(paciente.getPassword()))
				.genero(paciente.getGenero())
				.contactos(contactos)
				.direcciones(direcciones)
				.activo(paciente.getActivo())
				.autorizacionesComponentes(autorizacionesComponentes)
				.verificaciones(verificaciones)
				.gruposNotificaciones(grupoNotificaciones)
				.build();
		
		paciente = pacienteRepository.save(paciente);
		
		verificacionService.generateVerificacion(paciente);
		
		return paciente;
	
	}
	
	@Override
	public UsuarioPaciente saveUserFromGoogle(UsuarioPaciente paciente) throws BusinessException, IllegalArgumentException, InactiveUserException, Exception {
		
	
		List<AutorizacionComponent> autorizacionesComponentes = new ArrayList<AutorizacionComponent>();
		if(paciente.getAutorizacionesComponentes() != null) {
			autorizacionesComponentes = AutorizacionUtils.getListAutorizacionComponent(paciente.getAutorizacionesComponentes());
		}else {
			autorizacionesComponentes.add(autorizacionService.getAutorizacionUsuarioBase());
		}
		
		List<ContactoUsuario> contactos = new ArrayList<ContactoUsuario>();
		if(paciente.getContactos() != null) contactos = ContactoUtils.getListContactosUsuarios(paciente.getContactos());
		
		List<DireccionUsuario> direcciones = new ArrayList<DireccionUsuario>();
		if(paciente.getDirecciones() != null) direcciones = DireccionUtils.getListDireccionesUsuarios(paciente.getDirecciones());
		
		List<VerificacionCuenta> verificaciones = new ArrayList<VerificacionCuenta>();
		if(paciente.getVerificaciones() != null) verificaciones = VerificacionUtils.getListVerificaciones(paciente.getVerificaciones());
		
		paciente = UsuarioPaciente.builder()
				.imgPerfil(paciente.getImgPerfil())
				.documento(paciente.getDocumento())
				.nombre(paciente.getNombre())
				.apellido(paciente.getApellido())
				.fechaNacimiento(paciente.getFechaNacimiento())
				.mail(paciente.getMail())
				.password(bcryptEncoder.encode(paciente.getPassword()))
				.genero(paciente.getGenero())
				.contactos(contactos)
				.direcciones(direcciones)
				.activo(paciente.getActivo())
				.autorizacionesComponentes(autorizacionesComponentes)
				.verificaciones(verificaciones)
				.build();
		
		return pacienteRepository.save(paciente);
	
	}

	@Override
	public UsuarioPaciente update(UsuarioPaciente pacienteUpdate) throws BusinessException {
		
		if(pacienteUpdate.getId() != null) return pacienteRepository.save(pacienteUpdate);
		throw new BusinessException("No se puede actualizar un paciente que no fue creado");
	}
	
	@Override
	public UsuarioProfesional save(UsuarioProfesional profesional) throws BusinessException {
		
		if(profesional.getId() == null) return profesionalRepository.save(profesional);
		throw new BusinessException("No se puede crear un profesional que no ha sido creado");
	}

	@Override
	public UsuarioProfesional update(UsuarioProfesional profesionalUpdate) throws BusinessException {
		
		if(profesionalUpdate.getId() != null) return profesionalRepository.save(profesionalUpdate);
		throw new BusinessException("No se puede actualizar un profesional que no fue creado");
	
	}
	
	@Override
	public void setVerificacionValidacion(VerificacionCuenta verificacion) throws BusinessException {
		;
		verificacion = verificacionService.setFechaValidacion(verificacion);
	}
	
	@Override
	public Usuario createDireccion(Usuario usuario, DireccionUsuario direccion) throws BusinessException{
	
		direccion.setUsuario(usuario);
		usuario.addDireccion(direccionService.save(direccion));
		return usuario;
	}
	
	@Override
	public Usuario updateDireccion(Usuario usuario, Long direccionId, DireccionUsuario direccion) throws BusinessException, ClassCastException {
		
		DireccionUsuario direccionModificar = (DireccionUsuario) direccionService.findById(direccionId);
		direccion.setUsuario(usuario);
		
		direccionModificar = direccionService.update(direccionModificar, direccion);
		usuario.updateDireccion(direccionModificar);
		
		return usuario;
	}
	
	@Override
	public Usuario removeDireccion(Usuario usuario, Long direccionId) throws BusinessException {
		
		Direccion direccion = direccionService.findById(direccionId);
		usuario.removeDireccion(direccion);
		direccionService.delete(direccion);
		return usuario;
	}
	
	@Override
	public Usuario createContacto(Usuario usuario, ContactoUsuario contacto) throws BusinessException{
		contacto.setUsuario(usuario);
		usuario.addContacto(contactoService.save(contacto));
		return usuario;
	}
	
	@Override
	public Usuario updateContacto(Usuario usuario, Long contactoId, ContactoUsuario contacto) throws BusinessException, ClassCastException {
		
		ContactoUsuario contactoModificar = (ContactoUsuario) contactoService.findById(contactoId);
		contacto.setUsuario(usuario);
		
		contactoModificar = contactoService.update(contactoModificar, contacto);
		usuario.updateContacto(contactoModificar);
		
		return usuario;
	}
	
	@Override
	public Usuario removeContacto(Usuario usuario, Long contactoId) throws BusinessException {
		
		Contacto contacto = contactoService.findById(contactoId);
		usuario.removeContacto(contacto);
		contactoService.delete(contacto);
		return usuario;
	}

	@Override
	public Usuario findById(Long id) throws BusinessException {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if(usuarioOptional.isPresent()) return usuarioOptional.get();
		throw new BusinessException("No se pudo encontrar el paciente con el id: " + id);
	}
	
	@Override
	public Usuario findByMail(String mail) throws BusinessException {
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findByMail(mail);
		if(usuarioOptional.isPresent()) return usuarioOptional.get();
		throw new BusinessException("No se pudo encontrar el paciente con el mail: " + mail);
	}
	
	@Override
	public Usuario findByLogin(String mail, String password) throws BusinessException{
		Optional<Usuario> usuarioOptional = usuarioRepository.findByMailAndPassword(mail, password);
		if(usuarioOptional.isPresent()) return usuarioOptional.get();
		throw new BusinessException("No se pudo encontrar el usuario");
	}
	
	@Override
	public void createVerificacion(Usuario usuario) throws BusinessException, Exception {
		
		verificacionService.generateVerificacion(usuario);
	}
	
	@Override
	public VerificacionCuenta getVerificacionByCodigoAndUsuarioMail(String codigo, String usuarioMail)
			throws BusinessException {
		
		return verificacionService.findByCodigoAndUsuarioMail(codigo, usuarioMail);
	}
	
	@Override
	public Boolean existMail(String mail) throws BusinessException{
		return usuarioRepository.existsByMail(mail);
	}
	
	@Override
	public void obtenerNewPassword(String mail) throws BusinessException, Exception {
		
		String newPassword = PasswordGenerator.passwordGenerator();
		Usuario usuario = findByMail(mail);
		usuario.setPassword(bcryptEncoder.encode(newPassword));
		usuario = update(usuario);
		
		MailSmtpDetails mailDetails = MailSmtpDetails.builder()
				.recipient(usuario.getMail())
				.subject("NUEVA CONTRASEÑA")
				.msgBody("Su nueva contraseña es: " + newPassword)
				.build();

		mailService.sendMail(mailDetails);

	}
	
	@Override
	public Usuario updatePassword(String password, Usuario usuarioRequest) throws BusinessException{
		
		usuarioRequest.setPassword(bcryptEncoder.encode(password));
		
		return update(usuarioRequest);
	}
	
	@Override
	public Boolean comparePassword(String passwordComparar, String passwordGuardada) throws BusinessException{
		return bcryptEncoder.matches(passwordComparar, passwordGuardada);
	}
	
	@Override
	public Usuario addAutorizacionComponent(Usuario usuario, AutorizacionComponent autorizacionComponent)
			throws BusinessException {
		
		
		usuario.addAutorizacionComponent(autorizacionComponent);
		
		return  update(usuario);
	} 
	
	@Override
	public Usuario removeAutorizacionComponent(Usuario usuario, AutorizacionComponent autorizacionComponent)
			throws BusinessException {
		
		usuario.removeAutorizacionComponent(autorizacionComponent);
		
		return update(usuario);
	}
	
	
	@Override
	public void delete(Usuario usuario) {
		
		usuarioRepository.delete(usuario);
	}

	@Override
	public void delete(Long id) {
		
		usuarioRepository.deleteById(id);
		
	}

	@Override
	public long count() {
		
		return usuarioRepository.count();
	}

	@Override
	public Usuario activarUsuario(Usuario usuarioRequest) throws BusinessException {
		
		Usuario usuario = usuarioRequest;
		usuario.setActivo(true);
		
		return update(usuario);
	}
	
	@Override
	public void desactivarUsuario(Usuario usuarioRequest) throws BusinessException {
		
		Usuario usuario = usuarioRequest;
		usuario.setActivo(false);
		
		usuario = update(usuario);
	}
	
	private Boolean isActive(String mail) {
		return usuarioRepository.existsByMailAndActivoFalse(mail);
	}

	@Override
	public Usuario update(Usuario usuarioUpdate) throws BusinessException {
		
		if(usuarioUpdate instanceof UsuarioPaciente) {
			usuarioUpdate = update((UsuarioPaciente)usuarioUpdate);
		}else if(usuarioUpdate instanceof UsuarioProfesional) {
			usuarioUpdate = update((UsuarioProfesional)usuarioUpdate);
		}
		return usuarioUpdate;
	}
}
