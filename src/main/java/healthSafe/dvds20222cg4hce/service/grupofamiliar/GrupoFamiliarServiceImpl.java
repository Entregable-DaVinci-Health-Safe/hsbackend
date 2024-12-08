package healthSafe.dvds20222cg4hce.service.grupofamiliar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.MailSmtpDetails;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.GrupoFamiliarRepository;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;
import healthSafe.dvds20222cg4hce.service.MailSmtpService;
import healthSafe.dvds20222cg4hce.service.autorizacion.AutorizacionComponentService;
import healthSafe.dvds20222cg4hce.service.usuario.UsuarioService;
import healthSafe.dvds20222cg4hce.utils.GrupoFamiliarUtils;
import healthSafe.dvds20222cg4hce.utils.HistoriaMedicaUtils;
import healthSafe.dvds20222cg4hce.utils.UsuarioUtils;

@Service
public class GrupoFamiliarServiceImpl implements GrupoFamiliarService{
	
	private GrupoFamiliarRepository repository;
	private GrupoNotificacionService grupoNotificacionService;
	private HistoriaMedicaService historiaService;
	private UsuarioService usuarioService;
	private AutorizacionComponentService autorizacionComponentService;
	private MailSmtpService mailService;
	
	@Autowired
	public GrupoFamiliarServiceImpl(final GrupoFamiliarRepository repository,
			final HistoriaMedicaService historiaService,
			final MailSmtpService mailService,
			final UsuarioService usuarioService,
			final AutorizacionComponentService autorizacionComponentService,
			final GrupoNotificacionService grupoNotificacionService) {
		this.repository = repository;
		this.historiaService = historiaService;
		this.mailService = mailService;
		this.usuarioService = usuarioService;
		this.autorizacionComponentService = autorizacionComponentService;
		this.grupoNotificacionService = grupoNotificacionService;
	}

	@Override
	public List<GrupoFamiliar> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<GrupoFamiliar> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public GrupoFamiliar save(GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		if(repository.existsByNombre(grupoFamiliar.getNombre())) {
			throw new BusinessException("Ya existe un grupo familiar con este nombre");
		}
		
		List<HistoriaMedica> historias = new ArrayList<HistoriaMedica>();
		if(grupoFamiliar.getHistorias() != null) {
			historias = HistoriaMedicaUtils.getListHistoriasMedicas(grupoFamiliar.getHistorias());
		}
		
		List<Usuario> usuariosAdmins = new ArrayList<Usuario>();
		if(grupoFamiliar.getAdmins() != null) {
			usuariosAdmins = UsuarioUtils.getListUsuarios(grupoFamiliar.getAdmins());
		}
		
		List<Usuario> usuariosNormales = new ArrayList<Usuario>();
		if(grupoFamiliar.getUsuarios() != null) {
			usuariosNormales = UsuarioUtils.getListUsuarios(grupoFamiliar.getUsuarios());
		}
		
		List<GrupoNotificacion> grupoNotificaciones = new ArrayList<GrupoNotificacion>();
		if(grupoFamiliar.getGruposNotificaciones() != null) {
			grupoNotificaciones = GrupoFamiliarUtils.getListGruposNotificaciones(grupoFamiliar.getGruposNotificaciones());
		}
		
		grupoFamiliar = GrupoFamiliar.builder()
				.nombre(grupoFamiliar.getNombre())
				.codigo(grupoFamiliar.getCodigo())
				.fechaCreado(grupoFamiliar.getFechaCreado())
				.historias(historias)
				.admins(usuariosAdmins)
				.usuarios(usuariosNormales)
				.gruposNotificaciones(grupoNotificaciones)
				.activo(grupoFamiliar.getActivo())
				.build();
		
		return repository.save(grupoFamiliar);
	}

	@Override
	public GrupoFamiliar update(GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		List<HistoriaMedica> historias = new ArrayList<HistoriaMedica>();
		if(grupoFamiliar.getHistorias() != null) {
			historias = HistoriaMedicaUtils.getListHistoriasMedicas(grupoFamiliar.getHistorias());
		}
		
		List<Usuario> usuariosAdmins = new ArrayList<Usuario>();
		if(grupoFamiliar.getAdmins() != null) {
			usuariosAdmins = UsuarioUtils.getListUsuarios(grupoFamiliar.getAdmins());
		}
		
		List<Usuario> usuariosNormales = new ArrayList<Usuario>();
		if(grupoFamiliar.getUsuarios() != null) {
			usuariosNormales = UsuarioUtils.getListUsuarios(grupoFamiliar.getUsuarios());
		}
		
		List<GrupoNotificacion> grupoNotificaciones = new ArrayList<GrupoNotificacion>();
		if(grupoFamiliar.getGruposNotificaciones() != null) {
			grupoNotificaciones = GrupoFamiliarUtils.getListGruposNotificaciones(grupoFamiliar.getGruposNotificaciones());
		}
		
		grupoFamiliar = GrupoFamiliar.builder()
				.id(grupoFamiliar.getId())
				.nombre(grupoFamiliar.getNombre())
				.codigo(grupoFamiliar.getCodigo())
				.fechaCreado(grupoFamiliar.getFechaCreado())
				.historias(historias)
				.admins(usuariosAdmins)
				.usuarios(usuariosNormales)
				.gruposNotificaciones(grupoNotificaciones)
				.activo(grupoFamiliar.getActivo())
				.build();
		
		return repository.save(grupoFamiliar);
	}

	@Override
	public GrupoFamiliar findById(Long id) throws BusinessException {
		
		Optional<GrupoFamiliar> grupoFamiliarOptional = repository.findById(id);
		if(grupoFamiliarOptional.isPresent()) return grupoFamiliarOptional.get();
		throw new BusinessException("No se pudo encontrar el grupo familiar con el id: " + id);
	}

	@Override
	public GrupoFamiliar findByCodigo(String codigo) throws BusinessException {
		
		Optional<GrupoFamiliar> grupoFamiliarOptional = repository.findByCodigo(codigo);
		if(grupoFamiliarOptional.isPresent()) return grupoFamiliarOptional.get();
		throw new BusinessException("No se pudo encontrar el grupo familiar con el codigo: " + codigo);
	}
	
	@Override
	public List<GrupoFamiliar> findByHistoriaMedicaId(Long historiaMedicaId) throws BusinessException {
		
		List<GrupoFamiliar> gruposFamiliares = repository.findByHistoriasId(historiaMedicaId);
		if(!gruposFamiliares.isEmpty()) {return gruposFamiliares;}
		throw new BusinessException("No hay grupos familiares asociadas a la historia medica");
	}

	@Override
	public GrupoFamiliar addHistoriaMedica(Long usuarioId, GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		estaActivo(grupoFamiliar);
		HistoriaMedica historia = getHistoriaMedicaByPacienteId(usuarioId);
		grupoFamiliar.addHistoriaMedica(historia);
		return update(grupoFamiliar);
	}
	
	@Override
	public GrupoFamiliar removeHistoriaMedica(Long usuarioId, GrupoFamiliar grupoFamiliar)
			throws BusinessException {
		
		estaActivo(grupoFamiliar);
		grupoFamiliar.removeHistoriaMedica(getHistoriaMedicaByPacienteId(usuarioId));
		return update(grupoFamiliar);
	}
	
	@Override
	public GrupoFamiliar addAdmin(String usuarioMail, GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		estaActivo(grupoFamiliar);
		Usuario usuario = getUsuarioByMail(usuarioMail);
		
		if(!esUsuarioEnGrupo(usuario, grupoFamiliar)) {
			throw new BusinessException("El usuario no pertenece al grupo"); 
		}
		
		if(!tieneRolAdmin(usuario)) {
			AutorizacionComponent autorizacionComponent = autorizacionComponentService.getRolGrupoFamiliarAdmin();
			usuario = usuarioService.addAutorizacionComponent(usuario, autorizacionComponent);
		}
		
		grupoFamiliar.addAdmin(usuario);
		return update(grupoFamiliar);
	}

	@Override
	public GrupoFamiliar removeAdmin(String usuarioMail, GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		estaActivo(grupoFamiliar);
		Usuario usuario = getUsuarioByMail(usuarioMail);
		
		if(!existenMasAdmins(grupoFamiliar)) {
			throw new BusinessException(grupoFamiliar.getNombre() + " debe tener un admin");
		}
		
		if(!esAdminEnVariosGrupos(usuario)) {
			AutorizacionComponent autorizacionComponent = autorizacionComponentService.getRolGrupoFamiliarAdmin();
			usuario = usuarioService.removeAutorizacionComponent(usuario, autorizacionComponent);
		}
		
		grupoFamiliar.removeAdmin(usuario);
		return update(grupoFamiliar);
	}
	
	@Override
	public GrupoFamiliar addUsuario(String usuarioMail, GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		estaActivo(grupoFamiliar);
		Usuario usuario = getUsuarioByMail(usuarioMail);
		if(!tieneRolUsuario(usuario)) {
			AutorizacionComponent autorizacionComponent = autorizacionComponentService.getRolGrupoFamiliarUsuario();
			usuario = usuarioService.addAutorizacionComponent(usuario, autorizacionComponent);
		}
		grupoFamiliar.addUsuario(usuario);
		addHistoriaMedica(usuario.getId(), grupoFamiliar);
		return update(grupoFamiliar);
	}

	@Override
	public GrupoFamiliar removeUsuario(String usuarioMail, GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		estaActivo(grupoFamiliar);
		
		Usuario usuario = getUsuarioByMail(usuarioMail);
		if(!esUsuarioEnVariosGrupos(usuario)) {
			AutorizacionComponent autorizacionComponent = autorizacionComponentService.getRolGrupoFamiliarUsuario();
			usuario = usuarioService.removeAutorizacionComponent(usuario, autorizacionComponent);
		}
		
		grupoFamiliar.removeUsuario(usuario);
		
		if(tieneRolAdmin(usuario) && esAdminEnGrupo(usuario, grupoFamiliar)) {
			grupoFamiliar = removeAdmin(usuarioMail, grupoFamiliar);
		}
		removeHistoriaMedica(usuario.getId(), grupoFamiliar);
		return update(grupoFamiliar);
	}
	
	@Override
	public String compartirGrupoFamiliar(String codigo, String mail)  throws Exception{
		if(mail != null) {
			getUsuarioByMail(mail);
		}else {
			throw new BusinessException("El usuario es obligatorio");
		}
		
		if(codigo != null) {
			findByCodigo(codigo);
		}else {
			throw new BusinessException("El grupo familiar es obligatorio");
		}
		
		MailSmtpDetails mailDetails = MailSmtpDetails.builder()
				.recipient(mail)
				.subject("INVITACIÓN GRUPO FAMILIAR")
				.msgBody("Te invitaron a formar parte de un grupo familiar. Puedes unirte con el siguiente código: " + codigo)
				.build();

		mailService.sendMail(mailDetails);
		
		return "Invitación enviada con exito";
	}
	
	@Override
	public void sendNotificacion(GrupoNotificacion grupoNotificacion) throws BusinessException {
		
		Usuario usuario = null;
		GrupoFamiliar grupoFamiliar = null;
	
		if(grupoNotificacion.getUsuario() != null) {
			usuario = getUsuarioByMail(grupoNotificacion.getUsuario().getMail());
		}else {
			throw new BusinessException("El usuario es obligatorio");
		}
		
		if(grupoNotificacion.getGrupoFamiliar() != null) {
			grupoFamiliar = findByCodigo(grupoNotificacion.getGrupoFamiliar().getCodigo());
		}else {
			throw new BusinessException("El grupo familiar es obligatorio");
		}
		if(esUsuarioEnGrupo(usuario, grupoFamiliar)) {
			throw new BusinessException("Ya perteneces a este grupo familiar");
		}
		
		if(grupoNotificacionService.existsByUsuarioId(usuario.getId())) {
			throw new BusinessException("Ya existe una notificacion con este usuario");
		}
		
		grupoNotificacion.setGrupoFamiliar(grupoFamiliar);
		grupoNotificacion.setUsuario(usuario);
		
		grupoNotificacion = grupoNotificacionService.save(grupoNotificacion);
		
		grupoFamiliar.addNotificacion(grupoNotificacion);
		usuario.addNotificacion(grupoNotificacion);
		
	}
	
	@Override
	public GrupoFamiliar aceptarNotificacion(Long notificacionId, GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		GrupoNotificacion grupoNotificacion = grupoNotificacionService.findById(notificacionId);
		HistoriaMedica historia = getHistoriaMedicaByPacienteId(grupoNotificacion.getUsuario().getId());
		
		/*grupoNotificacion.setAceptada(true);
		grupoNotificacionService.update(grupoNotificacion);*/
		
		grupoFamiliar = addUsuario(grupoNotificacion.getUsuario().getMail(), grupoFamiliar);
		
		deleteNotificacion(notificacionId);
		
		return grupoFamiliar;
	}
	
	@Override
	public void deleteNotificacion(Long notificacionId) throws BusinessException {
		
		GrupoNotificacion grupoNotificacion = grupoNotificacionService.findById(notificacionId);
		GrupoFamiliar grupoFamiliar = grupoNotificacion.getGrupoFamiliar();
		Usuario usuario = grupoNotificacion.getUsuario();
		
		grupoNotificacionService.delete(grupoNotificacion);
		
		grupoFamiliar.removeNotificacion(grupoNotificacion);
		usuario.removeNotificacion(grupoNotificacion);
		
	}

	@Override
	public void delete(GrupoFamiliar grupoFamiliar) {
		
		grupoFamiliar.removeAllAdmins();
		grupoFamiliar.removeAllUsuarios();
		grupoFamiliar.removeAllHistoriasMedicas();
		repository.delete(grupoFamiliar);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	
	@Override
	public void activarGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		grupoFamiliar.setActivo(true);
		update(grupoFamiliar);
	}
	
	@Override
	public void desactivarGrupoFamiliar(GrupoFamiliar grupoFamiliar) throws BusinessException {
		
		grupoFamiliar.setActivo(false);
		update(grupoFamiliar);
	}

	@Override
	public long count() {
		
		return repository.count();
	}
	
	private HistoriaMedica getHistoriaMedica(Long historiaMedicaId) throws BusinessException {
		return historiaService.findById(historiaMedicaId);
	}
	
	private HistoriaMedica getHistoriaMedicaByPacienteId(Long usuarioId) throws BusinessException {
		return historiaService.findByPacienteId(usuarioId);
	}
	
	private Usuario getUsuarioById(Long id) throws BusinessException {
		return usuarioService.findById(id);
	}
	
	@Override
	public Usuario getUsuarioByMail(String mail) throws BusinessException {
		return usuarioService.findByMail(mail);
	}
	
	private Boolean tieneRolAdmin(Usuario usuario) {
		return usuario.getAutorizacionesComponentes()
			.stream()
			.anyMatch(uac -> uac.getCodigo().equals("GF_ADMIN"));
	}
	
	private Boolean tieneRolUsuario(Usuario usuario) {
		return usuario.getAutorizacionesComponentes()
			.stream()
			.anyMatch(uac -> uac.getCodigo().equals("GF_USER"));
	}
	
	private Boolean existenMasAdmins(GrupoFamiliar grupoFamiliar) {
		return (grupoFamiliar.getAdmins().size()-1) >= 1;
	}
	
	private Boolean esAdminEnVariosGrupos(Usuario usuario) {
		List<GrupoFamiliar> gruposAdmins =  repository.findByAdmins(usuario);
		return gruposAdmins.size() > 1;
	}
	
	private Boolean esUsuarioEnVariosGrupos(Usuario usuario) {
		List<GrupoFamiliar> gruposAdmins =  repository.findByUsuarios(usuario);
		return gruposAdmins.size() > 1;
	}
	
	private Boolean esUsuarioEnGrupo(Usuario usuario, GrupoFamiliar grupoFamiliar) {
		return grupoFamiliar.getUsuarios().stream().anyMatch(usr -> usr.getId() == usuario.getId());
	}
	
	@Override
	public Boolean esAdminEnGrupo(Usuario usuario, GrupoFamiliar grupoFamiliar) {
		return grupoFamiliar.getAdmins().stream().anyMatch(usr -> usr.getId() == usuario.getId());
	}
	
	private void estaActivo(GrupoFamiliar grupoFamiliar) throws BusinessException{
		if(!grupoFamiliar.getActivo()) throw new BusinessException("El grupo familiar no esta activo");
	}

	@Override
	public Boolean existsGrupoByName(String nombre) throws BusinessException {
		
		return repository.existsByNombre(nombre);
	}

}
