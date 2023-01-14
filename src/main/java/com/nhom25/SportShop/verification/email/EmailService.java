package com.nhom25.SportShop.verification.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String sender;

    private void sendMail(EmailModel emailModel) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailModel.getRecipient());
            mimeMessageHelper.setSubject(emailModel.getSubject());
            mimeMessageHelper.setText(emailModel.getContent(), true);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendOTP(String userEmail, Integer otpCode) throws MailException {
        String subject = "Here's your One Time Password (OTP) - Expire in 5 minutes!";
        String content = "<p>Hello !!"+"</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to verify:</p>"
                + "<p><b>" + otpCode + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 5 minutes.</p>";
        EmailModel emailModel = new EmailModel(userEmail, subject, content);
        sendMail(emailModel);
    }
}
