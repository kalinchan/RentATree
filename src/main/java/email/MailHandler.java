package email;

import DatabaseObjects.Tree;
import beans.CardDetailsBean;
import beans.CartBean;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

@Named
@Dependent
public class MailHandler implements Serializable {

	@Inject
	MailContents mailContents;

	@Inject
	FileBuilder fileBuilder;

	
	@Resource(lookup = "mailSession")
	Session session;
	
	public void sendConfirmationMail(String recipientEmail) {
		try {
			System.out.println("Trying to send mail");
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress("RentATree@gmail.com"));
			msg.setRecipients(RecipientType.TO, InternetAddress.parse(recipientEmail));
			msg.setSubject("Order Confirmation");
			String message = (mailContents.buildMessage());

			Multipart multipart = new MimeMultipart();
			//For Message
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(message);
			//For attachment
			fileBuilder.createFile();
			MimeBodyPart attachmentPart = new MimeBodyPart();
			attachmentPart.attachFile("OrderDetails.pdf");

			//Combine
			multipart.addBodyPart(messageBodyPart);
			multipart.addBodyPart(attachmentPart);
			msg.setContent(multipart);

			Transport t = session.getTransport();
			t.connect("RentATreePayara@gmail.com", "LYfwAn7an7qcwZE");
			t.sendMessage(msg, msg.getAllRecipients());
			System.out.println("Mail sent to "+recipientEmail);
		}catch (MessagingException | IOException | DocumentException e) {
			throw new RuntimeException(e);
		}
	}



	public void sendMail(String recipientEmail, String subject, String text) {
		try {
			System.out.println("Trying to send mail");
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("RentATree@gmail.com"));
			message.setRecipients(RecipientType.TO, InternetAddress.parse(recipientEmail));
			message.setSubject(subject);
			message.setText(text);
			
			Transport t = session.getTransport();
			//t.connect();
			t.connect("RentATreePayara@gmail.com", "LYfwAn7an7qcwZE");
			t.sendMessage(message, message.getAllRecipients());
			System.out.println("Mail sent to "+recipientEmail);
		}catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
