package healthSafe.dvds20222cg4hce.service;

import healthSafe.dvds20222cg4hce.domain.historia.MailSmtpDetails;

public interface MailSmtpService {
	
	String sendMail(MailSmtpDetails mailDetails) throws Exception;
}
