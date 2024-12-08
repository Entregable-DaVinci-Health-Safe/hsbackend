package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Estudio;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.prescripcion.ArgEstudioRepository;
import healthSafe.dvds20222cg4hce.repository.prescripcion.EstudioRepository;

@Service
public class EstudioServiceImpl implements EstudioService{
	
	private EstudioRepository estudioRepository;
	private ArgEstudioRepository argEstudioRepository;
	private AdjuntoService adjuntoService;
	
	@Autowired
	public EstudioServiceImpl(final EstudioRepository estudioRepository,
								final ArgEstudioRepository argEstudioRepository,
								final AdjuntoService adjuntoService) {
		
		this.estudioRepository = estudioRepository;
		this.argEstudioRepository = argEstudioRepository;
		this.adjuntoService = adjuntoService;
	}
	
	@Override
	public List<Estudio> listEstudio() {
		
		return estudioRepository.findAll();
	}

	@Override
	public List<ArgEstudio> listArgEstudio() {
		
		return argEstudioRepository.findAll();
	}

	@Override
	public Page<Estudio> listEstudio(Pageable pageable) {
		
		return estudioRepository.findAll(pageable);
	}

	@Override
	public Page<ArgEstudio> listArgEstudio(Pageable pageable) {
		
		return argEstudioRepository.findAll(pageable);
	}

	@Override
	public ArgEstudio save(ArgEstudio estudio) throws BusinessException {
		
		if(estudio.getId() == null) return argEstudioRepository.save(estudio);
		throw new BusinessException("No se puede crear un estudio que ya fue creada");
	}

	@Override
	public ArgEstudio update(ArgEstudio estudio) throws BusinessException {
		
		if(estudio.getId() != null) return argEstudioRepository.save(estudio);
		throw new BusinessException("No se puede actualizar un estudio que no ha sido creado");
	}

	@Override
	public Estudio findById(Long id) throws BusinessException {
		
		Optional<Estudio> estudioOptional = estudioRepository.findById(id);
		if(estudioOptional.isPresent()) return estudioOptional.get();
		throw new BusinessException("No se pudo encontrar el estudio con el id: " + id);
	}

	@Override
	public void delete(Estudio estudio) {
		
		estudioRepository.delete(estudio);
	}

	@Override
	public void delete(Long id) {
		
		estudioRepository.deleteById(id);
	}

	@Override
	public long count() {
		
		return estudioRepository.count();
	}

}
