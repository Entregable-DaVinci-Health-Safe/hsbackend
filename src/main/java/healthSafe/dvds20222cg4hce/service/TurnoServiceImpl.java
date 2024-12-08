package healthSafe.dvds20222cg4hce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.Turno;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.TurnoRepository;

@Service
public class TurnoServiceImpl implements TurnoService{

    private TurnoRepository repository;
    private HistoriaMedicaService historiaMedicaService;

    @Autowired
    public TurnoServiceImpl(final TurnoRepository repository,
                            final HistoriaMedicaService historiaMedicaService){
        this.repository = repository;
        this.historiaMedicaService = historiaMedicaService;
    }

    @Override
    public Turno save(Turno turno) throws BusinessException {
        HistoriaMedica historia = null;

        if(turno.getHistoriaMedica() != null) {
			historia = getHistoriaMedica(turno.getHistoriaMedica().getId());
		}else {
			throw new BusinessException("La Historia Medica es obligatoria");
		}

        turno = Turno.builder()
                    .fechaInicio(turno.getFechaInicio())
                    .direccion(turno.getDireccion())
                    .profesional(turno.getProfesional())
                    .especialidad(turno.getEspecialidad())
                    .institucion(turno.getInstitucion())
                    .motivo(turno.getMotivo())
                    .googleId(turno.getGoogleId())
                    .activo(turno.getActivo())
                    .historiaMedica(historia)
                    .build();

        turno = repository.save(turno);
        historiaMedicaService.addTurno(historia.getId(), turno);
        return turno;
    }

    @Override
    public Turno update(Turno turno) throws BusinessException {
        if(turno != null) return repository.save(turno);
		throw new BusinessException("No se puede actualizar el turno");
    }

    @Override
    public Turno findById(Long id) throws BusinessException {
        Optional<Turno> turnoOptional = repository.findById(id);
		if(turnoOptional.isPresent()) return turnoOptional.get();
		throw new BusinessException("No se pudo encontrar el turno con el id: " + id);
    }

    @Override
    public List<Turno> getTurnosByHistoriaMedica(Long historiaMedicaId) throws BusinessException {
        List<Turno> turnos = new ArrayList<Turno>();
        turnos.stream()
			.sorted((t1, t2) -> Long.compare(t1.getFechaInicio(), t2.getFechaInicio()))
			.collect(Collectors.toList());
        return repository.findByHistoriaMedicaId(historiaMedicaId);
    }

    @Override
    public Turno activarTurno(Turno turno) throws BusinessException {
        turno.setActivo(true);
		return update(turno);
    }

    @Override
    public void desactivarTurno(Turno turno) throws BusinessException {
        turno.setActivo(false);
        turno.setGoogleId(null);
        update(turno);
    }

    @Override
    public long count() {
        return repository.count();
    }

    private HistoriaMedica getHistoriaMedica(Long id) throws BusinessException{
		return historiaMedicaService.findById(id);
	}


}
