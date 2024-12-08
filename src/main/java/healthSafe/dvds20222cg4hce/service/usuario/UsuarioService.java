package healthSafe.dvds20222cg4hce.service.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.domain.autorizacion.AutorizacionComponent;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import healthSafe.dvds20222cg4hce.domain.usuario.Genero;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.exception.InactiveUserException;

public interface UsuarioService{

	List<Usuario> listUsuario();
	List<UsuarioPaciente> listPaciente();
	List<UsuarioProfesional> listProfesional();
	List<Genero> listGeneros();
	
	Page<Usuario> listUsuario(Pageable pageable);
	Page<UsuarioPaciente> listPaciente(Pageable pageable);
	Page<UsuarioProfesional> listProfesional(Pageable pageable);
	
	Usuario update(Usuario usuarioUpdate) throws BusinessException;;
	
	UsuarioPaciente save(UsuarioPaciente paciente) throws BusinessException, Exception;
	UsuarioPaciente saveUserFromGoogle(UsuarioPaciente paciente) throws BusinessException, Exception;
	UsuarioPaciente update(UsuarioPaciente pacienteUpdate) throws BusinessException;
	
	UsuarioProfesional save(UsuarioProfesional profesional) throws BusinessException, InactiveUserException;
	UsuarioProfesional update(UsuarioProfesional profesionalUpdate) throws BusinessException;
	
	void createVerificacion(Usuario usuario) throws BusinessException, Exception;
	void  setVerificacionValidacion(VerificacionCuenta verificacion)throws BusinessException;
	Usuario activarUsuario(Usuario usuario)throws BusinessException;
	
	Usuario createDireccion(Usuario usuario, DireccionUsuario direccion) throws BusinessException;
	Usuario updateDireccion(Usuario usuario, Long direccionId, DireccionUsuario direccion) throws BusinessException;
	Usuario removeDireccion(Usuario usuario, Long direccionId) throws BusinessException;
	
	Usuario createContacto(Usuario usuario, ContactoUsuario contacto) throws BusinessException;
	Usuario updateContacto(Usuario usuario, Long contactoId, ContactoUsuario contacto) throws BusinessException;
	Usuario removeContacto(Usuario usuario, Long contactoId) throws BusinessException;

	Usuario findById(Long id) throws BusinessException;
	Usuario findByMail(String mail) throws BusinessException;
	Usuario findByLogin(String mail, String password) throws BusinessException;
	
	VerificacionCuenta getVerificacionByCodigoAndUsuarioMail(String codigo, String usuarioMail) throws BusinessException;;
	
	Boolean existMail(String mail) throws BusinessException;
	
	Usuario updatePassword(String password, Usuario usuario) throws BusinessException;
	Boolean comparePassword(String passwordComparar, String passwordGuardada) throws BusinessException;
	void obtenerNewPassword(String mail)throws BusinessException, Exception;
	
	Usuario addAutorizacionComponent(Usuario usuario, AutorizacionComponent autorizacionComponent) throws BusinessException;
	Usuario removeAutorizacionComponent(Usuario usuario, AutorizacionComponent autorizacionComponent) throws BusinessException;
	
	void desactivarUsuario(Usuario usuario) throws BusinessException;
	void delete(Usuario usuario);
	void delete(Long id);
	
	long count();
}
