package uz.pdp.backend.service.gmailservice;

import lombok.SneakyThrows;
import uz.pdp.backend.service.isvalid.IsValid;

import javax.mail.*;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GmailServiceimp implements GmailService {
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
        Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
                message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
                message.setSubject("Verification code");
            message.setContent("<h2>Verification Code</h2>" +
                "           <p>Your verification code is: <strong>" + getVerificationCode() + "</strong></p>","text/html");
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
    public boolean checkVerificationCode(int verificationCode) {
        return getVerificationCode() == verificationCode;
    }
/**
 * verification codeni qaytaradi, bu faqat servicega tegishli
 */
    private int getVerificationCode() {
        Random random = new Random();
        return random.nextInt(9000) + 1000;
    }

    /**
     * bu gmail sintaksis jihatdan to'g'ri yozilganmi yoki yo'qmi tekshiradi
     */
    public boolean isValidGmail(String gmail) {
        return IsValid.isValidGmail(gmail);
    }
}