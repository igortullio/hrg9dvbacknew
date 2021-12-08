package com.igortullio.hering.adapter.email;

import com.igortullio.hering.core.domain.Email;
import com.igortullio.hering.core.port.EmailSendPort;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.util.Date;

@Component
public class EmailSendImpl implements EmailSendPort {

    private final JavaMailSender javaMailSender;

    public EmailSendImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void send(Email email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);

            mmh.setFrom(email.getFrom());
            mmh.setTo(email.getTo());
            mmh.setSubject(email.getSubject());
            mmh.setText(email.getText());
            mmh.setSentDate(new Date());

            String string = "{" +
                    "\"id\": 1," +
                    "\"name\": \"Igor Túllio\"," +
                    "\"age\": 24," +
                    "\"phone\": \"61984058320\"," +
                    "\"email\": \"igortullio@hotmail.com\"" +
                    "}";
            ByteArrayDataSource source = new ByteArrayDataSource(string.getBytes(), "application/json");
            mmh.addAttachment("attachment", source);
        } catch (MessagingException e) {
            throw new MailParseException(e);
        }

        javaMailSender.send(mimeMessage);
    }

}
