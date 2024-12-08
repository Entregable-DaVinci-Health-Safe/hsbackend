package healthSafe.dvds20222cg4hce.service;

import java.util.List;
import healthSafe.dvds20222cg4hce.domain.historia.Turno;
import healthSafe.dvds20222cg4hce.exception.BusinessException;

public interface TurnoService {

    List<Turno> getTurnosByHistoriaMedica(Long historiaMedicaId)throws BusinessException;
    Turno save(Turno turno) throws BusinessException;
    Turno update(Turno turno) throws BusinessException;
	Turno findById(Long id) throws BusinessException;

    Turno activarTurno(Turno turno) throws BusinessException;
	void desactivarTurno(Turno turno) throws BusinessException;
	
	long count();
}
