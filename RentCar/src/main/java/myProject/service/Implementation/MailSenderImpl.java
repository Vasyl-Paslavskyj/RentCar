package myProject.service.Implementation;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import myProject.service.MailSender;

@Service
public class MailSenderImpl implements MailSender{
	private final static String USERNAME = "vpaslavskyjj@gmail.com";
    private final static String PASSWORD = "AA14711aa";

	@Override
	@Async
	public void sendMail(String content, String email, String mailBody) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.setProperty("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
        	protected PasswordAuthentication getPasswordAuthentication(){
        		return new PasswordAuthentication(USERNAME, PASSWORD);
        	}
		});
		
        try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(USERNAME));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(USERNAME));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
			message.setSubject(content, "UTF-8");
			message.setText(mailBody);
			synchronized (this) {
				Transport.send(message);
			}
		} catch (MessagingException  e) {
			e.printStackTrace();
			System.out.println("You have some problems with connection!");
		}
	}

}
