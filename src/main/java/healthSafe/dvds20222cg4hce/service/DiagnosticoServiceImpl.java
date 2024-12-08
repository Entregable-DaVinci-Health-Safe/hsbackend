package healthSafe.dvds20222cg4hce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.DiagnosticoRepository;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService{
	
	private DiagnosticoRepository repository;
	
	@Autowired
	public DiagnosticoServiceImpl(final DiagnosticoRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Diagnostico> list() {
		
		return repository.findAllByOrderByNombreAsc();
	}
	
	@Override
	public List<Diagnostico> listDiagnosticosLike(String nombre){
		return repository.findByNombreIgnoreCaseContainingOrderByNombreAsc(nombre);
	}

	@Override
	public Page<Diagnostico> list(Pageable pageable) {
		
		return repository.findAllByOrderByNombreAsc(pageable);
	}
	
	@Override
	public Diagnostico findById(Long id) throws BusinessException {
		
		Optional<Diagnostico> diagnosticoOptional = repository.findById(id);
		if(diagnosticoOptional.isPresent()) return diagnosticoOptional.get();
		throw new BusinessException("No se pudo encontrar el diagnostico con el id: " + id);
	}

}
