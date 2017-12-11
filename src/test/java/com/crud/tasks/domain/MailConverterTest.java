package com.crud.tasks.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MailConverterTest {
    @InjectMocks
    private MailConverter mailConverter;

    @Test
    public void ShouldConvertMailToSimpleMailMessage() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message");
        SimpleMailMessage expectedMailMessage = new SimpleMailMessage();
        expectedMailMessage.setTo(mail.getTo());
        expectedMailMessage.setSubject(mail.getSubject());
        expectedMailMessage.setText(mail.getMessage());
        //When
        SimpleMailMessage actualMailMessage = mailConverter.convert(mail);
        //Then
        assertNotNull(actualMailMessage);
        assertArrayEquals(expectedMailMessage.getTo(), actualMailMessage.getTo());
        assertArrayEquals(expectedMailMessage.getCc(), actualMailMessage.getCc());
        assertEquals(expectedMailMessage.getSubject(), actualMailMessage.getSubject());
        assertEquals(expectedMailMessage.getText(), actualMailMessage.getText());
    }
}