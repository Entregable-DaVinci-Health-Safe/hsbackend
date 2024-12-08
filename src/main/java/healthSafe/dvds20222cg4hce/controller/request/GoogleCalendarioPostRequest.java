package healthSafe.dvds20222cg4hce.controller.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoogleCalendarioPostRequest {
	private String summary;
	private String location;
	private String description;
    private List<String> attendees;
    private String startTime;
    private String endTime;
}
