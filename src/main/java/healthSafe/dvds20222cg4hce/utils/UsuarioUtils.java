package healthSafe.dvds20222cg4hce.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import healthSafe.dvds20222cg4hce.Constantes;
import healthSafe.dvds20222cg4hce.controller.response.ContactoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionComponentResponse;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioPacienteResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioResponse;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;

public final class UsuarioUtils {
	private static DateFormat fechaFormat = new SimpleDateFormat(Constantes.FORMATO_FECHA);
	
	private UsuarioUtils() {}
	
	public static UsuarioPaciente mergeUsuariosPacientes(UsuarioPaciente usuarioModificar, UsuarioPaciente newUsuario) {
		usuarioModificar = (UsuarioPaciente) mergeUsuarios(usuarioModificar, newUsuario);
		
		return usuarioModificar;
	}
	
	public static UsuarioProfesional mergeUsuariosProfesionales(UsuarioProfesional usuarioModificar, UsuarioProfesional newUsuario) {
		usuarioModificar = (UsuarioProfesional) mergeUsuarios(usuarioModificar, newUsuario);
		usuarioModificar.setMatricula(newUsuario.getMatricula());
		return usuarioModificar;
	}
	
	private static Usuario mergeUsuarios(Usuario usuarioModificar, Usuario newUsuario) {
		usuarioModificar.setNombre(newUsuario.getNombre());
		usuarioModificar.setApellido(newUsuario.getApellido());
		usuarioModificar.setFechaNacimiento(newUsuario.getFechaNacimiento());
		
		return usuarioModificar;
	}
	
	public static Usuario getUsuario(Usuario usuarioRequest) {
		if(usuarioRequest instanceof UsuarioPaciente) {
			return getUsuarioPaciente((UsuarioPaciente) usuarioRequest);
		}else if(usuarioRequest instanceof UsuarioProfesional) {
			return getUsuarioProfesional((UsuarioProfesional) usuarioRequest);
		}
		
		return null;
	}
	
	public static UsuarioPaciente getUsuarioPaciente(UsuarioPaciente pacienteRequest) {
		List<DireccionUsuario> direcciones = new ArrayList<DireccionUsuario>();
		if(pacienteRequest.getDirecciones() != null) {
			direcciones = DireccionUtils.getListDireccionesUsuarios(pacienteRequest.getDirecciones());
		}
		
		List<ContactoUsuario> contactos = new ArrayList<ContactoUsuario>();
		if(pacienteRequest.getContactos() != null) {
			contactos = ContactoUtils.getListContactosUsuarios(pacienteRequest.getContactos());
		}
		
		List<VerificacionCuenta> verificaciones = new ArrayList<VerificacionCuenta>();
		if(pacienteRequest.getVerificaciones() != null) {
			verificaciones = pacienteRequest.getVerificaciones();
		}
		
		List<AutorizacionComponent> autorizacionesComponentes = null;
		if(pacienteRequest.getAutorizacionesComponentes() != null) {
			autorizacionesComponentes = AutorizacionUtils.getListAutorizacionComponent(pacienteRequest.getAutorizacionesComponentes());
		}
		
		List<GrupoNotificacion> grupoNotificaciones = new ArrayList<GrupoNotificacion>();
		if(pacienteRequest.getGruposNotificaciones() != null) {
			grupoNotificaciones = GrupoFamiliarUtils.getListGruposNotificaciones(pacienteRequest.getGruposNotificaciones());
		}
		
		return UsuarioPaciente.builder()
									.id(pacienteRequest.getId())
									.imgPerfil(pacienteRequest.getImgPerfil())
									.documento(pacienteRequest.getDocumento())
									.nombre(pacienteRequest.getNombre())
									.apellido(pacienteRequest.getApellido())
									.direcciones(direcciones)
									.contactos(contactos)
									.mail(pacienteRequest.getMail())
									.password(pacienteRequest.getPassword())
									.genero(pacienteRequest.getGenero())
									.activo(pacienteRequest.getActivo())
									.fechaNacimiento(pacienteRequest.getFechaNacimiento())
									.verificaciones(verificaciones)
									.autorizacionesComponentes(autorizacionesComponentes)
									.gruposNotificaciones(grupoNotificaciones)
									.build();
	}
	
	public static UsuarioProfesional getUsuarioProfesional(UsuarioProfesional profesionalRequest) {
		List<DireccionUsuario> direcciones = new ArrayList<DireccionUsuario>();
		if(profesionalRequest.getDirecciones() != null) {
			direcciones = DireccionUtils.getListDireccionesUsuarios(profesionalRequest.getDirecciones());
		}
		
		List<ContactoUsuario> contactos = new ArrayList<ContactoUsuario>();
		if(profesionalRequest.getContactos() != null) {
			contactos = ContactoUtils.getListContactosUsuarios(profesionalRequest.getContactos());
		}
		
		List<AutorizacionComponent> autorizacionesComponentes = null;
		if(profesionalRequest.getAutorizacionesComponentes() != null) {
			autorizacionesComponentes = AutorizacionUtils.getListAutorizacionComponent(profesionalRequest.getAutorizacionesComponentes());
		}
		
		List<GrupoNotificacion> grupoNotificaciones = new ArrayList<GrupoNotificacion>();
		if(profesionalRequest.getGruposNotificaciones() != null) {
			grupoNotificaciones = GrupoFamiliarUtils.getListGruposNotificaciones(profesionalRequest.getGruposNotificaciones());
		}
		
		return UsuarioProfesional.builder()
									.id(profesionalRequest.getId())
									.imgPerfil(profesionalRequest.getImgPerfil())
									.documento(profesionalRequest.getDocumento())
									.nombre(profesionalRequest.getNombre())
									.apellido(profesionalRequest.getApellido())
									.matricula(profesionalRequest.getMatricula())
									.direcciones(direcciones)
									.contactos(contactos)
									.mail(profesionalRequest.getMail())
									.genero(profesionalRequest.getGenero())
									.activo(profesionalRequest.getActivo())
									.fechaNacimiento(profesionalRequest.getFechaNacimiento())
									.autorizacionesComponentes(autorizacionesComponentes)
									.gruposNotificaciones(grupoNotificaciones)
									.build();
	}
	
	public static UsuarioResponse getUsuarioResponse(Usuario usuarioRequest) {
		if(usuarioRequest instanceof UsuarioPaciente) {
			return getUsuarioPacienteResponse((UsuarioPaciente) usuarioRequest);
		}else if(usuarioRequest instanceof UsuarioProfesional) {
			return getUsuarioProfesionalResponse((UsuarioProfesional) usuarioRequest);
		}
		
		return null;
	}
	
	public static UsuarioPacienteResponse getUsuarioPacienteResponse(UsuarioPaciente pacienteRequest) {
		String fechaStr = fechaFormat.format(pacienteRequest.getFechaNacimiento());
		List<DireccionResponse> direcciones = new ArrayList<DireccionResponse>();
		if(pacienteRequest.getDirecciones() != null) {
			direcciones = DireccionUtils.getListDireccionUsuarioResponse(pacienteRequest.getDirecciones());
		}
		
		List<ContactoResponse> contactos = new ArrayList<ContactoResponse>();
		if(pacienteRequest.getContactos() != null) {
			contactos = ContactoUtils.getListContactosUsuariosResponse(pacienteRequest.getContactos());
		}
		
		List<AutorizacionComponentResponse> autorizacionesResponse = null;
		if(pacienteRequest.getAutorizacionesComponentes() != null) {
			autorizacionesResponse = AutorizacionUtils.getListAutorizacionComponentResponse(pacienteRequest.getAutorizacionesComponentes());
		}
		
		return UsuarioPacienteResponse.builder()
									.imgPerfil(pacienteRequest.getImgPerfil())
									.documento(pacienteRequest.getDocumento())
									.nombre(pacienteRequest.getNombre())
									.apellido(pacienteRequest.getApellido())
									.direcciones(direcciones)
									.contactos(contactos)
									.mail(pacienteRequest.getMail())
									.genero(pacienteRequest.getGenero().getDescripcion())
									.activo(pacienteRequest.getActivo())
									.fechaNacimiento(fechaStr)
									.autorizacionesComponentes(autorizacionesResponse)
									.build();
	}
	
	public static UsuarioProfesionalResponse getUsuarioProfesionalResponse(UsuarioProfesional profesionalRequest) {
		String fechaStr = fechaFormat.format(profesionalRequest.getFechaNacimiento());
		List<DireccionResponse> direcciones = new ArrayList<DireccionResponse>();
		if(profesionalRequest.getDirecciones() != null) {
			direcciones = DireccionUtils.getListDireccionUsuarioResponse(profesionalRequest.getDirecciones());
		}
		
		List<ContactoResponse> contactos = new ArrayList<ContactoResponse>();
		if(profesionalRequest.getContactos() != null) {
			contactos = ContactoUtils.getListContactosUsuariosResponse(profesionalRequest.getContactos());
		}
		
		List<AutorizacionComponentResponse> autorizacionesResponse = null;
		if(profesionalRequest.getAutorizacionesComponentes() != null) {
			autorizacionesResponse = AutorizacionUtils.getListAutorizacionComponentResponse(profesionalRequest.getAutorizacionesComponentes());
		}
		
		return UsuarioProfesionalResponse.builder()
									.imgPerfil(profesionalRequest.getImgPerfil())
									.documento(profesionalRequest.getDocumento())
									.nombre(profesionalRequest.getNombre())
									.apellido(profesionalRequest.getApellido())
									.matricula(profesionalRequest.getMatricula())
									.direcciones(direcciones)
									.contactos(contactos)
									.mail(profesionalRequest.getMail())
									.genero(profesionalRequest.getGenero().getDescripcion())
									.activo(profesionalRequest.getActivo())
									.fechaNacimiento(fechaStr)
									.autorizacionesComponentes(autorizacionesResponse)
									.build();
	}
	
	public static List<Usuario> getListUsuarios(List<Usuario> usuarioRequest){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarioRequest.stream().forEach(usr -> usuarios.add(getUsuario(usr)));
		return usuarios;
	}
	
	public static List<UsuarioResponse> getListUsuariosResponse(List<Usuario> usuarioRequest){
		List<UsuarioResponse> usuariosResponse = new ArrayList<UsuarioResponse>();
		usuarioRequest.stream().forEach(usr -> usuariosResponse.add(getUsuarioResponse(usr)));
		return usuariosResponse;
	}

}
