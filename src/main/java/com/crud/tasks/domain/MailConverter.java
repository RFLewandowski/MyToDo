package com.crud.tasks.domain;

import com.crud.tasks.service.MailCreatorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Slf4j
@Component
public class MailConverter {

    private String dailySummaryText;
    private String trelloCardText;
    private final MailCreatorService mailCreatorService;

    @Autowired
    public MailConverter(MailCreatorService mailCreatorService) {
        this.mailCreatorService = mailCreatorService;
    }

    @Nullable
    public SimpleMailMessage convertToSimpleMail(Mail source) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(source.getTo());
        if (StringUtils.isNotEmpty(source.getCc())) mailMessage.setCc(source.getCc());
        mailMessage.setSubject(source.getSubject());
        mailMessage.setText(source.getMessage());
        return mailMessage;
    }

    public MimeMessagePreparator convertToMimeMessage(Mail source) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(source.getTo());
            messageHelper.setSubject(source.getSubject());
            dailySummaryText = mailCreatorService.buildTrelloCardEmail(source.getMessage());
            trelloCardText = mailCreatorService.buildDailySummaryEmail(source.getMessage());
            setTextPerRequiredMailType(source, messageHelper);
        };
    }

    private void setTextPerRequiredMailType(Mail source, MimeMessageHelper messageHelper) throws MessagingException {
        if (source instanceof TrelloCardMail) {
            messageHelper.setText(dailySummaryText, true);
        } else if (source instanceof DailySummaryMail) {
            messageHelper.setText(trelloCardText, true);
        } else {
            throw new IllegalArgumentException("Failed to prepare email - unknown email type.");
        }
    }
}