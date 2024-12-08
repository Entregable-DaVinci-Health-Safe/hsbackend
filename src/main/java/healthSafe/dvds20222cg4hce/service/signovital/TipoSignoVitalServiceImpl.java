package healthSafe.dvds20222cg4hce.service.signovital;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.signovital.TipoSignoVital;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.signovital.TipoSignoVitalRepository;

@Service
public class TipoSignoVitalServiceImpl implements TipoSignoVitalService{
	
	private TipoSignoVitalRepository repository;
	
	@Autowired
	public TipoSignoVitalServiceImpl(final TipoSignoVitalRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<TipoSignoVital> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<TipoSignoVital> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public TipoSignoVital findById(Long id) throws BusinessException {
		
		Optional<TipoSignoVital> tipoSignoVitalOptional = repository.findById(id);
		if(tipoSignoVitalOptional.isPresent()) return tipoSignoVitalOptional.get();
		throw new BusinessException("No se pudo encontrar el tipo de signo vital con el id: " + id);
	}

}
