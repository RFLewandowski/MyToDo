package com.crud.tasks.domain;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class MailConverter implements Converter<Mail, SimpleMailMessage> {

    @Nullable
    @Override
    public SimpleMailMessage convert(Mail source) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(source.getTo());
        if (StringUtils.isNotEmpty(source.getCc())) mailMessage.setCc(source.getCc());
        mailMessage.setSubject(source.getSubject());
        mailMessage.setText(source.getMessage());
        return mailMessage;
    }
}
