package healthSafe.dvds20222cg4hce.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
import healthSafe.dvds20222cg4hce.controller.request.GoogleCalendarioGetListRequest;
import healthSafe.dvds20222cg4hce.controller.request.GoogleCalendarioPostRequest;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.domain.usuario.Genero;
import healthSafe.dvds20222cg4hce.domain.usuario.UsuarioPaciente;
import healthSafe.dvds20222cg4hce.exception.BusinessException;
import healthSafe.dvds20222cg4hce.service.GoogleServiceImpl;
import healthSafe.dvds20222cg4hce.service.usuario.UsuarioService;
import healthSafe.dvds20222cg4hce.utils.DateUtils;
import healthSafe.dvds20222cg4hce.utils.PasswordGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.ConferenceData;
import com.google.api.services.calendar.model.ConferenceSolutionKey;
import com.google.api.services.calendar.model.CreateConferenceRequest;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Event.Reminders;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.people.v1.PeopleServiceScopes;

@Service
public class GoogleServiceImpl implements GoogleService{
	
	private static final String APPLICATION_NAME = "EasyEcom_Calendar";
	private static final String CALENDAR_ID = "primary";
	private static final String TIMEZONE = "GMT-3";
	
	private static Calendar calendario;

	private GoogleClientSecrets clientSecrets;
	private GoogleAuthorizationCodeFlow flow;
	private Credential credential;

	@Value("${google.client.client-id}")
	private String clientId;
	
	@Value("${google.client.client-secret}")
	private String clientSecret;
	
	@Value("${google.client.redirectUri}")
	private String redirectURI;
	
	private UsuarioService usuarioService;

	private HistoriaMedicaService historiaMedicaService;
	
	private final ObjectMapper objectMapper;
	
	@Autowired
	public GoogleServiceImpl(final UsuarioService usuarioService, 
							final HistoriaMedicaService historiaMedicaService) {
		this.usuarioService = usuarioService;
		this.historiaMedicaService = historiaMedicaService;
		objectMapper = new ObjectMapper();
	}
	
	@Override
	public RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception {

		return new RedirectView(authorize());
	}
	
	@Override
	public void initializeClient(TokenResponse token) throws GeneralSecurityException, IOException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		credential = flow.createAndStoreCredential(token, "Pratik Sontakke");
		calendario = new Calendar.Builder(httpTransport, GsonFactory.getDefaultInstance(), credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	@Override
	public TokenResponse oauth2Callback(String code) throws GeneralSecurityException, IOException, BusinessException, Exception {

		TokenResponse token = flow.newTokenRequest(code).setRedirectUri(redirectURI).execute();
		token.setExpiresInSeconds(System.currentTimeMillis() + 360 * 10000);
			
		initializeClient(token);
		/*	
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, GsonFactory.getDefaultInstance())
				   .setAudience(Collections.singletonList(clientId))
				   .build();

		
		GoogleIdToken idToken = verifier.verify((String)token.get("id_token"));
			
		if (idToken != null) { saveUserFromGoogle(idToken.getPayload(), token.getAccessToken());}
		*/
		return token;
	}
	
	@Override
	public Event createCalenderEvent(GoogleCalendarioPostRequest eventoRequest) 
			throws IOException, BusinessException {
		
		if(existEvent(eventoRequest.getStartTime(), eventoRequest.getEndTime())) {
			throw new BusinessException("Ya existe un evento en esa fecha");
		}
		
		Event event = new Event().setSummary(eventoRequest.getSummary())
				.setLocation(eventoRequest.getLocation())
				.setDescription(eventoRequest.getDescription());

		event.setConferenceData(getConferenceData());
		event.setStart(
				getEventDateTime(
						getDateTime(eventoRequest.getStartTime())
						).setTimeZone(TIMEZONE)
				);
		
		event.setEnd(
				getEventDateTime(
						getDateTime(eventoRequest.getEndTime())
						).setTimeZone(TIMEZONE)
				);

		event.setRecurrence(getListRecurrences());
		
		List<EventAttendee> attendees = new ArrayList<EventAttendee>();
		if(eventoRequest.getAttendees() != null) {
			attendees = getAttendees(eventoRequest.getAttendees());
		}
		
		event.setAttendees(attendees);
		event.setReminders(getReminders());
		event.getHangoutLink();
		
		 return insertEvent(event);
		
	}

	@Override
	public Event updateGoogleMeeting(String eventId, GoogleCalendarioPostRequest updatedEvent) throws IOException {
		try {
			Event oldEvent = calendario.events().get(CALENDAR_ID, eventId).execute();

			oldEvent.setSummary(updatedEvent.getSummary());
			oldEvent.setDescription(updatedEvent.getDescription());
			oldEvent.setLocation(updatedEvent.getLocation());
			oldEvent.setStart(
					getEventDateTime(
							getDateTime(updatedEvent.getStartTime())));
			
			oldEvent.setEnd(
					getEventDateTime(
							getDateTime(updatedEvent.getEndTime())));

			List<EventAttendee> attendees = new ArrayList<EventAttendee>();
			if(updatedEvent.getAttendees() != null) {
				attendees = getAttendees(updatedEvent.getAttendees());
			}
			oldEvent.setAttendees(attendees);

			return calendario.events().patch(CALENDAR_ID, eventId, oldEvent).execute();
		} catch (IOException e) {
			throw new IOException("Failed to update the Calendar Event: " + e.getMessage());
		}
	}
	
	@Override
	public Boolean existEvent(String startTime, String endTime) throws IOException {
		List<Event> events = calendario.events()
				.list(CALENDAR_ID)
				.setTimeMin(new DateTime(startTime))
				.setTimeMax(new DateTime(endTime)).execute()
				.getItems();
		
		return !events.isEmpty();

	}
	
	@Override
	public List<Event> getListCalendarEvents(GoogleCalendarioGetListRequest datosListEventos) throws IOException {
		
		List<Event> events = calendario.events()
				.list(CALENDAR_ID)
				.setTimeMin(getDateTime(
						datosListEventos.getStartDate()
						)).setTimeZone(TIMEZONE)
				.setTimeMax(getDateTime(
						datosListEventos.getEndDate()
						)).setTimeZone(TIMEZONE)
				.execute().getItems();

		return events;
	}
	
	public Event getCalendarEvent(String eventId) throws IOException, BusinessException{
		Event event = calendario.events().get(CALENDAR_ID, eventId).execute();
		if(event != null) return event;
		throw new BusinessException("No se pudo encontrar un evento con el id: " + eventId);	
	}
	
	@Override
	public Event deleteGoogleMeeting(String eventId) throws IOException {
		Event deletedEvent = calendario.events().get(CALENDAR_ID, eventId).execute();
		calendario.events().delete(CALENDAR_ID, eventId).execute();
		return deletedEvent;
	
	}

	private String authorize() throws Exception {
		AuthorizationCodeRequestUrl authorizationUrl;
		 
		if (flow == null) {
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);
			clientSecrets = new GoogleClientSecrets().setWeb(web);
			
			HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, 
					GsonFactory.getDefaultInstance(), clientSecrets, getScopes())
					.setAccessType("offline")
					.setApprovalPrompt("force")
					.build();
		}
		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectURI);
		return authorizationUrl.build();
		
	}
	
	private Event insertEvent(Event event) throws IOException {
		return calendario.events()
				.insert(CALENDAR_ID, event)
				.setConferenceDataVersion(1)
				.setSendNotifications(true).execute();
	}
	
	private EventDateTime getEventDateTime(DateTime dateTime) {
		return new EventDateTime().setDateTime(dateTime);
	}
	
	private DateTime getDateTime(String strDateTime) {
		return new DateTime(strDateTime);
	}
	
	private List<EventAttendee> getAttendees(List<String> requestAttendees){
		List<EventAttendee> attendees = new ArrayList<EventAttendee>();
		requestAttendees.stream().forEach(attendee -> 
			attendees.add(new EventAttendee().setEmail(attendee)));
		return attendees;
	}
	
	private ConferenceData getConferenceData() {
		ConferenceSolutionKey conferenceSKey = new ConferenceSolutionKey();
		conferenceSKey.setType("hangoutsMeet");
		
		CreateConferenceRequest createConferenceReq = new CreateConferenceRequest();
		createConferenceReq.setRequestId(UUID.randomUUID().toString()); // ID
		createConferenceReq.setConferenceSolutionKey(conferenceSKey);
		
		ConferenceData conferenceData = new ConferenceData();
		conferenceData.setCreateRequest(createConferenceReq);
		return conferenceData;
	}
	
	private Reminders getReminders() {
		List<EventReminder> reminderOverrides = new ArrayList<EventReminder>();
		reminderOverrides.add(new EventReminder().setMethod("email").setMinutes(24 * 60));
		reminderOverrides.add(new EventReminder().setMethod("popup").setMinutes(5));
		
		return new Reminders().setUseDefault(false).setOverrides(reminderOverrides);
	}

	private List<String> getListRecurrences() {
		// you can add recurrence rule here -> "RRULE:FREQ=DAILY;COUNT=2"
		List<String> recurrences = new ArrayList<String>();
		return recurrences;
	}
	
	/* 
	private ResponseEntity<String> getDataFromGooglePeopleApi(String userId, String accesToken){
		String uri = "https://people.googleapis.com/v1/people/" + userId + "?personFields=genders,birthdays";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(accesToken);
		
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		
		return restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
	}*/

	private List<String> getScopes(){
		return Arrays.asList(CalendarScopes.CALENDAR, "https://www.googleapis.com/auth/userinfo.email", 
				"https://www.googleapis.com/auth/userinfo.profile",
				PeopleServiceScopes.USER_GENDER_READ,
				PeopleServiceScopes.USER_BIRTHDAY_READ);
	}
	
	private Long saveUserFromGoogle(Payload payload)throws BusinessException, Exception {
		
		String email = payload.getEmail().toLowerCase();

		if(!usuarioService.existMail(email)) {
			UsuarioPaciente paciente = UsuarioPaciente.builder()
					.imgPerfil((String) payload.get("picture"))
					.nombre((String) payload.get("given_name"))
					.apellido((String) payload.get("family_name"))
					.mail(email)
					.genero(Genero.SIN_ESPECIFICAR)
					.fechaNacimiento(DateUtils.getFechaTimestamp("0000-00-00"))
					.password(PasswordGenerator.passwordGenerator())
					.activo(true)
					.build();
			
			paciente = usuarioService.saveUserFromGoogle(paciente);
			return Long.valueOf(0);
		}else{
			return historiaMedicaService.findByPacienteMail(email).getId();
		}
	
		
	}

	@Override
	public Long saveUserFromGoogleToken(String tokenId) throws Exception, BusinessException{
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken token = verifier.verify(tokenId);
        if (token == null) {
			throw new RuntimeException("Invalid ID token.");
        }

		return saveUserFromGoogle(token.getPayload());
		
	}
}
