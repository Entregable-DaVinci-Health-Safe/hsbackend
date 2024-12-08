package healthSafe.dvds20222cg4hce.service.signovital;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface SignoVitalPacienteService {
	
	List<SignoVitalPaciente> list();
	Page<SignoVitalPaciente> list(Pageable pageable);
	
	SignoVitalPaciente save(SignoVitalPaciente signoVitalPaciente) throws BusinessException;
	SignoVitalPaciente update(SignoVitalPaciente signoVitalPaciente) throws BusinessException;
	
	SignoVitalPaciente findById(Long id) throws BusinessException;
	
	void delete(SignoVitalPaciente signoVitalPaciente);
	void delete(Long id);
}
