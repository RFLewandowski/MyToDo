package com.crud.tasks.domain;

import lombok.Getter;

@Getter
public class Mail {
    private String to;
    private String cc = null;
    private String subject;
    private String message;

    public Mail(String to, String cc, String subject, String message) {
        this.to = to;
        this.cc = cc;
        this.subject = subject;
        this.message = message;
    }

    public Mail(String to, String subject, String message) {
        this(to, null, subject, message);
    }
}
