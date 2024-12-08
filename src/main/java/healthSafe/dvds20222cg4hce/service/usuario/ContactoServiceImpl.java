package healthSafe.dvds20222cg4hce.service.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.contacto.ContactoInstitucionSaludRepository;
import healthSafe.dvds20222cg4hce.repository.contacto.ContactoProfesionalRepository;
import healthSafe.dvds20222cg4hce.repository.contacto.ContactoRepository;
import healthSafe.dvds20222cg4hce.repository.contacto.ContactoUsuarioRepository;

@Service
public class ContactoServiceImpl implements ContactoService{
	
	private ContactoRepository repository;
	private ContactoUsuarioRepository contactoUsuarioRepository;
	private ContactoProfesionalRepository contactoProfesionalRepository;
	private ContactoInstitucionSaludRepository contactoInstitucionSaludRepository;
	
	@Autowired
	public ContactoServiceImpl(final ContactoRepository repository,
								final ContactoUsuarioRepository contactoUsuarioRepository,
								final ContactoProfesionalRepository contactoProfesionalRepository,
								final ContactoInstitucionSaludRepository contactoInstitucionSaludRepository) {
		this.repository = repository;
		this.contactoUsuarioRepository = contactoUsuarioRepository;
		this.contactoProfesionalRepository = contactoProfesionalRepository;
		this.contactoInstitucionSaludRepository = contactoInstitucionSaludRepository;
	}

	@Override
	public List<Contacto> listContactos() {
		
		return repository.findAll();
	}
	
	@Override
	public List<ContactoUsuario> listContactosUsuarios() {
		
		return contactoUsuarioRepository.findAll();
	}

	@Override
	public List<ContactoProfesional> listContactosProfesionales() {
		
		return contactoProfesionalRepository.findAll();
	}

	@Override
	public List<ContactoInstitucionSalud> listContactosCentrosSalud() {
		
		return contactoInstitucionSaludRepository.findAll();
	}
	
	@Override
	public List<Contacto> listContactosByUserId(Long idUsuario) throws BusinessException {
		
		return null;
	}

	@Override
	public Page<Contacto> listContactos(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
	
	@Override
	public Page<ContactoUsuario> listContactosUsuarios(Pageable pageable) {
		
		return contactoUsuarioRepository.findAll(pageable);
	}

	@Override
	public Page<ContactoProfesional> listContactosProfesionales(Pageable pageable) {
		
		return contactoProfesionalRepository.findAll(pageable);
	}

	@Override
	public Page<ContactoInstitucionSalud> listContactosCentrosSalud(Pageable pageable) {
		
		return contactoInstitucionSaludRepository.findAll(pageable);
	}
	
	@Override
	public ContactoUsuario save(ContactoUsuario contacto) throws BusinessException {
		
		Usuario usuario = null;
		
		if(contacto.getUsuario() != null) usuario = contacto.getUsuario();
		
		contacto = ContactoUsuario.builder()
				.mailAlternativo(contacto.getMailAlternativo())
				.telefono(contacto.getTelefono())
				.usuario(usuario)
				.build();
		
		return repository.save(contacto);
	}

	@Override
	public ContactoUsuario update(ContactoUsuario contactoModificar, ContactoUsuario contactoNuevo)
			throws BusinessException {
		
		Usuario usuario = null;

		if(contactoNuevo.getUsuario() != null) usuario = contactoNuevo.getUsuario();
		
		contactoModificar = ContactoUsuario.builder()
				.id(contactoModificar.getId())
				.mailAlternativo(contactoNuevo.getMailAlternativo())
				.telefono(contactoNuevo.getTelefono())
				.usuario(usuario)
				.build();
		
		return repository.save(contactoModificar);
	}

	@Override
	public ContactoProfesional save(ContactoProfesional contacto) throws BusinessException {
		
		Profesional profesional = null;
		
		if(contacto.getProfesional() != null) profesional = contacto.getProfesional(); 
		
		contacto = ContactoProfesional.builder()
				.mailAlternativo(contacto.getMailAlternativo())
				.telefono(contacto.getTelefono())
				.profesional(profesional)
				.build();
		
		return repository.save(contacto);
	}

	@Override
	public ContactoProfesional update(ContactoProfesional contactoModificar, ContactoProfesional contactoNuevo)
			throws BusinessException {
		
		Profesional profesional = null;
		
		if(contactoNuevo.getProfesional() != null) profesional = contactoNuevo.getProfesional(); 
		
		contactoModificar = ContactoProfesional.builder()
				.id(contactoModificar.getId())
				.mailAlternativo(contactoNuevo.getMailAlternativo())
				.telefono(contactoNuevo.getTelefono())
				.profesional(profesional)
				.build();
		
		return repository.save(contactoModificar);
	}

	@Override
	public ContactoInstitucionSalud save(ContactoInstitucionSalud contacto) throws BusinessException {
		
		InstitucionSalud institucionSalud = null;
		
		if(contacto.getInstitucionSalud() != null) institucionSalud = contacto.getInstitucionSalud();

		contacto = ContactoInstitucionSalud.builder()
				.mailAlternativo(contacto.getMailAlternativo())
				.telefono(contacto.getTelefono())
				.institucionSalud(institucionSalud)
				.build();
		
		return repository.save(contacto);
	}

	@Override
	public ContactoInstitucionSalud update(ContactoInstitucionSalud contactoModificar, ContactoInstitucionSalud contactoNuevo)
			throws BusinessException {
		
		InstitucionSalud institucionSalud = null;
		
		if(contactoNuevo.getInstitucionSalud() != null) institucionSalud = contactoNuevo.getInstitucionSalud();
		
		contactoModificar = ContactoInstitucionSalud.builder()
				.id(contactoModificar.getId())
				.mailAlternativo(contactoNuevo.getMailAlternativo())
				.telefono(contactoNuevo.getTelefono())
				.institucionSalud(institucionSalud)
				.build();
		
		return repository.save(contactoModificar);
	}

	@Override
	public Contacto findById(Long id) throws BusinessException {
		
		Optional<Contacto> contactoOptional = repository.findById(id);
		if(contactoOptional.isPresent()) return contactoOptional.get();
		throw new BusinessException("No se pudo encontrar el contacto con el id: " + id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

	@Override
	public void delete(Contacto contacto) {
		
		repository.delete(contacto);
	}

}
