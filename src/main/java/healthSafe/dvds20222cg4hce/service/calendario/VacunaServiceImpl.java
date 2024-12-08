package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.calendario.VacunaRepository;

@Service
public class VacunaServiceImpl implements VacunaService{
	
	private VacunaRepository repository;
	
	@Autowired
	public VacunaServiceImpl(final VacunaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Vacuna> listVacunas() {
		
		return repository.findAll();
	}

	@Override
	public Page<Vacuna> listVacunas(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public Vacuna save(Vacuna vacuna) throws BusinessException {
		
		if(vacuna.getId() == null) return repository.save(vacuna);
		throw new BusinessException("No se puede crear una vacuna que ya fue creada");
	}

	@Override
	public Vacuna update(Vacuna vacuna) throws BusinessException {
		
		return null;
	}

	@Override
	public Vacuna findById(Long id) throws BusinessException {
		
		Optional<Vacuna> vacunaOptional = repository.findById(id);
		if(vacunaOptional.isPresent()) return vacunaOptional.get();
		throw new BusinessException("No se pudo encontrar la vacuna con el id: " + id);
	}
	
	@Override
	public List<Vacuna> findAllByTipoCalendario(String tipoCalendario) throws BusinessException {
		
		return repository.findVacunasByTipoCalendariosId(tipoCalendario.toUpperCase());
	}
	
	@Override
	public List<Vacuna> findAllByRangoEdad(Long rangoEdadId) throws BusinessException {
		
		return repository.findVacunasByRangoEdadesId(rangoEdadId);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

	@Override
	public Boolean thisVacunaIsForThisCalendario(Vacuna vacuna, String tipoCalendario) throws BusinessException {
		
		return vacuna.getTipoCalendarios().stream().anyMatch(tcl -> tcl.getId().equalsIgnoreCase(tipoCalendario));
	}

}
