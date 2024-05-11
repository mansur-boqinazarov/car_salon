package uz.pdp.backend.service.gmailservice;

import lombok.SneakyThrows;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Objects;
import java.util.Properties;
import java.util.ResourceBundle;


public class GmailServiceimp implements GmailService {

    private String verificationCode;

    /**bu joyda username va passwordni settings.propertiesdan oladi*/
    private static final String USERNAME = ResourceBundle.getBundle("settings").getString("gmail.username");
    private static final String PASSWORD = ResourceBundle.getBundle("settings").getString("gmail.password");

    @Override
    @SneakyThrows
    public void registerMailService(String recipientEmail)  {
        Properties properties = getProperties();
            Session session = getSession(properties);
                Message message = getMessage(recipientEmail, session);
                    Transport.send(message);
    }

    private Message getMessage(String recipientEmail, Session session) throws MessagingException {
        this.verificationCode = getVerificationCode();
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Verification code");
        message.setContent("<h2>Verification Code</h2>" +
                "           <p>Your verification code is: <strong>" + this.verificationCode + "</strong></p>","text/html");
        return message;
    }

    private static Session getSession(Properties properties) {
        return Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        return properties;
    }
/**
 * bu verificationni tekshirib boolean qaytaradi
 */
    @Override
    public boolean checkVerificationCode(String verificationCode) {
        return Objects.equals(this.verificationCode, verificationCode);
    }
/**
 * verification codeni qaytaradi, bu faqat servicega tegishli
 */
    private String getVerificationCode() {
        return String.valueOf((int)(Math.random() * 9000) + 1000);
    }
}