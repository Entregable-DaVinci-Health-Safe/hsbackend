package healthSafe.dvds20222cg4hce.service.profesional;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.contacto.ContactoProfesional;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface ProfesionalService {
	
	List<Profesional> listProfesionales();
	Page<Profesional> listProfesionales(Pageable pageable);

	Profesional save(Profesional profesional) throws BusinessException;
	Profesional update(Profesional profesional) throws BusinessException;
	
	Profesional findById(Long id) throws BusinessException;
	List<Profesional> getProfesionalesByHistoriaMedica(Long historiaMedicaId) throws BusinessException;
	List<Profesional> getProfesionalesByCentrosSalud(Long centroSaludId) throws BusinessException;
	
	Profesional createDireccion(Profesional profesional, DireccionProfesional direccion) throws BusinessException;
	Profesional updateDireccion(Profesional profesional, Long direccionId, DireccionProfesional direccion) throws BusinessException;
	Profesional removeDireccion(Profesional profesional, Long direccionId)throws BusinessException;
	
	Profesional createContacto(Profesional profesional, ContactoProfesional contacto) throws BusinessException;
	Profesional updateContacto(Profesional profesional, Long contactoId, ContactoProfesional contacto) throws BusinessException;
	Profesional removeContacto(Profesional profesional, Long contactoId)throws BusinessException;
	
	Profesional addEspecialidad(Long profesionalId, Long especialidadId) throws BusinessException;
	Profesional addEspecialidades(Profesional profesional, List<Long> especialidadesIds) throws BusinessException;
	Profesional removeEspecialidad(Long profesionalId, Long especialidadId) throws BusinessException;
	Profesional removeEspecialidades(Profesional profesional, List<Long> especialidadesIds) throws BusinessException;
	List<Especialidad> getProfesionalEspecialidades(Profesional profesional) throws BusinessException;
	
	Profesional activarProfesional(Profesional profesional)throws BusinessException;
	void desactivarProfesional(Profesional profesional)throws BusinessException;
	
	long count();
	
}
