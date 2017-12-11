package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.MailConverter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private MailConverter mailConverter;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void ShouldSendEmailWithCC() {
        //Given
        Mail mail = new Mail("test@test.com", "testCC@test.com", "Test", "Test message");
        SimpleMailMessage actualMailMessage = convertMail(mail);
        when(mailConverter.convert(mail)).thenReturn(actualMailMessage);

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(actualMailMessage);
    }

    @Test
    public void ShouldSendEmailWithoutCC() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        SimpleMailMessage actualMailMessage = convertMail(mail);
        when(mailConverter.convert(mail)).thenReturn(actualMailMessage);

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(actualMailMessage);
    }

    private SimpleMailMessage convertMail(Mail mail) {
        MailConverter actualMailConverter = new MailConverter();
        return actualMailConverter.convert(mail);
    }
}