package uz.pdp.backend.service.gmailservice;

import javax.mail.internet.AddressException;

public interface GmailService {
    void registerMailService(String recipient) throws AddressException;
    String  verificationCode();
}
