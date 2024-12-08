package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.calendario.CalendarioEdadVacunaLinkRepository;

@Service
public class CalendarioEdadVacunaLinkServiceImpl implements CalendarioEdadVacunaLinkService{
	
	private CalendarioEdadVacunaLinkRepository repository;
	
	public CalendarioEdadVacunaLinkServiceImpl(
			final CalendarioEdadVacunaLinkRepository repository) {
		this.repository = repository;
	}
	
	
	@Override
	public List<CalendarioEdadVacunaLink> listAll() {
		
		return repository.findAll();
	}

	@Override
	public Page<CalendarioEdadVacunaLink> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public CalendarioEdadVacunaLink save(CalendarioEdadVacunaLink link) throws BusinessException {
		
		if(link.getId() == null) return repository.save(link);
		throw new BusinessException("No se puede crear una relación con un id específico");
	}

	@Override
	public CalendarioEdadVacunaLink update(CalendarioEdadVacunaLink link) throws BusinessException {
		
		if(link.getId() != null) return repository.save(link);
		throw new BusinessException("No se puede modificar una relación no creada");
	}

	@Override
	public CalendarioEdadVacunaLink findById(Long linkId) throws BusinessException {
		
		Optional<CalendarioEdadVacunaLink> linkOptional = repository.findById(linkId);
		if(linkOptional.isPresent()) return linkOptional.get();
		throw new BusinessException("No se pudo encontrar una relación con el id: " + linkId);
	}
	
	@Override
	public CalendarioEdadVacunaLink findByCalendarioIdAndRangoEdadIdAndVacunaUsuarioId
		(Long calendarioId, Long rangoEdadId, Long vacunaUsuarioId) throws BusinessException {
		
		Optional<CalendarioEdadVacunaLink> linkOptional = 
				repository.findByCalendarioIdAndRangoEdadIdAndVacunaUsuarioId(
						calendarioId, rangoEdadId, vacunaUsuarioId);
		if(linkOptional.isPresent()) return linkOptional.get();
		throw new BusinessException("No se pudo encontrar");
	}

	@Override
	public void delete(CalendarioEdadVacunaLink link) throws BusinessException {
		
		repository.delete(link);
	}

	@Override
	public void delete(Long linkId) throws BusinessException {
		
		repository.deleteById(linkId);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

}
