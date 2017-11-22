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
	
	private final String SMTP_USERNAME = "odontoprofesional.info@gmail.com";
	private final String SMTP_PASS = "zo85HzqGiS";
	private final String SMTP_URL = "smtp.gmail.com";
	private final int SMTP_PORT = 587;
	private final String SMTP_USE_TLS = "true";
	
	private final String USER_FROM = "info@metflix.co";
	
	private String asunto;
	private String mensaje ;
	private String emailDestinatario;
	

	/**
	 * metodo el cual controla las funcionalidades de el envio de correos
	 * 
	 * @return true si envia el correo, fase si no lo envia
	 */
	public boolean enviar() {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_URL);
			props.put("mail.smtp.starttls.enable", SMTP_USE_TLS);
			props.put("mail.smtp.port", SMTP_PORT);
			props.put("mail.smtp.user", SMTP_USERNAME);
			props.put("mail.smtp.password", SMTP_PASS);
			props.put("mail.smtp.auth", "true");

			Session sesion = Session.getDefaultInstance(props, null);
			
			
			MimeMessage message = new MimeMessage(sesion);

			message.setFrom(new InternetAddress(USER_FROM));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));
			message.setSubject(asunto);
			message.setContent(mensaje, "text/html");
			
			
			Transport transport = sesion.getTransport("smtp");
			transport.connect(SMTP_URL, SMTP_PORT, SMTP_USERNAME, SMTP_PASS);
			transport.sendMessage(message, message.getAllRecipients());
			return true;

		} catch (Exception e) {
			System.out.println("errores" + e.getMessage());
			e.printStackTrace();
			return false;
		}
		
		

	}
	
	

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setEmailDestinatario(String emailDestinatario) {
		this.emailDestinatario = emailDestinatario;
	}

}
