package healthSafe.dvds20222cg4hce.service.signovital;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.signovital.ResultadoSignoVital;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.signovital.SignoVitalPacienteRepository;
import healthSafe.dvds20222cg4hce.service.HistoriaMedicaService;
import healthSafe.dvds20222cg4hce.utils.SignoVitalUtils;

@Service
public class SignoVitalPacienteServiceImpl implements SignoVitalPacienteService{
	
	private SignoVitalPacienteRepository repository;
	private SignoVitalCustomService signoVitalCustomService;
	private HistoriaMedicaService historiaMedicaService;
	
	@Autowired
	public SignoVitalPacienteServiceImpl(final SignoVitalPacienteRepository repository, 
			final SignoVitalCustomService signoVitalCustomService,
			final HistoriaMedicaService historiaMedicaService) {
		this.repository = repository;
		this.signoVitalCustomService = signoVitalCustomService;
		this.historiaMedicaService = historiaMedicaService;
		
	}
	
	@Override
	public List<SignoVitalPaciente> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<SignoVitalPaciente> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public SignoVitalPaciente save(SignoVitalPaciente signoVitalPaciente) throws BusinessException {
		
		HistoriaMedica historiaMedica = null;
		SignoVitalCustom signoVitalCustom = null;
		ResultadoSignoVital segundoResultado = null;
		
		if(signoVitalPaciente.getHistoriaMedica().getId() == null) {
			throw new BusinessException("La historia medica es obligatorio");
		}
		
		if(signoVitalPaciente.getSignoVitalCustom().getId() == null) {
			throw new BusinessException("El signo vital custom es obligatorio");
		}
		
		historiaMedica = getHistoriaMedica(signoVitalPaciente.getHistoriaMedica().getId());
		signoVitalCustom = getSignoVitalCustom(signoVitalPaciente.getSignoVitalCustom().getId());
		
		if(signoVitalPaciente.getSegundoValor() != null) {
			segundoResultado = SignoVitalUtils.getResultadoSignoVital(
					signoVitalPaciente.getSegundoValor(),
					signoVitalCustom.getSegundoMinimo(), 
					signoVitalCustom.getSegundoMaximo());
		}
		
		signoVitalPaciente = SignoVitalPaciente.builder()
				.valor(signoVitalPaciente.getValor())
				.segundoValor(signoVitalPaciente.getSegundoValor())
				.fechaIngresado(signoVitalPaciente.getFechaIngresado())
				.resultado(SignoVitalUtils.getResultadoSignoVital(
						signoVitalPaciente.getValor(),
						signoVitalCustom.getMinimo(), 
						signoVitalCustom.getMaximo()))
				.segundoResultado(segundoResultado)
				.comentario(signoVitalPaciente.getComentario())
				.minimo(signoVitalCustom.getMinimo())
				.maximo(signoVitalCustom.getMaximo())
				.segundoMinimo(signoVitalCustom.getSegundoMinimo())
				.segundoMaximo(signoVitalCustom.getSegundoMaximo())
				.historiaMedica(historiaMedica)
				.signoVitalCustom(signoVitalCustom)
				.build();
		
		signoVitalPaciente =  repository.save(signoVitalPaciente);
		
		historiaMedica.addSignoVitalPaciente(signoVitalPaciente);
		
		return signoVitalPaciente;
	}

	@Override
	public SignoVitalPaciente update(SignoVitalPaciente signoVitalPaciente) throws BusinessException {
		
		ResultadoSignoVital segundoResultado = null;
		
		if(signoVitalPaciente.getSegundoValor() != null) {
			segundoResultado = SignoVitalUtils.getResultadoSignoVital(
					signoVitalPaciente.getSegundoValor(),
					signoVitalPaciente.getSegundoMinimo(), 
					signoVitalPaciente.getSegundoMaximo());
		}
		
		signoVitalPaciente.setResultado(SignoVitalUtils.getResultadoSignoVital(
						signoVitalPaciente.getValor(),
						signoVitalPaciente.getMinimo(), 
						signoVitalPaciente.getMaximo()));
		signoVitalPaciente.setSegundoResultado(segundoResultado);
		if(signoVitalPaciente.getId() != null) return repository.save(signoVitalPaciente);
		throw new BusinessException("No se puede actualizar el signo vital del paciente");
	}

	@Override
	public SignoVitalPaciente findById(Long id) throws BusinessException {
		
		Optional<SignoVitalPaciente> signoVitalPacienteOptional = repository.findById(id);
		if(signoVitalPacienteOptional.isPresent()) return signoVitalPacienteOptional.get();
		throw new BusinessException("No se pudo encontrar el signo vital paciente con el id: " + id);
	}

	@Override
	public void delete(SignoVitalPaciente signoVitalPaciente) {
		
		repository.delete(signoVitalPaciente);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}
	
	private HistoriaMedica getHistoriaMedica(Long historiaMedicaId) throws BusinessException{
		return historiaMedicaService.findById(historiaMedicaId);
	}
	
	private SignoVitalCustom getSignoVitalCustom(Long signoVitalCustomId) throws BusinessException{
		return signoVitalCustomService.findById(signoVitalCustomId);
	}
	

}
