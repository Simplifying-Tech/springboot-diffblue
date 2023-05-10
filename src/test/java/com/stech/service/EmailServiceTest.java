package com.stech.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Method under test: {@link EmailService#sendEmail()}
     */
    @Test
    void testSendEmail() throws MailException {
        doNothing().when(javaMailSender).send((SimpleMailMessage) any());
        assertEquals("Mail sent successfully", emailService.sendEmail());
        verify(javaMailSender).send((SimpleMailMessage) any());
    }

    /**
     * Method under test: {@link EmailService#sendEmailwithAttachment()}
     */
    @Test
    void testSendEmailwithAttachment() throws MailException {
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        assertEquals("Mail sent successfully", emailService.sendEmailwithAttachment());
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send((MimeMessage) any());
    }

    /**
     * Method under test: {@link EmailService#sendEmailwithAttachment()}
     */
    @Test
    void testSendEmailwithAttachment2() throws MailException {
        doNothing().when(javaMailSender).send((MimeMessage) any());
        when(javaMailSender.createMimeMessage()).thenReturn(null);
        assertEquals("Mail sent failed", emailService.sendEmailwithAttachment());
        verify(javaMailSender).createMimeMessage();
    }
}

