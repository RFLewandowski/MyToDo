package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CoreConfiguration;
import com.crud.tasks.domain.DailySummaryMail;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final CoreConfiguration coreConfiguration;
    private final AdminConfig adminConfig;

    @Autowired
    public EmailScheduler(SimpleEmailService simpleEmailService, TaskRepository taskRepository, CoreConfiguration coreConfiguration, AdminConfig adminConfig) {
        this.simpleEmailService = simpleEmailService;
        this.taskRepository = taskRepository;
        this.coreConfiguration = coreConfiguration;
        this.adminConfig = adminConfig;
    }

    //@Scheduled(cron = "0 0 10 * * *")
    @Scheduled(fixedDelay = 20000)
    public void sendInformationEmail() {
        long size = taskRepository.count();
        Mail dailySummaryMail = new DailySummaryMail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "Currently in database you got: " + size + " task" + getPluralEnding(size));
        simpleEmailService.send(dailySummaryMail);
    }

    private String getPluralEnding(long size) {
        String ending = "";
        if (size > 1) ending = "s";
        return ending;
    }
}
