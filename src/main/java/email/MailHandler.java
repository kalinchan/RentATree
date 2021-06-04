package email;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Named
@Dependent
public class MailHandler {
	
	@Resource(lookup = "mailSession")
	Session session;
	
	public void sendMail() {
		try {
			System.out.println("Trying to send mail");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("RentATree@gmail.com"));
			message.setRecipients(RecipientType.TO, InternetAddress.parse("kalin.chan@payara.fish"));
			message.setSubject("Test Mail" + Calendar.getInstance().getTime());
			message.setText("message sent");
			
			Transport t = session.getTransport();
			t.connect("RentATreePayara@gmail.com", "LYfwAn7an7qcwZE");
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("Mail sent");
		}catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
