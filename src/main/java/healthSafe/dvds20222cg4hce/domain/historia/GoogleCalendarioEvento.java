package healthSafe.dvds20222cg4hce.domain.historia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GoogleCalendarioEvento implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3058134587622367584L;
	
	private String summary;
	
	private String location;
	
	private String description;
    
    private String startTime;
    
    private String endTime;
    
    private List<String> attendees;
    
    public void addAttendees(String attendee) {
    	if(this.attendees == null) new ArrayList<String>();
    	this.attendees.add(attendee);
    } 
}
