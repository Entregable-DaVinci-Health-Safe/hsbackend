package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.TipoCalendario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.calendario.RangoEdadRepository;

@Service
public class RangoEdadServiceImpl implements RangoEdadService{
	
	private RangoEdadRepository repository;
	
	@Autowired
	public RangoEdadServiceImpl(final RangoEdadRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<RangoEdad> listRangoEdades() {
		
		return repository.findAll();
	}

	@Override
	public Page<RangoEdad> listRangoEdades(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public RangoEdad save(RangoEdad rangoEdad) throws BusinessException {
		
		return null;
	}

	@Override
	public RangoEdad update(RangoEdad rangoEdad) throws BusinessException {
		
		return null;
	}

	@Override
	public RangoEdad findById(Long id) throws BusinessException {
		
		Optional<RangoEdad> rangoEdadOptional = repository.findById(id);
		if(rangoEdadOptional.isPresent()) return rangoEdadOptional.get();
		throw new BusinessException("No se pudo encontrar el rango de edad con el id: " + id);
	}

	@Override
	public List<RangoEdad> findAllByTipoCalendario(String tipoCalendario) throws BusinessException {
		
		return repository.findRangoEdadesByTipoCalendariosId(tipoCalendario);
	}

	@Override
	public RangoEdad addTipoCalendario(Long id, TipoCalendario tipoCalendario) throws BusinessException {
		
		return null;
	}

	@Override
	public long count() {
		
		return repository.count();
	}

}
