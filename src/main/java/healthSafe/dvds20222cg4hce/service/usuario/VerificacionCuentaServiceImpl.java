package healthSafe.dvds20222cg4hce.service.usuario;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.historia.MailSmtpDetails;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.domain.usuario.VerificacionCuenta;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.usuario.VerificacionCuentaRepository;
import healthSafe.dvds20222cg4hce.service.MailSmtpService;
import healthSafe.dvds20222cg4hce.utils.DateUtils;
import healthSafe.dvds20222cg4hce.utils.PasswordGenerator;

@Service
public class VerificacionCuentaServiceImpl implements VerificacionCuentaService{
	
	private VerificacionCuentaRepository repository;
	private MailSmtpService mailService;
	
	@Autowired
	public VerificacionCuentaServiceImpl(final VerificacionCuentaRepository repository,
			final MailSmtpService mailService) {
		this.repository = repository;
		this.mailService = mailService;
	}
	
	@Override
	public void generateVerificacion(Usuario usuario) throws BusinessException, Exception {
		
		
		VerificacionCuenta verificacion = VerificacionCuenta.builder()
				.codigo(PasswordGenerator.codigoVerificacion())
				.fechaGenerado(DateUtils.getFechaNowTimestamp())
				.usuario(usuario)
				.build();
		
		verificacion = save(verificacion);
		usuario.addVerificacion(verificacion);
		
		
		MailSmtpDetails mailDetails = MailSmtpDetails.builder()
				.recipient(usuario.getMail())
				.subject("VERIFICACIÓN DE LA CUENTA")
				.msgBody("Su código de verificación es: " + verificacion.getCodigo())
				.build();
		
		mailService.sendMail(mailDetails);
		
	}
	
	@Override
	public VerificacionCuenta save(VerificacionCuenta verificacion) throws BusinessException {
		
		if(verificacion.getId() == null) return repository.save(verificacion);
		throw new BusinessException("No se puede crear une verificacion que ya fue creada");
	}

	@Override
	public VerificacionCuenta update(VerificacionCuenta verificacion) throws BusinessException {
		
		if(verificacion.getId() != null) return repository.save(verificacion);
		throw new BusinessException("No se puede actualizar une verificacion que no fue creada");
	}
	
	@Override
	public VerificacionCuenta setFechaValidacion(VerificacionCuenta verificacion) throws BusinessException {
		
		verificacion = VerificacionCuenta.builder()
				.id(verificacion.getId())
				.codigo("")
				.fechaGenerado(verificacion.getFechaGenerado())
				.fechaValidado(DateUtils.getFechaNowTimestamp())
				.usuario(verificacion.getUsuario())
				.build();
		return update(verificacion);
	}

	@Override
	public VerificacionCuenta findById(Long id) throws BusinessException {
		
		Optional<VerificacionCuenta> verificacionOptional = repository.findById(id);
		if(verificacionOptional.isPresent()) return verificacionOptional.get();
		throw new BusinessException("No se pudo encontrar la verificacion con el id: " + id);
	}
	
	@Override
	public VerificacionCuenta findByCodigoAndUsuarioMail(String codigo, String usuarioMail) throws BusinessException {
		
		Optional<VerificacionCuenta> verificacionOptional = repository.findByCodigoAndUsuarioMail(codigo, usuarioMail);
		if(verificacionOptional.isPresent()) return verificacionOptional.get();
		throw new BusinessException("No se pudo encontrar la verificacion con el codigo: " + codigo + " y usuario: " + usuarioMail);
	}

	@Override
	public void delete(VerificacionCuenta verificacion) throws BusinessException {
		
		repository.delete(verificacion);
	}

	@Override
	public void delete(Long id) throws BusinessException {
		
		repository.deleteById(id);
	}



}
