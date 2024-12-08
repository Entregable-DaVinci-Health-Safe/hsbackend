package healthSafe.dvds20222cg4hce.service.profesional;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.profesional.EspecialidadRepository;

@Service
public class EspecialidadServiceImpl implements EspecialidadService{
	
	private EspecialidadRepository repository;
	
	@Autowired
	public EspecialidadServiceImpl(final EspecialidadRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Especialidad> list() {
		
		return repository.findAllByOrderByNombreAsc();
	}

	@Override
	public Page<Especialidad> list(Pageable pageable) {
		
		return repository.findAllByOrderByNombreAsc(pageable);
	}
	
	@Override
	public Especialidad save(Especialidad especialidad) throws BusinessException {
		
		if(especialidad.getId() == null) return repository.save(especialidad);
		throw new BusinessException("No se puede guardar una especialidad que ya existe");
	}

	@Override
	public Especialidad findById(Long id) throws BusinessException {
		
		Optional<Especialidad> especialidadOptional = repository.findById(id);
		if(especialidadOptional.isPresent()) return especialidadOptional.get();
		throw new BusinessException("No se encotró la especialidad por el id: " + id);
	}

	@Override
	public List<Especialidad> findEspecialidadesByProfesionalId(Long profesionalId) throws BusinessException {
		
		return repository.findEspecialidadesByProfesionalesIdOrderByNombreAsc(profesionalId);
	}
	
	@Override
	public long count() {
		
		return repository.count();
	}

}
