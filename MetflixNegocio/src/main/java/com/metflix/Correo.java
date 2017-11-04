package com.metflix;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Correo {
	
	private final String SMTP_USERNAME = "AKIAIPV4R6K7UDLLL2CQ";
	private final String SMTP_PASS = "AgkDJwfa+BgFxTUJe5aUYN/R1s1UJgHUvtDkaCkZFto9";
	private final String SMTP_URL = "email-smtp.us-west-2.amazonaws.com";
	private final String SMTP_PORT = "465";
	private final String SMTP_USE_TLS = "true";
	
	private final String USER_FROM = "info@metflixu.co";
	private final String ASUNTO = "Recuperación de contraseña";
	
	private String mensaje ;
	private String emailDestinatario;
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

	/**
	 * metodo el cual controla las funcionalidades de el envio de correos
	 * 
	 * @return true si envia el correo, fase si no lo envia
	 */
	public boolean enviar() {
		try {
			Properties p = new Properties();
			p.put("mail.smtp.host", SMTP_URL);
			p.setProperty("mail.smtp.starttls.enable", SMTP_USE_TLS);
			p.setProperty("mail.smtp.port", SMTP_PORT);
			p.setProperty("mail.smtp.user", SMTP_USERNAME);
			p.setProperty("mail.smtp.auth", "true");

			Session s = Session.getDefaultInstance(p, null);
			
			BodyPart texto = new MimeBodyPart();
			texto.setText(mensaje);
			MimeMultipart m = new MimeMultipart();
			m.addBodyPart(texto);
			
			MimeMessage mimeMensaje = new MimeMessage(s);

			mimeMensaje.setFrom(new InternetAddress(USER_FROM));
			mimeMensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
			mimeMensaje.setSubject(ASUNTO);
			mimeMensaje.setContent(m);

			Transport t = s.getTransport("smtp");

			t.connect(SMTP_USERNAME, SMTP_PASS);

			t.sendMessage(mimeMensaje, mimeMensaje.getAllRecipients());
			t.close();
			return true;

		} catch (Exception e) {
			System.out.println("errores" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		

	}

}
