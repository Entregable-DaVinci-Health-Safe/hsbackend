package healthSafe.dvds20222cg4hce;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import healthSafe.dvds20222cg4hce.controller.request.HistoriaMedicaRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.AutorizacionRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.GrupoRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.PermisoRequest;
import healthSafe.dvds20222cg4hce.controller.request.autorizacion.RolRequest;
import healthSafe.dvds20222cg4hce.controller.request.calendario.CalendarioVacunaRequest;
import healthSafe.dvds20222cg4hce.controller.request.calendario.VacunaRequest;
import healthSafe.dvds20222cg4hce.controller.request.contacto.ContactoRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarIngresarRequest;
import healthSafe.dvds20222cg4hce.controller.request.grupofamiliar.GrupoFamiliarRequest;
import healthSafe.dvds20222cg4hce.controller.request.institucionsalud.InstitucionSaludRequest;
import healthSafe.dvds20222cg4hce.controller.request.institucionsalud.InstitucionSaludUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.medicamento.HistoriaMedicamentoRequest;
import healthSafe.dvds20222cg4hce.controller.request.medicamento.HistoriaMedicamentoUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.medicamento.MedicamentoRecordatorioRequest;
import healthSafe.dvds20222cg4hce.controller.request.prescripcion.EstudioRequest;
import healthSafe.dvds20222cg4hce.controller.request.prescripcion.PrescripcionRequest;
import healthSafe.dvds20222cg4hce.controller.request.prescripcion.RecetaRequest;
import healthSafe.dvds20222cg4hce.controller.request.profesional.EspecialidadRequest;
import healthSafe.dvds20222cg4hce.controller.request.profesional.ProfesionalRequest;
import healthSafe.dvds20222cg4hce.controller.request.signovital.SignoVitalCustomRequest;
import healthSafe.dvds20222cg4hce.controller.request.signovital.SignoVitalPacienteRequest;
import healthSafe.dvds20222cg4hce.controller.request.ubicacion.DireccionRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioPacienteRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioProfesionalRequest;
import healthSafe.dvds20222cg4hce.controller.request.usuario.UsuarioUpdateRequest;
import healthSafe.dvds20222cg4hce.controller.request.visitamedica.VisitaMedicaRequest;
import healthSafe.dvds20222cg4hce.controller.response.HistoriaMedicaResponse;
import healthSafe.dvds20222cg4hce.controller.response.InstitucionSaludResponse;
import healthSafe.dvds20222cg4hce.controller.response.ContactoResponse;
import healthSafe.dvds20222cg4hce.controller.response.DiagnosticoResponse;
import healthSafe.dvds20222cg4hce.controller.response.GrupoFamiliarResponse;
import healthSafe.dvds20222cg4hce.controller.response.GrupoNotificacionResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionComponentResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.AutorizacionResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.GrupoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.PermisoResponse;
import healthSafe.dvds20222cg4hce.controller.response.autorizacion.RolResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioAdultoResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioEmbarazadaResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioInfanteResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioPersonalSaludResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.CalendarioResponse;
import healthSafe.dvds20222cg4hce.controller.response.calendario.VacunaResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.HistoriaMedicamentoResponse;
import healthSafe.dvds20222cg4hce.controller.response.medicamento.MedicamentoRecordatorioResponse;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.EstudioResponse;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.PrescripcionResponse;
import healthSafe.dvds20222cg4hce.controller.response.prescripcion.RecetaResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.EspecialidadResponse;
import healthSafe.dvds20222cg4hce.controller.response.profesional.ProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalCustomResponse;
import healthSafe.dvds20222cg4hce.controller.response.signovital.SignoVitalPacienteResponse;
import healthSafe.dvds20222cg4hce.controller.response.ubicacion.DireccionResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioPacienteResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioProfesionalResponse;
import healthSafe.dvds20222cg4hce.controller.response.usuario.UsuarioResponse;
import healthSafe.dvds20222cg4hce.controller.response.visitamedica.VisitaMedicaResponse;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Autorizacion;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Grupo;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Permiso;
import healthSafe.dvds20222cg4hce.domain.autorizacion.Rol;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioAdulto;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEdadVacunaLink;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioEmbarazada;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioInfante;
import healthSafe.dvds20222cg4hce.domain.calendario.CalendarioPersonalSalud;
import healthSafe.dvds20222cg4hce.domain.calendario.RangoEdad;
import healthSafe.dvds20222cg4hce.domain.calendario.Vacuna;
import healthSafe.dvds20222cg4hce.domain.calendario.VacunaAplicada;
import healthSafe.dvds20222cg4hce.domain.calendario.VacunaUsuario;
import healthSafe.dvds20222cg4hce.domain.contacto.Contacto;
import healthSafe.dvds20222cg4hce.domain.historia.InstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.historia.Diagnostico;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoFamiliar;
import healthSafe.dvds20222cg4hce.domain.historia.GrupoNotificacion;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.historia.VisitaMedica;
import healthSafe.dvds20222cg4hce.domain.medicamento.HistoriaMedicamento;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoProducto;
import healthSafe.dvds20222cg4hce.domain.medicamento.MedicamentoRecordatorio;
import healthSafe.dvds20222cg4hce.domain.medicamento.TipoFrecuencia;
import healthSafe.dvds20222cg4hce.domain.medicamento.TipoPresentacion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.ArgReceta;
import healthSafe.dvds20222cg4hce.domain.prescripcion.Prescripcion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.factory.ArgFactory;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.PaisPrescripcion;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.TipoArgEstudio;
import healthSafe.dvds20222cg4hce.domain.prescripcion.tipoenum.TipoArgReceta;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalCustom;
import healthSafe.dvds20222cg4hce.domain.signovital.SignoVitalPaciente;
import healthSafe.dvds20222cg4hce.domain.signovital.TipoSignoVital;
import healthSafe.dvds20222cg4hce.domain.ubicacion.Direccion;
import healthSafe.dvds20222cg4hce.domain.ubicacion.DireccionInstitucionSalud;
import healthSafe.dvds20222cg4hce.domain.usuario.Profesional;
import healthSafe.dvds20222cg4hce.domain.usuario.TipoMatricula;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioProfesional;
import healthSafe.dvds20222cg4hce.utils.AutorizacionUtils;
import healthSafe.dvds20222cg4hce.utils.CalendarioUtils;
import healthSafe.dvds20222cg4hce.utils.InstitucionSaludUtils;
import healthSafe.dvds20222cg4hce.utils.ContactoUtils;
import healthSafe.dvds20222cg4hce.utils.DateUtils;
import healthSafe.dvds20222cg4hce.utils.DiagnosticoUtils;
import healthSafe.dvds20222cg4hce.utils.DireccionUtils;
import healthSafe.dvds20222cg4hce.utils.GrupoFamiliarUtils;
import healthSafe.dvds20222cg4hce.utils.HistoriaMedicaUtils;
import healthSafe.dvds20222cg4hce.utils.MedicamentoUtils;
import healthSafe.dvds20222cg4hce.utils.PasswordGenerator;
import healthSafe.dvds20222cg4hce.utils.PrescripcionUtils;
import healthSafe.dvds20222cg4hce.utils.ProfesionalUtils;
import healthSafe.dvds20222cg4hce.utils.SignoVitalUtils;
import healthSafe.dvds20222cg4hce.utils.TurnoUtils;
import healthSafe.dvds20222cg4hce.utils.UsuarioUtils;
import healthSafe.dvds20222cg4hce.utils.VisitaMedicaUtils;
import healthSafe.dvds20222cg4hce.domain.usuario.Especialidad;
import healthSafe.dvds20222cg4hce.domain.usuario.Genero;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Configuration
public class OrikaConfiguration {
	
	private final ObjectMapper objectMapper;
	
	public OrikaConfiguration() {
		objectMapper = new ObjectMapper();
	}
	
	@Bean
	public MapperFacade mapper() {
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		
		//USUARIO PACIENTE
		
		mapperFactory.classMap(UsuarioPaciente.class, UsuarioPacienteResponse.class)
		.customize(new CustomMapper<UsuarioPaciente, UsuarioPacienteResponse>(){
			public void mapAtoB(final UsuarioPaciente paciente, final UsuarioPacienteResponse response, final MappingContext context) {
				
				
				List<AutorizacionComponentResponse> autorizacionesComponentesResponse = null;
				if(paciente.getAutorizacionesComponentes() != null) {
					autorizacionesComponentesResponse = AutorizacionUtils.getListAutorizacionComponentResponse(paciente.getAutorizacionesComponentes());
				}
				
				List<DireccionResponse> direcciones = new ArrayList<DireccionResponse>();
				if(paciente.getDirecciones() != null) {
					direcciones = DireccionUtils.getListDireccionUsuarioResponse(paciente.getDirecciones());
				}
				
				List<ContactoResponse> contactos = new ArrayList<ContactoResponse>();
				if(paciente.getContactos() != null){
					contactos = ContactoUtils.getListContactosUsuariosResponse(paciente.getContactos());
				}
						

				response.setImgPerfil(paciente.getImgPerfil());
				response.setDocumento(paciente.getDocumento());				
				response.setNombre(paciente.getNombre());
				response.setApellido(paciente.getApellido());
				response.setDirecciones(direcciones);
				response.setContactos(contactos);
				response.setActivo(paciente.getActivo());
				response.setMail(paciente.getMail());
				response.setFechaNacimiento(DateUtils.getStringDate(paciente.getFechaNacimiento()));
				response.setGenero(paciente.getGenero().getDescripcion());
				response.setAutorizacionesComponentes(autorizacionesComponentesResponse);
			}
		}).register();
		
		mapperFactory.classMap(UsuarioPacienteRequest.class, UsuarioPaciente.class)
		.customize(new CustomMapper<UsuarioPacienteRequest, UsuarioPaciente>(){
			public void mapAtoB(final UsuarioPacienteRequest request, final UsuarioPaciente paciente, final MappingContext context) {
				
				paciente.setDocumento(request.getDocumento());
				paciente.setNombre(request.getNombre());
				paciente.setApellido(request.getApellido());
				paciente.setMail(request.getMail().toLowerCase());
				paciente.setPassword(request.getPassword());
				paciente.setFechaNacimiento(DateUtils.getFechaTimestamp(request.getFechaNacimiento()));
				paciente.setActivo(false);
				
				Genero genero = Genero.valueOf(request.getGenero());
				paciente.setGenero(genero);
				
			}
		}).register();
		
		mapperFactory.classMap(UsuarioUpdateRequest.class, UsuarioPaciente.class)
		.customize(new CustomMapper<UsuarioUpdateRequest, UsuarioPaciente>(){
			public void mapAtoB(final UsuarioUpdateRequest request, final UsuarioPaciente paciente, final MappingContext context) {
				paciente.setNombre(request.getNombre());
				paciente.setApellido(request.getApellido());
				paciente.setFechaNacimiento(DateUtils.getFechaTimestamp(request.getFechaNacimiento()));
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//USUARIO PROFESIONAL
		
		mapperFactory.classMap(UsuarioProfesional.class, UsuarioProfesionalResponse.class)
		.customize(new CustomMapper<UsuarioProfesional, UsuarioProfesionalResponse>(){
			public void mapAtoB(final UsuarioProfesional profesional, final UsuarioProfesionalResponse response, final MappingContext context) {
				
				List<DireccionResponse> direcciones = new ArrayList<DireccionResponse>();
				if(profesional.getDirecciones() != null) {
					direcciones = DireccionUtils.getListDireccionUsuarioResponse(profesional.getDirecciones());
				}
				
				List<ContactoResponse> contactos = new ArrayList<ContactoResponse>();
				if(profesional.getContactos() != null){
					contactos = ContactoUtils.getListContactosUsuariosResponse(profesional.getContactos());
				}
				
				response.setImgPerfil(profesional.getImgPerfil());
				response.setNombre(profesional.getNombre());
				response.setApellido(profesional.getApellido());
				response.setDirecciones(direcciones);
				response.setContactos(contactos);
				response.setActivo(profesional.getActivo());
				response.setMatricula(profesional.getMatricula());
				response.setMail(profesional.getMail());
				response.setFechaNacimiento(DateUtils.getStringDate(profesional.getFechaNacimiento()));
				response.setGenero(profesional.getGenero().getDescripcion());
			}
		}).register();
		
		mapperFactory.classMap(UsuarioProfesionalRequest.class, UsuarioProfesional.class)
		.customize(new CustomMapper<UsuarioProfesionalRequest, UsuarioProfesional>(){
			public void mapAtoB(final UsuarioProfesionalRequest request, final UsuarioProfesional profesional, final MappingContext context) {
				
				profesional.setNombre(request.getNombre());
				profesional.setApellido(request.getApellido());
				profesional.setMail(request.getMail());
				profesional.setMatricula(request.getMatricula());
				profesional.setPassword(request.getPassword());
				profesional.setFechaNacimiento(DateUtils.getFechaTimestamp(request.getFechaNacimiento()));
				profesional.setActivo(false);
				
				Genero genero = Genero.valueOf(request.getGenero());
				profesional.setGenero(genero);
				

			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//PROFESIONAL
		
		mapperFactory.classMap(Profesional.class, ProfesionalResponse.class)
		.customize(new CustomMapper<Profesional, ProfesionalResponse>(){
			public void mapAtoB(final Profesional profesional, final ProfesionalResponse response, final MappingContext context) {
				List<DireccionResponse> direcciones = new ArrayList<DireccionResponse>();
				if(profesional.getDirecciones() != null) {
					direcciones = DireccionUtils.getListDireccionProfesionalResponse(profesional.getDirecciones());
				}
				
				List<ContactoResponse> contactos = new ArrayList<ContactoResponse>();
				if(profesional.getContactos() != null){
					contactos = ContactoUtils.getListContactosProfesionalesResponse(profesional.getContactos());
				}
				
				List<EspecialidadResponse> especialidades = new ArrayList<EspecialidadResponse>();
				if(profesional.getEspecialidades() != null){
					especialidades = ProfesionalUtils.getListEspecialidadesResponse(profesional.getEspecialidades());
				}
				
				response.setId(profesional.getId());
				response.setNombre(profesional.getNombre());
				response.setTipoMatricula(profesional.getTipoMatricula().getDescripcion());
				response.setMatricula(profesional.getMatricula());
				response.setDirecciones(direcciones);
				response.setContactos(contactos);
				response.setEspecialidades(especialidades);
				response.setActivo(profesional.getActivo());
			}
		}).register();
		
		mapperFactory.classMap(ProfesionalRequest.class, Profesional.class)
		.customize(new CustomMapper<ProfesionalRequest, Profesional>(){
			public void mapAtoB(final ProfesionalRequest request, final Profesional profesional, final MappingContext context) {
				HistoriaMedica historia = HistoriaMedica.builder().id(request.getIdHistoriaMedica()).build();
				TipoMatricula tipoMatricula = TipoMatricula.valueOf(request.getTipoMatricula().toUpperCase());
				profesional.setNombre(request.getNombre());
				profesional.setTipoMatricula(tipoMatricula);
				profesional.setMatricula(request.getMatricula());
				profesional.setHistoriaMedica(historia);
				profesional.setActivo(true);
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//PRESCRIPCION
		
		mapperFactory.classMap(Prescripcion.class, PrescripcionResponse.class)
		.customize(new CustomMapper<Prescripcion, PrescripcionResponse>(){
			public void mapAtoB(final Prescripcion prescripcion, final PrescripcionResponse response, final MappingContext context) {
				List<EstudioResponse> estudios = new ArrayList<EstudioResponse>();
				if(prescripcion.getEstudios() != null) {
					estudios = PrescripcionUtils.getListEstudiosResponse(prescripcion.getEstudios());
				}
				
				List<RecetaResponse> recetas = new ArrayList<RecetaResponse>();
				if(prescripcion.getRecetas() != null) {
					recetas = PrescripcionUtils.getListRecetasResponse(prescripcion.getRecetas());
				}
				
				response.setId(prescripcion.getId());
				response.setPais(prescripcion.getPaisPrescripcion().getDescripcion());
				response.setEstudios(estudios);
				response.setRecetas(recetas);
			}
		}).register();
		
		mapperFactory.classMap(PrescripcionRequest.class, Prescripcion.class)
		.customize(new CustomMapper<PrescripcionRequest, Prescripcion>(){
			public void mapAtoB(final PrescripcionRequest request, final Prescripcion prescripcion, final MappingContext context) {
				
				PaisPrescripcion pais = PaisPrescripcion.valueOf(request.getPais().toUpperCase());
				prescripcion.setPaisPrescripcion(pais);
				
				if(pais.getDescripcion().equals("Argentina")) {
					prescripcion.setPrescripcionComponentFactory(new ArgFactory());
				}
				
				VisitaMedica visita = VisitaMedica.builder().id(request.getVisitaMedicaId()).build();
				prescripcion.setVisitaMedica(visita);	
			}
		}).register();
		//------------------------------------------------------------------------------
		
		//ESTUDIO
		
		mapperFactory.classMap(EstudioRequest.class, ArgEstudio.class)
		.customize(new CustomMapper<EstudioRequest, ArgEstudio>(){
			public void mapAtoB(final EstudioRequest request, final ArgEstudio estudio, final MappingContext context) {
				TipoArgEstudio tipo = TipoArgEstudio.valueOf(request.getTipo().toUpperCase());
				estudio.setTipoEstudio(tipo);
				estudio.setUrl(request.getUrl());
				estudio.setDescripcion(request.getDescripcion());
				estudio.setFechaCreado(DateUtils.getFechaNowTimestamp());
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//RECETA
		
		mapperFactory.classMap(RecetaRequest.class, ArgReceta.class)
		.customize(new CustomMapper<RecetaRequest, ArgReceta>(){
			public void mapAtoB(final RecetaRequest request, final ArgReceta receta, final MappingContext context) {
				TipoArgReceta tipo = TipoArgReceta.valueOf(request.getTipo().toUpperCase());
				receta.setTipoReceta(tipo);
				receta.setUrl(request.getUrl());
				receta.setDescripcion(request.getDescripcion());
				receta.setFechaCreado(DateUtils.getFechaNowTimestamp());
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//VISITA MEDICA
		
		mapperFactory.classMap(VisitaMedicaRequest.class, VisitaMedica.class)
		.customize(new CustomMapper<VisitaMedicaRequest, VisitaMedica>(){
			public void mapAtoB(final VisitaMedicaRequest request, final VisitaMedica visita, final MappingContext context) {
				
				HistoriaMedica historia = null;
				Diagnostico diagnostico = null;
				Profesional profesional = null;
				Especialidad especialidad = null;
				InstitucionSalud institucionSalud = null;
				
				if(request.getHistoriaMedicaId() != null) {
					historia = HistoriaMedica.builder().id(request.getHistoriaMedicaId()).build();
				}
				
				if(request.getDiagnosticoId() != null) {
					diagnostico = Diagnostico.builder().id(request.getDiagnosticoId()).build();
				}
				
				if(request.getProfesionalId() != null) {
					profesional = Profesional.builder().id(request.getProfesionalId()).build();
				}
				
				if(request.getEspecialidadId() != null) {
					especialidad = Especialidad.builder().id(request.getEspecialidadId()).build();
				}
					
				if(request.getInstitucionSaludId() != null) {
					institucionSalud = InstitucionSalud.builder().id(request.getInstitucionSaludId()).build();
				}
				
				visita.setAtencionVirtual(request.getAtencionVirtual());
				visita.setFechaVisita(DateUtils.getFechaTimestamp(request.getFechaVisita()));
				visita.setIndicaciones(request.getIndicaciones());
				visita.setHistoriaMedica(historia);
				visita.setDiagnostico(diagnostico);
				visita.setInstitucionSalud(institucionSalud);
				visita.setProfesional(profesional);
				visita.setEspecialidad(especialidad);
				visita.setActivo(true);				
			}
		}).register();
		
		mapperFactory.classMap(VisitaMedica.class, VisitaMedicaResponse.class)
		.customize(new CustomMapper<VisitaMedica, VisitaMedicaResponse>(){
			public void mapAtoB(final VisitaMedica visita, final VisitaMedicaResponse response, final MappingContext context) {
				
				List<PrescripcionResponse> prescripcionesResponse = new ArrayList<PrescripcionResponse>();
				InstitucionSaludResponse institucionSaludResponse = null;
				ProfesionalResponse profesionalResponse = null;
				EspecialidadResponse especialidadResponse = null;
				DiagnosticoResponse diagnosticoResponse = null;
				
				if(visita.getPrescripciones() != null) {
					prescripcionesResponse = PrescripcionUtils.getListPrescripcionesResponse(visita.getPrescripciones());
				}
				
				if(visita.getInstitucionSalud() != null) {
					institucionSaludResponse = InstitucionSaludUtils.getInstitucionSaludResponse(visita.getInstitucionSalud());
				}
				
				if(visita.getProfesional() != null) {
					profesionalResponse = ProfesionalUtils.getProfesionalResponse(visita.getProfesional());
				}
				
				if(visita.getEspecialidad() != null) {
					especialidadResponse = ProfesionalUtils.getEspecialidadResponse(visita.getEspecialidad());
				}
				
				if(visita.getDiagnostico() != null) {
					diagnosticoResponse = DiagnosticoUtils.getDiagnosticoResponse(visita.getDiagnostico());
				}
				
				response.setId(visita.getId());
				response.setAtencionVirtual(visita.getAtencionVirtual());
				response.setFechaVisita(DateUtils.getStringDate(visita.getFechaVisita()));
				response.setIndicaciones(visita.getIndicaciones());
				response.setPrescripciones(prescripcionesResponse);
				response.setInstitucionSalud(institucionSaludResponse);
				response.setProfesional(profesionalResponse);
				response.setEspecialidad(especialidadResponse);
				response.setDiagnostico(diagnosticoResponse);
				response.setActivo(visita.getActivo());
				
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//HISTORIA MEDICA
		
		mapperFactory.classMap(HistoriaMedicaRequest.class, HistoriaMedica.class)
		.customize(new CustomMapper<HistoriaMedicaRequest, HistoriaMedica>(){
			public void mapAtoB(final HistoriaMedicaRequest request, final HistoriaMedica historia, final MappingContext context) {
				UsuarioPaciente paciente = UsuarioPaciente.builder().mail(request.getMailPaciente()).build();
				historia.setPaciente(paciente);
			}
		}).register();
		
		mapperFactory.classMap(HistoriaMedica.class, HistoriaMedicaResponse.class)
		.customize(new CustomMapper<HistoriaMedica, HistoriaMedicaResponse>(){
			public void mapAtoB(final HistoriaMedica historia, final HistoriaMedicaResponse response, final MappingContext context) {
				response.setId(historia.getId());
				
				List<VisitaMedicaResponse> visitasMedicas = new ArrayList<VisitaMedicaResponse>();
				if(historia.getVisitasMedicas() != null) {
					visitasMedicas = VisitaMedicaUtils.getListVisitaMedicaResponse(historia.getVisitasMedicas());
				}
				
				List<ProfesionalResponse> profesionales = new ArrayList<ProfesionalResponse>();
				if(historia.getProfesionales() != null) {
					profesionales = ProfesionalUtils.getListProfesionalesActivosResponse(historia.getProfesionales());
				}
				
				List<InstitucionSaludResponse> institucionesSalud = new ArrayList<InstitucionSaludResponse>();
				if(historia.getInstitucionesSalud() != null) {
					institucionesSalud = InstitucionSaludUtils.getListInstitucionesSaludActivosResponse(historia.getInstitucionesSalud());
				}
				
				List<CalendarioResponse> calendarios = new ArrayList<CalendarioResponse>();
				if(historia.getCalendarios() != null) {
					calendarios = CalendarioUtils.getListCalendariosResponse(historia.getCalendarios());
				}
				
				List<HistoriaMedicamentoResponse> historiaMedicamentos = new ArrayList<HistoriaMedicamentoResponse>();
				if(historia.getHistoriaMedicamentos() != null) {
					historiaMedicamentos = MedicamentoUtils.getListHistoriaMedicamentoProductoResponse(historia.getHistoriaMedicamentos());
				}
				
				List<Long> gruposFamiliaresIds = new ArrayList<Long>();
				if(historia.getGruposFamiliares() != null) {
					historia.getGruposFamiliares().stream().forEach(gfm -> gruposFamiliaresIds.add(gfm.getId()));
				}
				
				List<SignoVitalCustomResponse> signosVitalesCustoms = new ArrayList<SignoVitalCustomResponse>();
				if(historia.getSignosVitalesCustoms() != null) {
					signosVitalesCustoms = SignoVitalUtils.getListSignosVitalesCustomsResponse(historia.getSignosVitalesCustoms());
				}
				
				List<SignoVitalPacienteResponse> signosVitalesPaciente = new ArrayList<SignoVitalPacienteResponse>();
				if(historia.getSignosVitalesPaciente() != null) {
					signosVitalesPaciente = SignoVitalUtils.getListSignosVitalesPacienteResponse(historia.getSignosVitalesPaciente());
				}
				
				response.setVisitasMedicas(visitasMedicas);
				response.setProfesionales(profesionales);
				response.setInstitucionesSalud(institucionesSalud);			
				response.setCalendarios(calendarios);
				response.setMedicamentos(historiaMedicamentos);
				response.setPaciente(UsuarioUtils.getUsuarioPacienteResponse(historia.getPaciente()));
				response.setGruposFamiliaresIds(gruposFamiliaresIds);
				response.setSignosVitalesCustoms(signosVitalesCustoms);
				response.setSignosVitalesPaciente(signosVitalesPaciente);
				response.setTurnos(TurnoUtils.getListResponseFromListDomain(historia.getTurnos()));
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//CONTACTO
		
		mapperFactory.classMap(ContactoRequest.class, Contacto.class)
		.customize(new CustomMapper<ContactoRequest, Contacto>() {
			public void mapAtoB(final ContactoRequest request, final Contacto contacto, final MappingContext context) {
				contacto.setMailAlternativo(request.getMailAlternativo());
				contacto.setTelefono(request.getTelefono());
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//DIRECCION
		
		mapperFactory.classMap(DireccionRequest.class, Direccion.class)
		.customize(new CustomMapper<DireccionRequest, Direccion>() {
			public void mapAtoB(final DireccionRequest request, final Direccion direccion, final MappingContext context) {
				
				direccion.setDireccion(request.getDireccion());
				direccion.setProvincia(request.getProvincia());
				direccion.setLocalidad(request.getLocalidad());
				direccion.setBarrio(request.getBarrio());
				direccion.setDepartamento(request.getDepartamento());
				direccion.setPiso(request.getPiso());
				direccion.setReferencia(request.getReferencia());
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//CENTRO SALUD
		
		mapperFactory.classMap(InstitucionSaludRequest.class, InstitucionSalud.class)
		.customize(new CustomMapper<InstitucionSaludRequest, InstitucionSalud>(){
			public void mapAtoB(final InstitucionSaludRequest request, final InstitucionSalud InstitucionSalud, final MappingContext context) {
				
				HistoriaMedica historia = HistoriaMedica.builder().id(request.getHistoriaMedicaId()).build();
				DireccionInstitucionSalud direccion = DireccionInstitucionSalud.builder()
												.direccion(request.getDireccion().getDireccion())
												.provincia(request.getDireccion().getProvincia())
												.localidad(request.getDireccion().getLocalidad())
												.barrio(request.getDireccion().getBarrio())
												.piso(request.getDireccion().getPiso())
												.departamento(request.getDireccion().getDepartamento())
												.referencia(request.getDireccion().getReferencia())
												.build();
				
				InstitucionSalud.setNombre(request.getNombre());
				InstitucionSalud.setHistoriaMedica(historia);
				InstitucionSalud.setDireccion(direccion);
				InstitucionSalud.setActivo(true);
				
			}
		}).register();
		
		mapperFactory.classMap(InstitucionSaludUpdateRequest.class, InstitucionSalud.class)
		.customize(new CustomMapper<InstitucionSaludUpdateRequest, InstitucionSalud>(){
			public void mapAtoB(final InstitucionSaludUpdateRequest request, final InstitucionSalud InstitucionSalud, final MappingContext context) {
				
				InstitucionSalud.setNombre(request.getNombre());
				
			}
		}).register();
		
		mapperFactory.classMap(InstitucionSalud.class, InstitucionSaludResponse.class)
		.customize(new CustomMapper<InstitucionSalud, InstitucionSaludResponse>(){
			public void mapAtoB(final InstitucionSalud InstitucionSalud, final InstitucionSaludResponse response, final MappingContext context) {
				
				DireccionResponse direccionResponse = null;
				if(InstitucionSalud.getDireccion() != null) {
					direccionResponse =  DireccionUtils.getDireccionResponse(InstitucionSalud.getDireccion());
				}
				
				response.setId(InstitucionSalud.getId());
				response.setNombre(InstitucionSalud.getNombre());
				response.setContactos(ContactoUtils.getListContactosCentrosSaludResponse(InstitucionSalud.getContactos()));
				response.setDireccion(direccionResponse);
				response.setProfesionales(ProfesionalUtils.getListProfesionalesActivosResponse(InstitucionSalud.getProfesionales()));
				response.setActivo(InstitucionSalud.getActivo());
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//CALENDARIO INFANTE
		
		mapperFactory.classMap(CalendarioInfante.class, CalendarioInfanteResponse.class)
		.customize(new CustomMapper<CalendarioInfante, CalendarioInfanteResponse>(){
			public void mapAtoB(final CalendarioInfante infante, final CalendarioInfanteResponse response, final MappingContext context) {
				
				response.setId(infante.getId());
				response.setTipo("INFANTE");
				response.setRangoEdades(CalendarioUtils.getListRangoEdadResponse(infante.getRangoEdades(), infante.getCalendarioEdadVacunaLinks()));		
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//CALENDARIO ADULTO
		
		mapperFactory.classMap(CalendarioAdulto.class, CalendarioAdultoResponse.class)
		.customize(new CustomMapper<CalendarioAdulto, CalendarioAdultoResponse>(){
			public void mapAtoB(final CalendarioAdulto adulto, final CalendarioAdultoResponse response, final MappingContext context) {
						
				response.setId(adulto.getId());
				response.setTipo("ADULTO");
				response.setRangoEdades(CalendarioUtils.getListRangoEdadResponse(adulto.getRangoEdades(), adulto.getCalendarioEdadVacunaLinks()));
			}
		}).register();
				
		//------------------------------------------------------------------------------
				
		//CALENDARIO EMBARAZADA
				
		mapperFactory.classMap(CalendarioEmbarazada.class, CalendarioEmbarazadaResponse.class)
		.customize(new CustomMapper<CalendarioEmbarazada, CalendarioEmbarazadaResponse>(){
			public void mapAtoB(final CalendarioEmbarazada embarazada, final CalendarioEmbarazadaResponse response, final MappingContext context) {
						
				response.setId(embarazada.getId());
				response.setTipo("EMBARAZADA");
				response.setRangoEdades(CalendarioUtils.getListRangoEdadResponse(embarazada.getRangoEdades(), embarazada.getCalendarioEdadVacunaLinks()));
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//CALENDARIO PERSONAL SALUD
		
		mapperFactory.classMap(CalendarioPersonalSalud.class, CalendarioPersonalSaludResponse.class)
		.customize(new CustomMapper<CalendarioPersonalSalud, CalendarioPersonalSaludResponse>(){
			public void mapAtoB(final CalendarioPersonalSalud personalSalud, final CalendarioPersonalSaludResponse response, final MappingContext context) {
								
				response.setId(personalSalud.getId());
				response.setTipo("PERSONAL_SALUD");
				response.setRangoEdades(CalendarioUtils.getListRangoEdadResponse(personalSalud.getRangoEdades(), personalSalud.getCalendarioEdadVacunaLinks()));
			}
		}).register();
				
		//------------------------------------------------------------------------------
		
		//CALENDARIO - EDAD - VACUNA
		
		mapperFactory.classMap(CalendarioVacunaRequest.class, CalendarioEdadVacunaLink.class)
		.customize(new CustomMapper<CalendarioVacunaRequest, CalendarioEdadVacunaLink>(){
			public void mapAtoB(final CalendarioVacunaRequest request, final CalendarioEdadVacunaLink calendarioEdadVacuna, final MappingContext context) {
				VacunaUsuario vacunaUsuario = VacunaUsuario.builder()
						.fecha(DateUtils.getFechaTimestamp(request.getFechaAplicada()))
						.aplicada(VacunaAplicada.valueOf(request.getAplicada().toUpperCase()))
						.numeroDosis(request.getNumeroDosis())
						.vacuna(Vacuna.builder().id(request.getVacunaId()).build())
						.build();
				
				calendarioEdadVacuna.setVacunaUsuario(vacunaUsuario);
				calendarioEdadVacuna.setRangoEdad(RangoEdad.builder().id(request.getRangoEdadId()).build());

			}
		}).register();
				
		//------------------------------------------------------------------------------
		
		//VACUNA
		
		mapperFactory.classMap(VacunaRequest.class, Vacuna.class)
		.customize(new CustomMapper<VacunaRequest, Vacuna>(){
			public void mapAtoB(final VacunaRequest request, final Vacuna vacuna, final MappingContext context) {
				
				vacuna.setNombre(request.getNombre());
				vacuna.setDescripcion(request.getDescripcion());
				vacuna.setObligatoria(request.getObligatoria());
				vacuna.setCantidadDosis(request.getCantidadDosis());
			}
			
		}).register();
		
		
		mapperFactory.classMap(Vacuna.class, VacunaResponse.class)
		.customize(new CustomMapper<Vacuna, VacunaResponse>(){
			public void mapAtoB(final Vacuna vacuna, final VacunaResponse response, final MappingContext context) {
				
				response.setId(vacuna.getId());
				response.setNombre(vacuna.getNombre());
				response.setDescripcion(vacuna.getDescripcion());
				response.setObligatoria(vacuna.getObligatoria());
				response.setCantidadDosis(vacuna.getCantidadDosis());
			}
			
		}).register();
		
		//------------------------------------------------------------------------------
		
		//ESPECIALIDAD
		
		mapperFactory.classMap(EspecialidadRequest.class, Especialidad.class)
		.customize(new CustomMapper<EspecialidadRequest, Especialidad>(){
			public void mapAtoB(final EspecialidadRequest request, final Especialidad especialidad, final MappingContext context) {
				
				especialidad.setNombre(request.getNombre());
			}
			
		}).register();
		
		mapperFactory.classMap(Especialidad.class, EspecialidadResponse.class)
		.customize(new CustomMapper<Especialidad, EspecialidadResponse>(){
			public void mapAtoB(final Especialidad especialidad, final EspecialidadResponse response, final MappingContext context) {
				
				response.setId(especialidad.getId());
				response.setNombre(especialidad.getNombre());;
			}
			
		}).register();
		
		//------------------------------------------------------------------------------
		
		//HISTORIA - MEDICAMENTO
		
		mapperFactory.classMap(HistoriaMedicamentoRequest.class, HistoriaMedicamento.class)
		.customize(new CustomMapper<HistoriaMedicamentoRequest, HistoriaMedicamento>(){
			public void mapAtoB(final HistoriaMedicamentoRequest request, final HistoriaMedicamento historiaMedicamento, final MappingContext context) {
				
				HistoriaMedica historia = HistoriaMedica.builder().id(request.getHistoriaMedicaId()).build();
				MedicamentoProducto medicamentoProducto = MedicamentoProducto.builder().id(request.getMedicamentoProductoId()).build();
				
				TipoPresentacion presentacion = TipoPresentacion.valueOf(request.getPresentacion().toUpperCase());
				
				historiaMedicamento.setComentarios(request.getComentarios());
				historiaMedicamento.setCantidad(request.getCantidad());
				historiaMedicamento.setPresentacion(presentacion);
				historiaMedicamento.setHistoriaMedica(historia);
				historiaMedicamento.setMedicamento(medicamentoProducto);
			}
			
		}).register();
		
		mapperFactory.classMap(HistoriaMedicamentoUpdateRequest.class, HistoriaMedicamento.class)
		.customize(new CustomMapper<HistoriaMedicamentoUpdateRequest, HistoriaMedicamento>(){
			public void mapAtoB(final HistoriaMedicamentoUpdateRequest request, final HistoriaMedicamento historiaMedicamento, final MappingContext context) {
				
				historiaMedicamento.setComentarios(request.getComentarios());
			}
			
		}).register();
		
		mapperFactory.classMap(HistoriaMedicamento.class, HistoriaMedicamentoResponse.class)
		.customize(new CustomMapper<HistoriaMedicamento, HistoriaMedicamentoResponse>(){
			public void mapAtoB(final HistoriaMedicamento historiaMedicamento, HistoriaMedicamentoResponse response, final MappingContext context) {
				
				response.setId(historiaMedicamento.getId());
				response.setComentarios(historiaMedicamento.getComentarios());;
				response.setCantidad(historiaMedicamento.getCantidad());
				response.setPresentacion(historiaMedicamento.getPresentacion().getDescripcion());
				response.setMedicamento(MedicamentoUtils.getMedicamentoWithProductoResponse(historiaMedicamento.getMedicamento()));
			}
			
		}).register();
		
		mapperFactory.classMap(MedicamentoRecordatorioRequest.class, MedicamentoRecordatorio.class)
		.customize(new CustomMapper<MedicamentoRecordatorioRequest, MedicamentoRecordatorio>(){
			public void mapAtoB(final MedicamentoRecordatorioRequest request, final MedicamentoRecordatorio recordatorio, final MappingContext context) {
				TipoFrecuencia frecuencia = TipoFrecuencia.valueOf(request.getTipoFrecuencia().toUpperCase());
				HistoriaMedicamento medicamento = HistoriaMedicamento.builder().id(request.getHistoriaMedicamentoId()).build();
				
				recordatorio.setDosis(request.getDosis());
				recordatorio.setEsCronico(request.getEsCronico());
				recordatorio.setFrecuencia(request.getFrecuencia());
				recordatorio.setReposicion(request.getReposicion());
				recordatorio.setFechaInicio(DateUtils.getFechaTimestamp(request.getFechaInicio()));
				recordatorio.setFechaFinal(DateUtils.getFechaTimestamp(request.getFechaFinal()));			
				recordatorio.setTipoFrecuencia(frecuencia);
				recordatorio.setGoogleId(request.getGoogleId());
				recordatorio.setHistoriaMedicamento(medicamento);			
			}
		}).register();
		
		mapperFactory.classMap(MedicamentoRecordatorio.class, MedicamentoRecordatorioResponse.class)
		.customize(new CustomMapper<MedicamentoRecordatorio, MedicamentoRecordatorioResponse>(){
			public void mapAtoB(final MedicamentoRecordatorio recordatorio, final MedicamentoRecordatorioResponse response, final MappingContext context) {
				String fechaFinal = null;
				if(recordatorio.getFechaFinal() != null) {
					fechaFinal = DateUtils.getStringDate(recordatorio.getFechaFinal());
				}
				
				response.setId(recordatorio.getId());
				response.setDosis(recordatorio.getDosis());
				response.setEsCronico(recordatorio.getEsCronico());
				response.setFrecuencia(recordatorio.getFrecuencia());
				response.setReposicion(recordatorio.getReposicion());
				response.setFechaInicio(DateUtils.getStringDate(recordatorio.getFechaInicio()));
				response.setFechaFinal(fechaFinal);			
				response.setTipoFrecuencia(recordatorio.getTipoFrecuencia().getDescripcion());
				response.setGoogleId(recordatorio.getGoogleId());
			}
		}).register();
		//------------------------------------------------------------------------------
		
		//AUTORIZACION
		
		mapperFactory.classMap(AutorizacionRequest.class, Autorizacion.class)
		.customize(new CustomMapper<AutorizacionRequest, Autorizacion>(){
			public void mapAtoB(final AutorizacionRequest request, final Autorizacion autorizacion, final MappingContext context) {
				autorizacion.setCodigo(request.getCodigo());
				autorizacion.setDescripcion(request.getDescripcion());
				autorizacion.setActivo(false);
			}
		}).register();
		
		mapperFactory.classMap(Autorizacion.class, AutorizacionResponse.class)
		.customize(new CustomMapper<Autorizacion, AutorizacionResponse>(){
			public void mapAtoB(final Autorizacion autorizacion, final AutorizacionResponse response, final MappingContext context) {
				
				List<AutorizacionComponentResponse> componentesResponse = new ArrayList<AutorizacionComponentResponse>();
				if(autorizacion.getComponentes() != null) {
					componentesResponse = AutorizacionUtils.getListAutorizacionComponentResponse(autorizacion.getComponentes());
				}
				
				response.setId(autorizacion.getId());
				response.setCodigo(autorizacion.getCodigo());
				response.setDescripcion(autorizacion.getDescripcion());
				response.setActivo(autorizacion.getActivo());
				response.setComponentes(componentesResponse);
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//PERMISO
		
		mapperFactory.classMap(PermisoRequest.class, Permiso.class)
		.customize(new CustomMapper<PermisoRequest, Permiso>(){
			public void mapAtoB(final PermisoRequest request, final Permiso permiso, final MappingContext context) {
				permiso.setCodigo(request.getCodigo());
				permiso.setDescripcion(request.getDescripcion());
				permiso.setActivo(false);
			}
		}).register();
				
		mapperFactory.classMap(Permiso.class, PermisoResponse.class)
		.customize(new CustomMapper<Permiso, PermisoResponse>(){
			public void mapAtoB(final Permiso permiso, final PermisoResponse response, final MappingContext context) {
				response.setId(permiso.getId());
				response.setCodigo(permiso.getCodigo());
				response.setDescripcion(permiso.getDescripcion());
				response.setActivo(permiso.getActivo());
			}
		}).register();
				
		//------------------------------------------------------------------------------
		
		//ROL
		
		mapperFactory.classMap(RolRequest.class, Rol.class)
		.customize(new CustomMapper<RolRequest, Rol>(){
			public void mapAtoB(final RolRequest request, final Rol rol, final MappingContext context) {
				rol.setCodigo(request.getCodigo());
				rol.setDescripcion(request.getDescripcion());
				rol.setActivo(false);
			}
		}).register();
						
		mapperFactory.classMap(Rol.class, RolResponse.class)
		.customize(new CustomMapper<Rol, RolResponse>(){
			public void mapAtoB(final Rol rol, final RolResponse response, final MappingContext context) {
				
				List<PermisoResponse> permisosResponse = new ArrayList<PermisoResponse>();
				if(rol.getPermisos() != null) {
					permisosResponse = AutorizacionUtils.getListPermisosResponse(rol.getPermisos());
				}
				
				response.setId(rol.getId());
				response.setCodigo(rol.getCodigo());
				response.setDescripcion(rol.getDescripcion());
				response.setActivo(rol.getActivo());
				response.setPermisos(permisosResponse);
			}
		}).register();
						
		//------------------------------------------------------------------------------
		
		//GRUPO
		
		mapperFactory.classMap(GrupoRequest.class, Grupo.class)
		.customize(new CustomMapper<GrupoRequest, Grupo>(){
			public void mapAtoB(final GrupoRequest request, final Grupo grupo, final MappingContext context) {
				grupo.setCodigo(request.getCodigo());
				grupo.setDescripcion(request.getDescripcion());
				grupo.setActivo(false);
			}
		}).register();
								
		mapperFactory.classMap(Grupo.class, GrupoResponse.class)
		.customize(new CustomMapper<Grupo, GrupoResponse>(){
			public void mapAtoB(final Grupo grupo, final GrupoResponse response, final MappingContext context) {
						
				List<PermisoResponse> permisosResponse = new ArrayList<PermisoResponse>();
				if(grupo.getPermisos() != null) {
					permisosResponse = AutorizacionUtils.getListPermisosResponse(grupo.getPermisos());
				}
				
				List<RolResponse> rolesResponse = new ArrayList<RolResponse>();
				if(grupo.getRoles() != null) {
					rolesResponse = AutorizacionUtils.getListRolesResponse(grupo.getRoles());
				}
						
				response.setId(grupo.getId());
				response.setCodigo(grupo.getCodigo());
				response.setDescripcion(grupo.getDescripcion());
				response.setActivo(grupo.getActivo());
				response.setPermisos(permisosResponse);
				response.setRoles(rolesResponse);
			}
		}).register();
								
		//------------------------------------------------------------------------------
				
		//GRUPO FAMILIAR
		
		mapperFactory.classMap(GrupoFamiliarRequest.class, GrupoFamiliar.class)
		.customize(new CustomMapper<GrupoFamiliarRequest, GrupoFamiliar>(){
			public void mapAtoB(final GrupoFamiliarRequest request, final GrupoFamiliar grupoFamiliar, final MappingContext context) {
				grupoFamiliar.setCodigo(PasswordGenerator.codigoVerificacion());
				grupoFamiliar.setNombre(request.getNombre());
				grupoFamiliar.setFechaCreado(DateUtils.getFechaNowTimestamp());
				grupoFamiliar.setActivo(true);
			}
		}).register();
		
		mapperFactory.classMap(GrupoFamiliar.class, GrupoFamiliarResponse.class)
		.customize(new CustomMapper<GrupoFamiliar, GrupoFamiliarResponse>(){
			public void mapAtoB(final GrupoFamiliar grupoFamiliar, final GrupoFamiliarResponse response, final MappingContext context) {
				
				List<HistoriaMedicaResponse> historiasResponse = new ArrayList<HistoriaMedicaResponse>();
				if(grupoFamiliar.getHistorias() != null) {
					historiasResponse = HistoriaMedicaUtils.getListHistoriaMedicaResponse(grupoFamiliar.getHistorias());
				}
				
				List<UsuarioResponse> usuariosAdminsResponse = new ArrayList<UsuarioResponse>();
				if(grupoFamiliar.getAdmins() != null) {
					usuariosAdminsResponse = UsuarioUtils.getListUsuariosResponse(grupoFamiliar.getAdmins());
				}
				
				List<UsuarioResponse> usuariosNormalesResponse = new ArrayList<UsuarioResponse>();
				if(grupoFamiliar.getUsuarios() != null) {
					usuariosNormalesResponse = UsuarioUtils.getListUsuariosResponse(grupoFamiliar.getUsuarios());
				}
				
				List<GrupoNotificacionResponse> grupoNotificaciones = new ArrayList<GrupoNotificacionResponse>();
				if(grupoFamiliar.getGruposNotificaciones() != null) {
					grupoNotificaciones = GrupoFamiliarUtils.getListGruposNotificacionesResponse(grupoFamiliar.getGruposNotificaciones());
				}
				
				response.setId(grupoFamiliar.getId());
				response.setNombre(grupoFamiliar.getNombre());
				response.setCodigo(grupoFamiliar.getCodigo());
				response.setFechaCreado(DateUtils.getStringDate(grupoFamiliar.getFechaCreado()));
				response.setHistoriasMedicasResponse(historiasResponse);
				response.setAdmins(usuariosAdminsResponse);
				response.setUsuarios(usuariosNormalesResponse);
				response.setNotificaciones(grupoNotificaciones);
				response.setActivo(grupoFamiliar.getActivo());
				
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//GRUPO NOTIFICACION
		
		mapperFactory.classMap(GrupoFamiliarIngresarRequest.class, GrupoNotificacion.class)
		.customize(new CustomMapper<GrupoFamiliarIngresarRequest, GrupoNotificacion>(){
			public void  mapAtoB(final GrupoFamiliarIngresarRequest request, final GrupoNotificacion grupoNotificacion, final MappingContext context) {
				GrupoFamiliar grupoFamiliar = null;
				UsuarioPaciente usuario = null;
				
				if(request.getCodigo() != null) {
					grupoFamiliar = GrupoFamiliar.builder().codigo(request.getCodigo()).build();
				}
				
				if(request.getUsuarioMail() != null) {
					usuario = UsuarioPaciente.builder().mail(request.getUsuarioMail()).build();
				}
				
				grupoNotificacion.setGrupoFamiliar(grupoFamiliar);
				grupoNotificacion.setUsuario(usuario);
				grupoNotificacion.setFecha(DateUtils.getFechaNowTimestamp());
				grupoNotificacion.setAceptada(false);
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//SIGNO VITAL CUSTOM
		
		mapperFactory.classMap(SignoVitalCustomRequest.class, SignoVitalCustom.class)
		.customize(new CustomMapper<SignoVitalCustomRequest, SignoVitalCustom>(){
			public void mapAtoB(final SignoVitalCustomRequest request, final SignoVitalCustom signoVitalCustom, final MappingContext context) {
				
				HistoriaMedica historiaMedica = null;
				TipoSignoVital tipoSignoVital = null;
				
				if(request.getHistoriaMedicaId() != null) {
					historiaMedica = HistoriaMedica.builder().id(request.getHistoriaMedicaId()).build();
				}
				
				if(request.getTipoSignoVitalId() != null) {
					tipoSignoVital = TipoSignoVital.builder().id(request.getTipoSignoVitalId()).build();
				}
				
				signoVitalCustom.setMaximo(request.getMaximo());
				signoVitalCustom.setMinimo(request.getMinimo());
				signoVitalCustom.setSegundoMaximo(request.getSegundoMaximo());
				signoVitalCustom.setSegundoMinimo(request.getSegundoMinimo());
				signoVitalCustom.setHistoriaMedica(historiaMedica);
				signoVitalCustom.setTipoSignoVital(tipoSignoVital);
				
			}
		}).register();
		
		mapperFactory.classMap(SignoVitalCustom.class, SignoVitalCustomResponse.class)
		.customize(new CustomMapper<SignoVitalCustom, SignoVitalCustomResponse>(){
			public void mapAtoB(final SignoVitalCustom signoVitalCustom, final SignoVitalCustomResponse response, final MappingContext context) {
				List<SignoVitalPacienteResponse> signosVitalesPacienteResponse = new ArrayList<SignoVitalPacienteResponse>();
				if(signoVitalCustom.getSignosVitalesPaciente() != null){
					signosVitalesPacienteResponse = SignoVitalUtils.getListSignosVitalesPacienteResponse(signoVitalCustom.getSignosVitalesPaciente());
				}
				response.setId(signoVitalCustom.getId());
				response.setMaximo(signoVitalCustom.getMaximo());
				response.setMinimo(signoVitalCustom.getMinimo());
				response.setSegundoMaximo(signoVitalCustom.getSegundoMaximo());
				response.setSegundoMinimo(signoVitalCustom.getSegundoMinimo());
				response.setTipoSignoVital(signoVitalCustom.getTipoSignoVital().getNombre());
				response.setMedida(signoVitalCustom.getTipoSignoVital().getMedida());
				response.setSignosVitalesPaciente(signosVitalesPacienteResponse);
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		//SIGNO VITAL PACIENTE
		
		mapperFactory.classMap(SignoVitalPacienteRequest.class, SignoVitalPaciente.class)
		.customize(new CustomMapper<SignoVitalPacienteRequest, SignoVitalPaciente>(){
			public void mapAtoB(final SignoVitalPacienteRequest request, final SignoVitalPaciente signoVitalPaciente, final MappingContext context) {
				
				HistoriaMedica historiaMedica = null;
				SignoVitalCustom signoVitalCustom = null;
				
				if(request.getHistoriaMedicaId() != null) {
					historiaMedica = HistoriaMedica.builder().id(request.getHistoriaMedicaId()).build();
				}
				
				if(request.getSignoVitalCustomId() != null) {
					signoVitalCustom = SignoVitalCustom.builder().id(request.getSignoVitalCustomId()).build();
				}
				
				signoVitalPaciente.setValor(request.getValor());
				signoVitalPaciente.setSegundoValor(request.getSegundoValor());
				signoVitalPaciente.setFechaIngresado(DateUtils.getFechaNowTimestamp());
				signoVitalPaciente.setComentario(request.getComentario());
				signoVitalPaciente.setHistoriaMedica(historiaMedica);
				signoVitalPaciente.setSignoVitalCustom(signoVitalCustom);
				
			}
		}).register();
		
		mapperFactory.classMap(SignoVitalPaciente.class, SignoVitalPacienteResponse.class)
		.customize(new CustomMapper<SignoVitalPaciente, SignoVitalPacienteResponse>(){
			public void mapAtoB(final SignoVitalPaciente signoVitalPaciente, final SignoVitalPacienteResponse response, final MappingContext context) {
				String segundoResultado = null;
				
				if(signoVitalPaciente.getSegundoResultado() != null) {
					segundoResultado = signoVitalPaciente.getSegundoResultado().getDescripcion();
				}
				
				response.setId(signoVitalPaciente.getId());
				response.setValor(signoVitalPaciente.getValor());
				response.setSegundoValor(signoVitalPaciente.getSegundoValor());
				response.setResultado(signoVitalPaciente.getResultado().getDescripcion());
				response.setSegundoResultado(segundoResultado);
				response.setFechaIngresado(DateUtils.getStringDateTime(signoVitalPaciente.getFechaIngresado()));
				response.setComentario(signoVitalPaciente.getComentario());
				response.setMinimo(signoVitalPaciente.getMinimo());
				response.setMaximo(signoVitalPaciente.getMaximo());
				response.setSegundoMinimo(signoVitalPaciente.getSegundoMinimo());
				response.setSegundoMaximo(signoVitalPaciente.getSegundoMaximo());
			}
		}).register();
		
		//------------------------------------------------------------------------------
		
		return mapperFactory.getMapperFacade();
	}
}
