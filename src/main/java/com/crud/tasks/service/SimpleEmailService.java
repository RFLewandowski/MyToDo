package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.MailConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleEmailService {
    private final JavaMailSender javaMailSender;
    private final MailConverter mailConverter;


    @Autowired
    public SimpleEmailService(JavaMailSender javaMailSender, MailConverter mailConverter) {
        this.javaMailSender = javaMailSender;
        this.mailConverter = mailConverter;
    }

    public void send(final Mail mail) {
        log.info("Starting email preparation...");
        try {
            MimeMessagePreparator mimeMessage = createMimeMessage(mail);
            javaMailSender.send(mimeMessage);
            log.info("Email has been send.");
        } catch (MailException e) {
            log.error("Failed to process email sending: ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail) {
        return mailConverter.convertToMimeMessage(mail);
    }

    private SimpleMailMessage createMailMessage(Mail mail) {
        return mailConverter.convertToSimpleMail(mail);
    }
}
