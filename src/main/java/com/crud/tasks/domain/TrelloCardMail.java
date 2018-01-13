package com.crud.tasks.domain;

public class TrelloCardMail extends Mail {
    public TrelloCardMail(String to, String cc, String subject, String message) {
        super(to, cc, subject, message);
    }

    public TrelloCardMail(String to, String subject, String message) {
        super(to, subject, message);
    }
}
