package healthSafe.dvds20222cg4hce.service.prescripcion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.grupoabstract.Receta;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.prescripcion.ArgRecetaRepository;
import healthSafe.dvds20222cg4hce.repository.prescripcion.RecetaRepository;

@Service
public class RecetaServiceImpl implements RecetaService{
	
	private RecetaRepository recetaRepository;
	private ArgRecetaRepository argRecetaRepository;
	
	@Autowired
	public RecetaServiceImpl(final RecetaRepository recetaRepository,
								final ArgRecetaRepository argRecetaRepository) {
		this.recetaRepository = recetaRepository;
		this.argRecetaRepository = argRecetaRepository;
	}

	@Override
	public List<Receta> listReceta() {
		
		return recetaRepository.findAll();
	}

	@Override
	public List<ArgReceta> listArgReceta() {
		
		return argRecetaRepository.findAll();
	}

	@Override
	public Page<Receta> listReceta(Pageable pageable) {
		
		return recetaRepository.findAll(pageable);
	}

	@Override
	public Page<ArgReceta> listArgReceta(Pageable pageable) {
		
		return argRecetaRepository.findAll(pageable);
	}

	@Override
	public ArgReceta save(ArgReceta receta) throws BusinessException {
		
		if(receta.getId() == null) return argRecetaRepository.save(receta);
		throw new BusinessException("No se puede crear una receta que ya fue creada");
	}

	@Override
	public ArgReceta update(ArgReceta receta) throws BusinessException {
		
		if(receta.getId() != null) return argRecetaRepository.save(receta);
		throw new BusinessException("No se puede actualizar una receta que no ha sido creada");
	}

	@Override
	public Receta findById(Long id) throws BusinessException {
		
		Optional<Receta> recetaOptional = recetaRepository.findById(id);
		if(recetaOptional.isPresent()) return recetaOptional.get();
		throw new BusinessException("No se pudo encontrar la receta con el id: " + id);
	}

	@Override
	public void delete(ArgReceta receta) {
		
		recetaRepository.delete(receta);
	}

	@Override
	public void delete(Long id) {
		
		recetaRepository.deleteById(id);
	}

	@Override
	public long count() {
		
		return recetaRepository.count();
	}

}
