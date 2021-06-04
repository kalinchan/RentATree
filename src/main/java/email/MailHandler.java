package email;

import DatabaseObjects.Tree;
import beans.CardDetailsBean;
import beans.CartBean;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
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
public class MailHandler implements Serializable {

	@Inject
	MailContents mailContents;
	
	@Resource(lookup = "mailSession")
	Session session;
	
	public void sendMail(String recipientEmail) {
		try {
			System.out.println("Trying to send mail");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("RentATree@gmail.com"));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject("Order Confirmation");
			message.setText(Calendar.getInstance().getTime()+mailContents.buildMessage());
			
			Transport t = session.getTransport();
			t.connect("RentATreePayara@gmail.com", "LYfwAn7an7qcwZE");
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("Mail sent to "+recipientEmail);
		}catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}




}
