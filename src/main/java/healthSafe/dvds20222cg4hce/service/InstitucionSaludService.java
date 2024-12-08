package healthSafe.dvds20222cg4hce.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface InstitucionSaludService {
	
	List<InstitucionSalud> listInstitucionesSalud();
	Page<InstitucionSalud> listInstitucionesSalud(Pageable pageable);
	
	InstitucionSalud save(InstitucionSalud institucionSalud) throws BusinessException;
	InstitucionSalud update(InstitucionSalud institucionSalud) throws BusinessException;
	
	InstitucionSalud findById(Long id) throws BusinessException;
	List<InstitucionSalud> getInstitucionesSaludByHistoriaMedica(Long historiaMedicaId)throws BusinessException;
	List<InstitucionSalud> getInstitucionesSaludByProfesionales(Long profesionalId)throws BusinessException;
	
	InstitucionSalud addProfesional(Long institucionSaludId, Long profesionalId) throws BusinessException;
	InstitucionSalud removeProfesional(Long institucionSaludId, Long profesionalId) throws BusinessException;
	
	InstitucionSalud addProfesionales(InstitucionSalud institucionSalud, List<Long> profesionalesIds) throws BusinessException;
	InstitucionSalud removeProfesionales(InstitucionSalud institucionSalud, List<Long> profesionalesIds) throws BusinessException;
	
	InstitucionSalud createContacto(InstitucionSalud institucionSalud, ContactoInstitucionSalud contacto) throws BusinessException;
	InstitucionSalud updateContacto(InstitucionSalud institucionSalud, Long contactoId, ContactoInstitucionSalud contacto)throws BusinessException;
	InstitucionSalud removeContacto(InstitucionSalud institucionSalud, Long contactoId)throws BusinessException;
	
	InstitucionSalud createDireccion(InstitucionSalud institucionSalud, DireccionInstitucionSalud direccion) throws BusinessException;
	InstitucionSalud updateDireccion(InstitucionSalud institucionSalud, Long direccionId, DireccionInstitucionSalud direccion)throws BusinessException;
	
	InstitucionSalud activarInstitucionSalud(InstitucionSalud institucionSalud)throws BusinessException;
	void desactivarInstitucionSalud(InstitucionSalud institucionSalud)throws BusinessException;
	
	long count();
}
