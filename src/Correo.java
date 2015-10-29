
/*

This file is part of "Sistema de Axeso 2015".

"Sistema de Axeso 2015" is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

"Sistema de Axeso 2015" is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
"Sistema de Axeso 2015"

*/

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Correo {
	Properties props = new Properties();
	final String username = "maximosoria7@gmail.com";
    final String password = "chiquis83";
	Session session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });
	
	// Configuracion previa para enviar el correo
	public Correo() {
		props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
	}
	
	//Correo enviado cuando hay una falta
	public void ausencia(String destino) {
    	LocalDateTime myTime = LocalDateTime.now();
	    String time = myTime.toString();
				
		try {
			Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("maximosoria7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(destino));
            message.setSubject("Su hijo no vino a clases");
            message.setText("Le informamos que hoy su hijo faltó a la escuela");
            Transport.send(message);
            System.out.println("e-mail de inasistencia enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
	
	// Correo enviado cuando va a haber junta
	public void junta(String destino) {
    	try {
    		Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("maximosoria7@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(destino));
            message.setSubject("Próxima junta");
            message.setText("El miercoles hay junta");
            Transport.send(message);
            System.out.println("e-mail de junta enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
