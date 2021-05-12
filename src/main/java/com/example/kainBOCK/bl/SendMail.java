package com.example.kainBOCK.bl;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMail {

    /**
     *
     * @param smtpHost
     * @param username
     * @param password
     * @param senderAddress
     * @param recipientsAddress
     * @param subject
     * @param text
     */
    public void sendMail(String smtpHost, String username, String password, String senderAddress, String recipientsAddress, String subject, String text) {
        MailAuthenticator auth = new MailAuthenticator(username, password);

        Properties properties = new Properties();

        // Den Properties wird die ServerAdresse hinzugefügt
        properties.put("mail.smtp.host", smtpHost);

        // !!Wichtig!! Falls der SMTP-Server eine Authentifizierung
        // verlangt
        // muss an dieser Stelle die Property auf "true" gesetzt
        // werden
        properties.put("mail.smtp.auth", "true");

        // Hier wird mit den Properties und dem implements Contructor
        // erzeugten
        // MailAuthenticator eine Session erzeugt
        Session session = Session.getDefaultInstance(properties, auth);

        try {
            // Eine neue Message erzeugen
            Message msg = new MimeMessage(session);

            // Hier werden die Absender- und Empfängeradressen gesetzt
            msg.setFrom(new InternetAddress(senderAddress));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(
                    recipientsAddress, false));

            // Der Betreff und Body der Message werden gesetzt
            msg.setSubject(subject);
            msg.setText(text);

            // Hier lassen sich HEADER-Informationen hinzufügen
            msg.setHeader("Test", "Test");
            msg.setSentDate(new Date());

            // Zum Schluss wird die Mail natürlich noch verschickt
            Transport.send(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}