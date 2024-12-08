package healthSafe.dvds20222cg4hce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import healthSafe.dvds20222cg4hce.domain.historia.MailSmtpDetails;

@Service
public class MailSmtpServiceImpl implements MailSmtpService{
	
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}") 
	private String sender;
	
	@Autowired
	public MailSmtpServiceImpl(final JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public String sendMail(MailSmtpDetails mailDetails) throws Exception{
		// TODO Auto-generated method stub
		SimpleMailMessage mailMessage = new SimpleMailMessage();

	    mailMessage.setFrom(sender);
	    mailMessage.setTo(mailDetails.getRecipient());
	    mailMessage.setText(mailDetails.getMsgBody());
	    mailMessage.setSubject(mailDetails.getSubject());
	        
	    mailSender.send(mailMessage);
	    return "Mail enviado exitosamente...";

	}
}
