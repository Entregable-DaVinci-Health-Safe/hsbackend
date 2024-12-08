package healthSafe.dvds20222cg4hce.service.usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionProfesional;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionUsuario;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.domain.usuario.Usuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.ubicacion.DireccionInstitucionSaludRepository;
import healthSafe.dvds20222cg4hce.repository.ubicacion.DireccionProfesionalRepository;
import healthSafe.dvds20222cg4hce.repository.ubicacion.DireccionRepository;
import healthSafe.dvds20222cg4hce.repository.ubicacion.DireccionUsuarioRepository;

@Service
public class DireccionServiceImpl implements DireccionService{
	
	private DireccionRepository repository;
	private DireccionUsuarioRepository direccionUsuarioRepository;
	private DireccionProfesionalRepository direccionProfesionalRepository;
	private DireccionInstitucionSaludRepository direccionCentroSaludRepository;
	
	@Autowired
	public DireccionServiceImpl(final DireccionRepository repository,
								final DireccionUsuarioRepository direccionUsuarioRepository,
								final DireccionProfesionalRepository direccionProfesionalRepository,
								final DireccionInstitucionSaludRepository direccionCentroSaludRepository) {
		
		this.repository = repository;
		this.direccionUsuarioRepository = direccionUsuarioRepository;
		this.direccionProfesionalRepository = direccionProfesionalRepository;
		this.direccionCentroSaludRepository = direccionCentroSaludRepository;
	}
	
	@Override
	public List<Direccion> listDirecciones() {
		
		return repository.findAll();
	}
	
	@Override
	public List<DireccionUsuario> listDireccionesUsuarios() {
		
		return direccionUsuarioRepository.findAll();
	}
	
	@Override
	public List<DireccionProfesional> listDireccionesProfesionales() {
		
		return direccionProfesionalRepository.findAll();
	}

	@Override
	public List<DireccionInstitucionSalud> listDireccionesCentrosSalud() {
		
		return direccionCentroSaludRepository.findAll();
	}
	
	@Override
	public List<Direccion> listDireccionesByUserId(Long idUsuario) throws BusinessException {
		
		return null;
	}

	@Override
	public Page<Direccion> listDirecciones(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
	
	@Override
	public Page<DireccionUsuario> listDireccionesUsuarios(Pageable pageable) {
		
		return direccionUsuarioRepository.findAll(pageable);
	}

	@Override
	public Page<DireccionProfesional> listDireccionesProfesionales(Pageable pageable) {
		
		return direccionProfesionalRepository.findAll(pageable);
	}

	@Override
	public Page<DireccionInstitucionSalud> listDireccionesCentrosSalud(Pageable pageable) {
		
		return direccionCentroSaludRepository.findAll(pageable);
	}

	@Override
	public DireccionUsuario save(DireccionUsuario direccion) throws BusinessException {
		
		Usuario usuario = null;
		
		if(direccion.getUsuario() != null) usuario = direccion.getUsuario();
		
		direccion = DireccionUsuario.builder()
				.direccion(direccion.getDireccion())
				.provincia(direccion.getProvincia())
				.localidad(direccion.getLocalidad())
				.barrio(direccion.getBarrio())
				.departamento(direccion.getDepartamento())
				.piso(direccion.getPiso())
				.referencia(direccion.getReferencia())
				.usuario(usuario)
				.build();
		
		return direccionUsuarioRepository.save(direccion);

	}

	@Override
	public DireccionUsuario update(DireccionUsuario direccionModificar, DireccionUsuario direccionNueva) throws BusinessException {
		
		Usuario usuario = null;
		
		if(direccionNueva.getUsuario() != null) usuario = direccionNueva.getUsuario();
		
		direccionModificar = DireccionUsuario.builder()
				.id(direccionModificar.getId())
				.direccion(direccionNueva.getDireccion())
				.provincia(direccionNueva.getProvincia())
				.localidad(direccionNueva.getLocalidad())
				.barrio(direccionNueva.getBarrio())
				.departamento(direccionNueva.getDepartamento())
				.piso(direccionNueva.getPiso())
				.referencia(direccionNueva.getReferencia())
				.usuario(usuario)
				.build();
		
		
		return direccionUsuarioRepository.save(direccionModificar);
	}
	
	@Override
	public DireccionProfesional save(DireccionProfesional direccion) throws BusinessException {
		
		Profesional profesional = null;
		
		if(direccion.getProfesional() != null) profesional = direccion.getProfesional();
		
		direccion = DireccionProfesional.builder()
				.direccion(direccion.getDireccion())
				.provincia(direccion.getProvincia())
				.localidad(direccion.getLocalidad())
				.barrio(direccion.getBarrio())
				.departamento(direccion.getDepartamento())
				.piso(direccion.getPiso())
				.referencia(direccion.getReferencia())
				.profesional(profesional)
				.build();
		
		return direccionProfesionalRepository.save(direccion);
	}

	@Override
	public DireccionProfesional update(DireccionProfesional direccionModificar, DireccionProfesional direccionNueva)
			throws BusinessException {
		
		Profesional profesional = null;
		
		if(direccionNueva.getProfesional() != null) profesional = direccionNueva.getProfesional();
		
		direccionModificar = DireccionProfesional.builder()
				.id(direccionModificar.getId())
				.direccion(direccionNueva.getDireccion())
				.provincia(direccionNueva.getProvincia())
				.localidad(direccionNueva.getLocalidad())
				.barrio(direccionNueva.getBarrio())
				.departamento(direccionNueva.getDepartamento())
				.piso(direccionNueva.getPiso())
				.referencia(direccionNueva.getReferencia())
				.profesional(profesional)
				.build();
		
		
		return direccionProfesionalRepository.save(direccionModificar);
	}

	@Override
	public DireccionInstitucionSalud save(DireccionInstitucionSalud direccion) throws BusinessException {
		
		
		direccion = DireccionInstitucionSalud.builder()
				.direccion(direccion.getDireccion())
				.provincia(direccion.getProvincia())
				.localidad(direccion.getLocalidad())
				.barrio(direccion.getBarrio())
				.departamento(direccion.getDepartamento())
				.piso(direccion.getPiso())
				.referencia(direccion.getReferencia())
				.build();
		
		return direccionCentroSaludRepository.save(direccion);
	}

	@Override
	public DireccionInstitucionSalud update(DireccionInstitucionSalud direccionModificar, DireccionInstitucionSalud direccionNueva)
			throws BusinessException {
		
		
		direccionModificar = DireccionInstitucionSalud.builder()
				.id(direccionModificar.getId())
				.direccion(direccionNueva.getDireccion())
				.provincia(direccionNueva.getProvincia())
				.localidad(direccionNueva.getLocalidad())
				.barrio(direccionNueva.getBarrio())
				.departamento(direccionNueva.getDepartamento())
				.piso(direccionNueva.getPiso())
				.referencia(direccionNueva.getReferencia())
				.build();
		
		
		return direccionCentroSaludRepository.save(direccionModificar);
	}

	@Override
	public Direccion findById(Long id) throws BusinessException {
		
		Optional<Direccion> direccionOptional = repository.findById(id);
		if(direccionOptional.isPresent()) return direccionOptional.get();
		throw new BusinessException("No se pudo encontrar la dirección con el id: " + id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}
	
	@Override
	public void delete(Direccion direccion) {
		
		repository.delete(direccion);
	}
	
	
}
