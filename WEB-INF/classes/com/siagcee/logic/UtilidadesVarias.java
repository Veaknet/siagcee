package com.siagcee.logic;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Creado por Fábio Pereira.
 * Bajo la tutoría del Prof. Francisco Mirabal.
 * Trabajo Especial De Grado.
 * Licenciatura de Computación.
 * Universidad Central de Venezuela.
 * Fecha: 28/01/2010
 * Hora: 10:48:29 AM
 */
public class UtilidadesVarias {

	public static String dominioWeb = "http://www.ciens.ucv.ve/sigecene/";

	public UtilidadesVarias() {
	}

	public static String reemplazarCaracteres(String _cadenaFuente, String _cadenaDeseada, String _cadenaDeReemplazo){
		StringBuffer _miBuffer = new StringBuffer();
		int fin = _cadenaFuente.indexOf(_cadenaDeseada);

		while(fin >= 0){

			_miBuffer.append(_cadenaFuente.substring(0, fin));
			_miBuffer.append(_cadenaDeReemplazo);
			_cadenaFuente = _cadenaFuente.substring(fin + _cadenaDeseada.length());

			fin = _cadenaFuente.indexOf(_cadenaDeseada);
		}
		_miBuffer.append(_cadenaFuente.substring(0, _cadenaFuente.length()));

		return _miBuffer.toString();
	}

	public static void enviarMailSinAutenticacion(String _destinatarios[], String _titulo, String _mensaje, String _de, String _encabezados, String _smtp_server) throws MessagingException {
		try{
			// Propiedades de la conexión
			Properties props = new Properties();
			props.setProperty("mail.smtp.host", "smtp.gmail.com");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", "sigecene@gmail.com");
			props.setProperty("mail.smtp.auth", "true");

			// Preparamos la sesion
			Session session = Session.getDefaultInstance(props);

			// Construimos el mensaje
			for (int i = 0; i < _destinatarios.length; i++){
				String to = _destinatarios[i];
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(_de));
				message.addRecipient(
						Message.RecipientType.TO,
						new InternetAddress(to));
				message.setSubject(_titulo);
				//message.setText(_mensaje);
				message.setContent(_mensaje, "text/html");
				// Lo enviamos.
				Transport t = session.getTransport("smtp");
				t.connect("sigecene@gmail.com", "_siagcee_");
				t.sendMessage(message, message.getAllRecipients());

				// Cierre.
				t.close();
			}
		}catch (Exception e){
				e.printStackTrace();
		}
	}

	public static void enviarMailSinAutenticacion(String _destinatario, String _titulo, String _mensaje, String _de, String _encabezados, String _smtp_server) throws MessagingException {
		String _destinatarios[] = {_destinatario};
		enviarMailSinAutenticacion(_destinatarios, _titulo, _mensaje, _de, _encabezados, _smtp_server);
	}
}
