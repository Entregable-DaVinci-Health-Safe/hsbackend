package healthSafe.dvds20222cg4hce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.Turno;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.HistoriaMedicaRepository;
import healthSafe.dvds20222cg4hce.service.calendario.CalendarioService;
import healthSafe.dvds20222cg4hce.service.usuario.UsuarioService;
import healthSafe.dvds20222cg4hce.utils.CalendarioUtils;
import healthSafe.dvds20222cg4hce.utils.InstitucionSaludUtils;
import healthSafe.dvds20222cg4hce.utils.ProfesionalUtils;
import healthSafe.dvds20222cg4hce.utils.SignoVitalUtils;
import healthSafe.dvds20222cg4hce.utils.TurnoUtils;
import healthSafe.dvds20222cg4hce.utils.VisitaMedicaUtils;

@Service
public class HistoriaMedicaServiceImpl implements HistoriaMedicaService{
	
	private HistoriaMedicaRepository repository;
	private UsuarioService usuarioService;
	private CalendarioService calendarioService;
	
	@Autowired
	public HistoriaMedicaServiceImpl(final HistoriaMedicaRepository repository,
									final UsuarioService usuarioService,
									final CalendarioService calendarioService) {
		this.repository = repository;
		this.usuarioService = usuarioService;
		this.calendarioService = calendarioService;
	}
	
	@Override
	public List<HistoriaMedica> list() {
		
		return repository.findAll();
	}

	@Override
	public Page<HistoriaMedica> list(Pageable pageable) {
		
		return repository.findAll(pageable);
	}

	@Override
	public HistoriaMedica save(HistoriaMedica historiaMedica) throws BusinessException {
		
		UsuarioPaciente paciente = null;
		
		if(repository.existsByPaciente_Mail(historiaMedica.getPaciente().getMail())) {
			throw new BusinessException("El usuario ya tiene una historia medica");
		}
		
		if(historiaMedica.getPaciente().getMail() == null) {
			throw new BusinessException("El usuario es obligatorio");
		}
		paciente = getUsuarioByMail(historiaMedica.getPaciente().getMail());
		
		
		if(!paciente.getActivo())throw new BusinessException("El usuario no esta activo");
		
		List<VisitaMedica> visitas = new ArrayList<VisitaMedica>();
		if(historiaMedica.getVisitasMedicas() != null) {
			visitas = VisitaMedicaUtils.getListVisitasMedicas(historiaMedica.getVisitasMedicas());
		}
		
		List<Profesional> profesionales = new ArrayList<Profesional>();
		if(historiaMedica.getProfesionales() != null) {
			profesionales = ProfesionalUtils.getListProfesionalesActivos(historiaMedica.getProfesionales());
		}
		
		List<InstitucionSalud> institucionesSalud = new ArrayList<InstitucionSalud>();
		if(historiaMedica.getInstitucionesSalud() != null) {
			institucionesSalud = InstitucionSaludUtils.getListInstitucionesSalud(historiaMedica.getInstitucionesSalud());
		}
		
		List<Calendario> calendarios = new ArrayList<Calendario>();
		if(historiaMedica.getCalendarios() != null) {
			calendarios = CalendarioUtils.getListCalendarios(historiaMedica.getCalendarios());
		}
		
		List<SignoVitalCustom> signosVitalesCustoms = new ArrayList<SignoVitalCustom>();
		if(historiaMedica.getSignosVitalesCustoms() != null) {
			signosVitalesCustoms = SignoVitalUtils.getListSignosVitalesCustoms(signosVitalesCustoms);
		}
		
		List<SignoVitalPaciente> signosVitalesPaciente = new ArrayList<SignoVitalPaciente>();
		if(historiaMedica.getSignosVitalesPaciente() != null) {
			signosVitalesPaciente = SignoVitalUtils.getListSignosVitalesPaciente(signosVitalesPaciente);
		}
		
		historiaMedica = HistoriaMedica.builder()
							.paciente(paciente)
							.visitasMedicas(visitas)
							.profesionales(profesionales)
							.institucionesSalud(institucionesSalud)
							.calendarios(calendarios)
							.signosVitalesCustoms(signosVitalesCustoms)
							.signosVitalesPaciente(signosVitalesPaciente)
							.turnos(TurnoUtils.getListTurnoFromListDomain(historiaMedica.getTurnos()))
							.build();
		
		historiaMedica = repository.save(historiaMedica);
		
		historiaMedica = calendarioService.createCalendarios(historiaMedica);
		
		return historiaMedica;
	}

	@Override
	public HistoriaMedica update(HistoriaMedica historiaMedica)throws BusinessException {
		
		if(historiaMedica != null) return repository.save(historiaMedica);
		throw new BusinessException("No se puede actualizar la historia medica");
	}

	@Override
	public HistoriaMedica findById(Long id) throws BusinessException {
		
		Optional<HistoriaMedica> historiaOptional = repository.findById(id);
		if(historiaOptional.isPresent()) return historiaOptional.get();
		throw new BusinessException("No se pudo encontrar la Historia Medica con el id: " + id);
	}
	
	@Override
	public HistoriaMedica findByPacienteId(Long id) throws BusinessException {
		
		Optional<HistoriaMedica> historiaOptional = repository.findByPacienteId(id);
		if(historiaOptional.isPresent()) return historiaOptional.get();
		throw new BusinessException("No se pudo encontrar la Historia Medica con el paciente: " + id);
	}
	
	@Override
	public HistoriaMedica findByPacienteMail(String mail) throws BusinessException {
		
		UsuarioPaciente paciente = getUsuarioByMail(mail);
		Optional<HistoriaMedica> historiaOptional = repository.findByPacienteId(paciente.getId());
		if(historiaOptional.isPresent()) return historiaOptional.get();
		throw new BusinessException("No se pudo encontrar la Historia Medica con el paciente: " + paciente.getMail());
	}
	
	@Override
	public HistoriaMedica createCalendarioPersonalSalud(HistoriaMedica historiaMedica) throws BusinessException {
		
		return calendarioService.createCalendarioPersonalSalud(historiaMedica);
	}
	
	@Override
	public List<Calendario> getCalendariosByHistoriaMedicaId(Long historiaMedicaId) throws BusinessException {
		
		return calendarioService.findByHistoriaMedica(historiaMedicaId);
	}
	
	@Override
	public void addVisitaMedica(Long id, VisitaMedica visitaMedica) throws BusinessException {
		
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addVisitaMedica(visitaMedica);
	}
	
	@Override
	public void addProfesional(Long id, Profesional profesional) throws BusinessException {
		
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addProfesional(profesional);
	}
	
	@Override
	public void addCentroSalud(Long id, InstitucionSalud institucionSalud) throws BusinessException {
		
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addInstitucionSalud(institucionSalud);
	}

	@Override
	public void addTurno(Long id, Turno turno) throws BusinessException {
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addTurno(turno);
	}
	
	@Override
	public void addMedicamento(Long id, HistoriaMedicamento historiaMedicamento) throws BusinessException {
		
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addHistoriaMedicamento(historiaMedicamento);
	}
	
	@Override
	public void addSignoVitalCustom(Long id, SignoVitalCustom signoVitalCustom) throws BusinessException {
		
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addSignoVitalCustom(signoVitalCustom);
	}

	@Override
	public void addSignoVitalPaciente(Long id, SignoVitalPaciente signoVitalPaciente) throws BusinessException {
		
		HistoriaMedica historiaMedica = findById(id);
		historiaMedica.addSignoVitalPaciente(signoVitalPaciente);
	}

	@Override
	public void delete(HistoriaMedica historiaMedica) {
		
		repository.delete(historiaMedica);
	}

	@Override
	public void delete(Long id) {
		
		repository.deleteById(id);
	}

	@Override
	public long count() {
		
		return repository.count();
	}
	
	private UsuarioPaciente getUsuarioByMail(String mail) throws BusinessException{
		return (UsuarioPaciente)usuarioService.findByMail(mail);
	}
	
	private UsuarioPaciente getUsuarioById(Long id) throws BusinessException{
		return (UsuarioPaciente)usuarioService.findById(id);
	}

}
