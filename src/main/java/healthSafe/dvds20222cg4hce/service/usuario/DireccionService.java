package healthSafe.dvds20222cg4hce.service.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface DireccionService {
	
	List<Direccion> listDirecciones();
	List<DireccionUsuario> listDireccionesUsuarios();
	List<DireccionProfesional> listDireccionesProfesionales();
	List<DireccionInstitucionSalud> listDireccionesCentrosSalud();
	
	List<Direccion> listDireccionesByUserId(Long idUsuario) throws BusinessException;
	
	Page<Direccion> listDirecciones(Pageable pageable);
	Page<DireccionUsuario> listDireccionesUsuarios(Pageable pageable);
	Page<DireccionProfesional> listDireccionesProfesionales(Pageable pageable);
	Page<DireccionInstitucionSalud> listDireccionesCentrosSalud(Pageable pageable);
	
	DireccionUsuario save(DireccionUsuario direccion) throws BusinessException;
	DireccionUsuario update(DireccionUsuario direccionModificar, DireccionUsuario direccionNueva) throws BusinessException;
	
	DireccionProfesional save(DireccionProfesional direccion) throws BusinessException;
	DireccionProfesional update(DireccionProfesional direccionModificar, DireccionProfesional direccionNueva) throws BusinessException;
	
	DireccionInstitucionSalud save(DireccionInstitucionSalud direccion) throws BusinessException;
	DireccionInstitucionSalud update(DireccionInstitucionSalud direccionModificar, DireccionInstitucionSalud direccionNueva) throws BusinessException;
	
	Direccion findById(Long id) throws BusinessException;
	
	void delete(Direccion direccion);
	
	long count();
}
