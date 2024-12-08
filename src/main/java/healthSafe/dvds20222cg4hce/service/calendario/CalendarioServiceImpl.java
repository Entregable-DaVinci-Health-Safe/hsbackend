package healthSafe.dvds20222cg4hce.service.calendario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import healthSafe.dvds20222cg4hce.domain.calendario.Calendario;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEmbarazada;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioInfante;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioPersonalSalud;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.domain.calendario.VacunaUsuario;
import healthSafe.dvds20222cg4hce.domain.calendario.builders.CalendarioDirector;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.usuario.Genero;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.repository.calendario.CalendarioAdultoRepository;
import healthSafe.dvds20222cg4hce.repository.calendario.CalendarioEmbarazadaRepository;
import healthSafe.dvds20222cg4hce.repository.calendario.CalendarioInfanteRepository;
import healthSafe.dvds20222cg4hce.repository.calendario.CalendarioPersonalSaludRepository;
import healthSafe.dvds20222cg4hce.repository.calendario.CalendarioRepository;
import healthSafe.dvds20222cg4hce.utils.CalendarioUtils;

@Service
public class CalendarioServiceImpl implements CalendarioService{
	
	
	private CalendarioRepository calendarioRepository;
	private CalendarioInfanteRepository calendarioInfanteRepository;
	private CalendarioAdultoRepository calendarioAdultoRepository;
	private CalendarioEmbarazadaRepository calendarioEmbarazadaRepository;
	private CalendarioPersonalSaludRepository calendarioPersonalSaludRepository;
	private RangoEdadService rangoEdadService;
	private VacunaService vacunaService;
	private VacunaUsuarioService vacunaUsuarioService;
	private CalendarioEdadVacunaLinkService calendarioEdadVacunaLinkService;
	private CalendarioDirector calendarioDirector;
	
	@Autowired
	public CalendarioServiceImpl(final CalendarioRepository calendarioRepository,
			final CalendarioInfanteRepository calendarioInfanteRepository,
			final CalendarioAdultoRepository calendarioAdultoRepository,
			final CalendarioEmbarazadaRepository calendarioEmbarazadaRepository,
			final CalendarioPersonalSaludRepository calendarioPersonalSaludRepository,
			final RangoEdadService rangoEdadService,
			final VacunaService vacunaService,
			final VacunaUsuarioService vacunaUsuarioService,
			final CalendarioEdadVacunaLinkService calendarioEdadVacunaLinkService) {
		
		this.calendarioRepository = calendarioRepository;
		this.calendarioInfanteRepository = calendarioInfanteRepository;
		this.calendarioAdultoRepository = calendarioAdultoRepository;
		this.calendarioEmbarazadaRepository = calendarioEmbarazadaRepository;
		this.calendarioPersonalSaludRepository = calendarioPersonalSaludRepository;
		this.rangoEdadService = rangoEdadService;
		this.vacunaService = vacunaService;
		this.vacunaUsuarioService = vacunaUsuarioService;
		this.calendarioEdadVacunaLinkService = calendarioEdadVacunaLinkService;
		this.calendarioDirector = new CalendarioDirector();
	}
	
	@Override
	public List<Calendario> listCalendario() {
		
		return calendarioRepository.findAll();
	}

	@Override
	public List<CalendarioInfante> listInfante() {
		
		return calendarioInfanteRepository.findAll();
	}

	@Override
	public List<CalendarioAdulto> listAdulto() {
		
		return calendarioAdultoRepository.findAll();
	}

	@Override
	public List<CalendarioEmbarazada> listEmbarazada() {
		
		return calendarioEmbarazadaRepository.findAll();
	}

	@Override
	public Page<Calendario> listCalendario(Pageable pageable) {
		
		return calendarioRepository.findAll(pageable);
	}

	@Override
	public Page<CalendarioInfante> listInfante(Pageable pageable) {
		
		return calendarioInfanteRepository.findAll(pageable);
	}

	@Override
	public Page<CalendarioAdulto> listAdulto(Pageable pageable) {
		
		return calendarioAdultoRepository.findAll(pageable);
	}

	@Override
	public Page<CalendarioEmbarazada> listEmbarazada(Pageable pageable) {
		
		return calendarioEmbarazadaRepository.findAll(pageable);
	}
	
	@Override
	public HistoriaMedica createCalendarios(HistoriaMedica historiaMedica) throws BusinessException {
		
		
		List<RangoEdad> rangoEdadesInfante = rangoEdadService.findAllByTipoCalendario("INFANTE");
		List<Vacuna> vacunasInfante = vacunaService.findAllByTipoCalendario("INFANTE");
		CalendarioInfante infante = calendarioDirector.constructCalendarioInfante(historiaMedica, rangoEdadesInfante, vacunasInfante);
		historiaMedica.addCalendario(save(infante));
		
		List<RangoEdad> rangoEdadesAdulto = rangoEdadService.findAllByTipoCalendario("ADULTO");
		List<Vacuna> vacunasAdulto = vacunaService.findAllByTipoCalendario("ADULTO");
		CalendarioAdulto adulto = calendarioDirector.constructCalendarioAdulto(historiaMedica, rangoEdadesAdulto, vacunasAdulto);
		historiaMedica.addCalendario(save(adulto));
		
		if(historiaMedica.getPaciente().getGenero() == Genero.FEMENINO) {
			
			List<RangoEdad> rangoEdadesEmbarazada = rangoEdadService.findAllByTipoCalendario("EMBARAZADA");
			List<Vacuna> vacunasEmbarazada = vacunaService.findAllByTipoCalendario("EMBARAZADA");
			CalendarioEmbarazada embarazada = calendarioDirector.constructCalendarioEmbarazada(historiaMedica, rangoEdadesEmbarazada, vacunasEmbarazada);
			historiaMedica.addCalendario(save(embarazada));
		}
		
		return historiaMedica;
		
	}
	
	@Override
	public HistoriaMedica createCalendarioPersonalSalud(HistoriaMedica historiaMedica) throws BusinessException {
		
		List<RangoEdad> rangoEdadesInfante = rangoEdadService.findAllByTipoCalendario("PERSONAL_SALUD");
		List<Vacuna> vacunasSalud = vacunaService.findAllByTipoCalendario("PERSONAL_SALUD");
		CalendarioPersonalSalud personalSalud = calendarioDirector.constructCalendarioPersonalSalud(historiaMedica, rangoEdadesInfante, vacunasSalud);
		historiaMedica.addCalendario(save(personalSalud));
		
		return historiaMedica;
	}
	
	@Override
	public CalendarioPersonalSalud save(CalendarioPersonalSalud personalSalud) throws BusinessException {
		
		List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		if(personalSalud.getCalendarioEdadVacunaLinks() != null) {
			calendarioEdadVacunaLinks = getCalendarioEdadVacunaLinks(personalSalud.getCalendarioEdadVacunaLinks());
		}
		
		CalendarioPersonalSalud newPersonalSalud = CalendarioPersonalSalud.builder()
				.historiaMedica(personalSalud.getHistoriaMedica())
				.calendarioEdadVacunaLinks(calendarioEdadVacunaLinks)
				.build();
		
		newPersonalSalud = calendarioPersonalSaludRepository.save(newPersonalSalud);
		newPersonalSalud.addListRangoEdades(personalSalud.getRangoEdades());
		
		return update(newPersonalSalud);
	}

	@Override
	public CalendarioPersonalSalud update(CalendarioPersonalSalud personalSalud) throws BusinessException {
		
		if(personalSalud.getId() != null) return calendarioPersonalSaludRepository.save(personalSalud);
		throw new BusinessException("No se puede actualizar un calendario de salud que no ha sido creado");
	}

	@Override
	public CalendarioInfante save(CalendarioInfante infante) throws BusinessException {
		
		
		List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		if(infante.getCalendarioEdadVacunaLinks() != null) {
			calendarioEdadVacunaLinks = getCalendarioEdadVacunaLinks(infante.getCalendarioEdadVacunaLinks());
		}
		
		CalendarioInfante newInfante = CalendarioInfante.builder()
				.historiaMedica(infante.getHistoriaMedica())
				.calendarioEdadVacunaLinks(calendarioEdadVacunaLinks)
				.build();
		
		newInfante = calendarioInfanteRepository.save(newInfante);
		newInfante.addListRangoEdades(infante.getRangoEdades());
		
		return update(newInfante);
		
	}

	@Override
	public CalendarioInfante update(CalendarioInfante infante) throws BusinessException {
		
		if(infante.getId() != null) return calendarioInfanteRepository.save(infante);
		throw new BusinessException("No se puede actualizar un calendario infante que no ha sido creado");
	}

	@Override
	public CalendarioAdulto save(CalendarioAdulto adulto) throws BusinessException {
		
		List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		if(adulto.getCalendarioEdadVacunaLinks() != null) {
			calendarioEdadVacunaLinks = getCalendarioEdadVacunaLinks(adulto.getCalendarioEdadVacunaLinks());
		}
		
		CalendarioAdulto newAdulto = CalendarioAdulto.builder()
				.historiaMedica(adulto.getHistoriaMedica())
				.calendarioEdadVacunaLinks(calendarioEdadVacunaLinks)
				.build();
		
		newAdulto = calendarioAdultoRepository.save(newAdulto);
		newAdulto.addListRangoEdades(adulto.getRangoEdades());
		
		return update(newAdulto);
	}
	
	@Override
	public CalendarioAdulto update(CalendarioAdulto adulto) throws BusinessException {
		
		if(adulto.getId() != null) return calendarioAdultoRepository.save(adulto);
		throw new BusinessException("No se puede actualizar un calendario adulto que no ha sido creado");
	}

	@Override
	public CalendarioEmbarazada save(CalendarioEmbarazada embarazada) throws BusinessException {
		
		List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		if(embarazada.getCalendarioEdadVacunaLinks() != null) {
			calendarioEdadVacunaLinks = getCalendarioEdadVacunaLinks(embarazada.getCalendarioEdadVacunaLinks());
		}
		
		CalendarioEmbarazada newEmbarazada = CalendarioEmbarazada.builder()
				.historiaMedica(embarazada.getHistoriaMedica())
				.calendarioEdadVacunaLinks(calendarioEdadVacunaLinks)
				.build();
		
		newEmbarazada = calendarioEmbarazadaRepository.save(newEmbarazada);
		newEmbarazada.addListRangoEdades(embarazada.getRangoEdades());
		
		return update(newEmbarazada);
	}

	@Override
	public CalendarioEmbarazada update(CalendarioEmbarazada embarazada)
			throws BusinessException {
		
		if(embarazada.getId() != null) return calendarioEmbarazadaRepository.save(embarazada);
		throw new BusinessException("No se puede actualizar un calendario embarazada que no ha sido creado");
	}
	
	@Override
	public Calendario findById(Long id) throws BusinessException {
		Optional<Calendario> calendarioOptional = calendarioRepository.findById(id);
		if(calendarioOptional.isPresent()) return calendarioOptional.get();
		throw new BusinessException("No se pudo encontrar el calendario con el id: " + id);
	}
	
	@Override
	public List<Calendario> findByHistoriaMedica(Long historiaId) throws BusinessException {
		
		return calendarioRepository.findByHistoriaMedicaId(historiaId);
	}
	
	@Override 
	public Calendario addVacuna(Calendario calendario, CalendarioEdadVacunaLink calendarioEdadVacuna)
			throws BusinessException{
		
		RangoEdad rangoEdad = getRangoEdad(calendarioEdadVacuna.getRangoEdad().getId());
		VacunaUsuario vacunaUsuario = getVacunaUsuario(calendarioEdadVacuna.getVacunaUsuario(), 
														CalendarioUtils.getTipoCalendario(calendario));
		
		CalendarioEdadVacunaLink link = CalendarioEdadVacunaLink.builder()
				.calendario(calendario)
				.rangoEdad(rangoEdad)
				.vacunaUsuario(vacunaUsuario)
				.build();
		
		link = calendarioEdadVacunaLinkService.save(link);
		
		calendario.addCalendarioEdadVacunaLink(link);
		
		return calendario;
	}
	
	@Override 
	public Calendario removeVacuna(Calendario calendario, Long rangoEdadId, Long vacunaUsuarioId)
			throws BusinessException{
		
		CalendarioEdadVacunaLink link =
		calendarioEdadVacunaLinkService.findByCalendarioIdAndRangoEdadIdAndVacunaUsuarioId(
				calendario.getId(), rangoEdadId, vacunaUsuarioId);
		
		calendarioEdadVacunaLinkService.delete(link.getId());
		calendario.removeCalendarioEdadVacunaLink(link);
		return calendario;
	}

	@Override
	public void delete(Calendario calendario) {
		
		calendarioRepository.delete(calendario);
	}

	@Override
	public void delete(Long id) {
		
		calendarioRepository.deleteById(id);
	}

	@Override
	public long count() {
		
		return calendarioRepository.count();
	}
	
	private List<CalendarioEdadVacunaLink> getCalendarioEdadVacunaLinks(
			List<CalendarioEdadVacunaLink> requestCalendarioEdadVacunaLinks) throws BusinessException{
		
		List<CalendarioEdadVacunaLink> calendarioEdadVacunaLinks = new ArrayList<CalendarioEdadVacunaLink>();
		for(CalendarioEdadVacunaLink requestLink: requestCalendarioEdadVacunaLinks) {
			
			RangoEdad rangoEdad = getRangoEdad(requestLink.getRangoEdad().getId());
			VacunaUsuario vacunaUsuario = getVacunaUsuario(requestLink.getVacunaUsuario(), 
															CalendarioUtils.getTipoCalendario(requestLink.getCalendario()));
			CalendarioEdadVacunaLink link = CalendarioEdadVacunaLink.builder()
					.rangoEdad(rangoEdad)
					.vacunaUsuario(vacunaUsuario)
					.build();
			
			calendarioEdadVacunaLinks.add(link);
		}
		
		return calendarioEdadVacunaLinks;
	}

	private VacunaUsuario getVacunaUsuario(VacunaUsuario vacunaUsuario, String tipoCalendario) throws BusinessException {
		
		
		
		if(vacunaUsuario.getVacuna().getId() != null) {
			Vacuna vacuna = vacunaService.findById(vacunaUsuario.getVacuna().getId());
			if(vacunaService.thisVacunaIsForThisCalendario(vacuna, tipoCalendario)) {
				vacunaUsuario.setVacuna(vacuna);
				return vacunaUsuarioService.save(vacunaUsuario);
			}
			throw new BusinessException("La vacuna no corresponde al calendario");
		}
		throw new BusinessException("La vacuna es obligatoria");
	}

	private RangoEdad getRangoEdad(Long rangoEdadId) throws BusinessException{
		if(rangoEdadId != null) return rangoEdadService.findById(rangoEdadId);
		throw new BusinessException("El rango edad es obligatoria");
	}

}
