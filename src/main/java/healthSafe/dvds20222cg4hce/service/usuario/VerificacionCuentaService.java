package healthSafe.dvds20222cg4hce.service.usuario;

import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface VerificacionCuentaService {
	
	void generateVerificacion(Usuario usuario) throws BusinessException, Exception;
	VerificacionCuenta save(VerificacionCuenta verificacion) throws BusinessException;
	
	VerificacionCuenta update(VerificacionCuenta verificacion) throws BusinessException;
	VerificacionCuenta setFechaValidacion(VerificacionCuenta verificacion)throws BusinessException;
	
	VerificacionCuenta findById(Long id) throws BusinessException;
	VerificacionCuenta findByCodigoAndUsuarioMail(String codigo, String usuarioMail) throws BusinessException;
	
	void delete(VerificacionCuenta verificacion) throws BusinessException;
	void delete(Long id) throws BusinessException;
}
