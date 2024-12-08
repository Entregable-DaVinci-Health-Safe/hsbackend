package healthSafe.dvds20222cg4hce.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.services.calendar.model.Event;
import healthSafe.dvds20222cg4hce.controller.request.GoogleCalendarioGetListRequest;
import healthSafe.dvds20222cg4hce.controller.request.GoogleCalendarioPostRequest;
import healthSafe.dvds20222cg4hce.domain.historia.HistoriaMedica;
import healthSafe.dvds20222cg4hce.exception.BusinessException;


public interface GoogleService {
	
	void initializeClient(TokenResponse token) throws GeneralSecurityException, IOException;
	
	RedirectView googleConnectionStatus(HttpServletRequest request) throws Exception;
	
	TokenResponse oauth2Callback(String code) throws GeneralSecurityException, IOException, BusinessException, Exception;
	
	List<Event> getListCalendarEvents(GoogleCalendarioGetListRequest datosListEventos) throws IOException;
	
	Event getCalendarEvent(String eventId) throws IOException, BusinessException;
	
	Event createCalenderEvent(GoogleCalendarioPostRequest eventoRequest) 
			throws IOException, BusinessException;
	
	Event updateGoogleMeeting(String eventId, GoogleCalendarioPostRequest updatedEvent) throws IOException;
	
	Event deleteGoogleMeeting(String eventId) throws IOException;
	
	Boolean existEvent(String startTime, String endTime) throws IOException;

	Long saveUserFromGoogleToken(String tokenId) throws Exception, BusinessException;
}
