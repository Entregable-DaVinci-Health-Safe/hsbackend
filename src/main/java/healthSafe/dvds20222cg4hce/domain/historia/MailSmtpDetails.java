package healthSafe.dvds20222cg4hce.domain.historia;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MailSmtpDetails {
	private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
