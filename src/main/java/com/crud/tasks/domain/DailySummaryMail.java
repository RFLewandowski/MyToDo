package com.crud.tasks.domain;

public class DailySummaryMail extends Mail {
    public DailySummaryMail(String to, String cc, String subject, String message) {
        super(to, cc, subject, message);
    }

    public DailySummaryMail(String to, String subject, String message) {
        super(to, subject, message);
    }
}
