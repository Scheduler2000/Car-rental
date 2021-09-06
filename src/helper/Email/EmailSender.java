package helper.Email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * If you're getting this error <em>"javax.mail.AuthenticationFailedException:
 * 535-5.7.8 Username and Password not accepted"</em> when you try to send mail
 * see :
 * https://stackoverflow.com/questions/35347269/javax-mail-authenticationfailedexception-535-5-7-8-username-and-password-not-ac
 */
public class EmailSender {

    private final Session _session;

    public EmailSender(EmailConfiguration configuration) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", configuration.GetSMTPEmailHost());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        _session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(configuration.getSenderEmail(),
                        configuration.GetSenderPassword());
            }
        });

    }

    public void sendEmail(String[] receivers, String emailSubject, String emailText)
            throws MessagingException, IOException {

        Message message = new MimeMessage(_session);

        for (int i = 0; i < receivers.length; i++)
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receivers[i]));

        message.setSubject(emailSubject);
        message.setText(emailText);

        Transport.send(message, message.getAllRecipients());
    }

}
