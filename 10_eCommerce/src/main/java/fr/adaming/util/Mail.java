package fr.adaming.util;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {
	
	public static void sendMail(String toMail, String sujet, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("proxibanque17@gmail.com","proxibanque17+20");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("aaaa7@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toMail));
			message.setSubject(sujet);
			message.setText(text);
			// test
			  // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H1>Bonjour</H1><div>"+text+" <br/></div>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource(
	            "C:\\Users\\INTI0489\\Desktop\\pdftest\\pdf1.pdf");
	         

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setFileName("pdf1.pdf");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void sendRecapCommande(String toMail, String sujet, String text) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("proxibanque17@gmail.com","proxibanque17+20");
				}
			});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("aaaa7@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(toMail));
			message.setSubject(sujet);
			message.setText(text);
			// test
			  // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         String htmlText = "<H1>Bonjour</H1><div>"+text+" <br/></div>";
	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds = new FileDataSource("C:\\Users\\INTI0489\\Desktop\\pdftest\\Recapitulatif.pdf");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setFileName("Recapitulatif.pdf");

	         // add image to the multipart
	         multipart.addBodyPart(messageBodyPart);

	         // put everything together
	         message.setContent(multipart);

			Transport.send(message);

			System.out.println("Done recap de commande");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

}
