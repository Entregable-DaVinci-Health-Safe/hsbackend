package healthSafe.dvds20222cg4hce.service.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoUsuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface ContactoService {
	
	List<Contacto> listContactos();
	List<ContactoUsuario> listContactosUsuarios();
	List<ContactoProfesional> listContactosProfesionales();
	List<ContactoInstitucionSalud> listContactosCentrosSalud();
	List<Contacto> listContactosByUserId(Long idUsuario) throws BusinessException;
	
	Page<Contacto> listContactos(Pageable pageable);
	Page<ContactoUsuario> listContactosUsuarios(Pageable pageable);
	Page<ContactoProfesional> listContactosProfesionales(Pageable pageable);
	Page<ContactoInstitucionSalud> listContactosCentrosSalud(Pageable pageable);

	ContactoUsuario save(ContactoUsuario contacto) throws BusinessException;
	ContactoUsuario update(ContactoUsuario contactoModificar, ContactoUsuario contactoNuevo) throws BusinessException;
	
	ContactoProfesional save(ContactoProfesional contacto) throws BusinessException;
	ContactoProfesional update(ContactoProfesional contactoModificar, ContactoProfesional contactoNuevo) throws BusinessException;
	
	ContactoInstitucionSalud save(ContactoInstitucionSalud contacto) throws BusinessException;
	ContactoInstitucionSalud update(ContactoInstitucionSalud contactoModificar, ContactoInstitucionSalud contactoNuevo) throws BusinessException;
	
	Contacto findById(Long id) throws BusinessException;
	
	void delete(Contacto contacto);
	
	long count();
}
