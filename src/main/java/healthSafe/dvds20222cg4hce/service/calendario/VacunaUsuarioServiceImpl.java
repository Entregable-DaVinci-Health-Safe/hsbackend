package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.calendario.VacunaUsuario;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.calendario.VacunaUsuarioRepository;

@Service
public class VacunaUsuarioServiceImpl implements VacunaUsuarioService{
	
	private VacunaUsuarioRepository repository;
	
	@Autowired
	public VacunaUsuarioServiceImpl(final VacunaUsuarioRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<VacunaUsuario> listVacunasUsuarios() {
		
		return repository.findAll();
	}

	@Override
	public Page<VacunaUsuario> listVacunasUsuario(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public VacunaUsuario save(VacunaUsuario vacunaUsuario) throws BusinessException {
		

		vacunaUsuario = VacunaUsuario.builder()
				.aplicada(vacunaUsuario.getAplicada())
				.fecha(vacunaUsuario.getFecha())
				.numeroDosis(vacunaUsuario.getNumeroDosis())
				.vacuna(vacunaUsuario.getVacuna())
				.build();
		
		return repository.save(vacunaUsuario);

	}

	@Override
	public VacunaUsuario update(VacunaUsuario vacunaUsuario) throws BusinessException {
		
		return null;
	}

	@Override
	public VacunaUsuario findById(Long id) throws BusinessException {
		
		Optional<VacunaUsuario> vacunaUsuarioOptional = repository.findById(id);
		if(vacunaUsuarioOptional.isPresent()) return vacunaUsuarioOptional.get();
		throw new BusinessException("No se pudo encontrar la vacuna del usuario con el id: " + id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}

}
