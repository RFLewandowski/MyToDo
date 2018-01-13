package com.crud.tasks.service;

import com.crud.tasks.domain.DailySummaryMail;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.MailConverter;
import com.crud.tasks.domain.TrelloCardMail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private MailConverter mailConverter;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private MailCreatorService mailCreatorService;


    @Test
    public void ShouldSendTrelloCardEmail() throws Exception {
        //Given
        Mail mail = new TrelloCardMail("test@test.com", "Test", "Test message");
        MimeMessagePreparator actualMimeMessage = convertToMimeMessage(mail);
        when(mailConverter.convertToMimeMessage(mail)).thenReturn(actualMimeMessage);

        //When
        simpleEmailService.send(mail);

        //then
        verify(javaMailSender, times(1)).send(actualMimeMessage);
    }

    @Test
    public void ShouldSendSummaryEmail() throws Exception {
        //Given
        Mail mail = new DailySummaryMail("test@test.com", "Test", "Test message");
        MimeMessagePreparator actualMimeMessage = convertToMimeMessage(mail);
        when(mailConverter.convertToMimeMessage(mail)).thenReturn(actualMimeMessage);

        //When
        simpleEmailService.send(mail);

        //then
        verify(javaMailSender, times(1)).send(actualMimeMessage);
    }

    private MimeMessagePreparator convertToMimeMessage(Mail mail){
        MailConverter converter = new MailConverter(mailCreatorService);
        return converter.convertToMimeMessage(mail);
    }

}