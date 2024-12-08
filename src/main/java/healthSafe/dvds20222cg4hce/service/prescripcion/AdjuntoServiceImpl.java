package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.prescripcion.Adjunto;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.AdjuntoRepository;

@Service
public class AdjuntoServiceImpl implements AdjuntoService{
	
	private AdjuntoRepository repository;
	
	@Autowired
	public AdjuntoServiceImpl(final AdjuntoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Adjunto> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<Adjunto> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}
	
	@Override
	public Adjunto save(Adjunto adjunto) throws BusinessException {
		
		if(adjunto.getId() == null) return repository.save(adjunto);
		throw new BusinessException("No se puede guardar un adjunto que ya fue creado");
	}

	@Override
	public Adjunto findById(Long id) throws BusinessException {
		
		Optional<Adjunto> adjuntoOptional = repository.findById(id);
		if(adjuntoOptional.isPresent()) return adjuntoOptional.get();
		throw new BusinessException("No se pudo encontrar el adjunto con el id: " + id);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}

	@Override
	public void delete(Adjunto adjunto) {
		
		repository.delete(adjunto);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

}
