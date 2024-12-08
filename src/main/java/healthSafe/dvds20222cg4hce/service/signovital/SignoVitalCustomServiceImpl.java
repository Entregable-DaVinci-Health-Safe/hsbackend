package healthSafe.dvds20222cg4hce.service.signovital;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.TipoSignoVital;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.signovital.SignoVitalCustomRepository;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;


@Service
public class SignoVitalCustomServiceImpl implements SignoVitalCustomService{

	private SignoVitalCustomRepository repository;
	private HistoriaMedicaService historiaMedicaService;
	private TipoSignoVitalService tipoSignoVitalService;

	
	@Autowired
	public SignoVitalCustomServiceImpl(final SignoVitalCustomRepository repository,
			final HistoriaMedicaService historiaMedicaService,
			final TipoSignoVitalService tipoSignoVitalService) {
		this.repository = repository;
		this.historiaMedicaService = historiaMedicaService;
		this.tipoSignoVitalService = tipoSignoVitalService;
	}
	
	@Override
	public List<SignoVitalCustom> list() {
		
		return repository.findAll();
	}
	
	@Override
	public List<SignoVitalCustom> listSignoVitalCustomByHistoriamedica(Long id) {
		
		return repository.findByHistoriaMedicaId(id);
	}

	@Override
	public Page<SignoVitalCustom> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public SignoVitalCustom save(SignoVitalCustom signoVitalCustom) throws BusinessException {
		
		
		HistoriaMedica historiaMedica = null;
		TipoSignoVital tipoSignoVital = null;
		
		if(signoVitalCustom.getHistoriaMedica().getId() == null) {
			throw new BusinessException("La historia medica es obligatorio");
		}
		
		if(signoVitalCustom.getTipoSignoVital().getId() == null) {
			throw new BusinessException("El tipo de signo vital es obligatorio");
		}
		
		historiaMedica = getHistoriaMedica(signoVitalCustom.getHistoriaMedica().getId());
		tipoSignoVital = getTipoSignoVital(signoVitalCustom.getTipoSignoVital().getId());
		
		signoVitalCustom = SignoVitalCustom.builder()
				.maximo(signoVitalCustom.getMaximo())
				.minimo(signoVitalCustom.getMinimo())
				.segundoMinimo(signoVitalCustom.getSegundoMinimo())
				.segundoMaximo(signoVitalCustom.getSegundoMaximo())
				.historiaMedica(historiaMedica)
				.tipoSignoVital(tipoSignoVital)
				.build();
				
		signoVitalCustom = repository.save(signoVitalCustom);
		
		historiaMedicaService.addSignoVitalCustom(signoVitalCustom.getHistoriaMedica().getId(), signoVitalCustom);
		
		return signoVitalCustom;
	}

	@Override
	public SignoVitalCustom update(SignoVitalCustom signoVitalCustom) throws BusinessException {
		
		if(signoVitalCustom.getId() != null) return repository.save(signoVitalCustom);
		throw new BusinessException("No se puede actualizar el signo vital");
		
	}
	
	@Override
	public SignoVitalCustom findById(Long id) throws BusinessException {
		
		Optional<SignoVitalCustom> signoVitalCustomOptional = repository.findById(id);
		if(signoVitalCustomOptional.isPresent()) return signoVitalCustomOptional.get();
		throw new BusinessException("No se pudo encontrar el signo vital custom con el id: " + id);
	}

	@Override
	public void delete(SignoVitalCustom signoVitalCustom) {
		
		repository.delete(signoVitalCustom);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	
	private HistoriaMedica getHistoriaMedica(Long historiaMedicaId) throws BusinessException{
		return historiaMedicaService.findById(historiaMedicaId);
	}
	
	private TipoSignoVital getTipoSignoVital(Long tipoSignoVitalId) throws BusinessException{
		return tipoSignoVitalService.findById(tipoSignoVitalId);
	}

}
