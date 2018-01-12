package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    private final TemplateEngine templateEngine;
    private final AdminConfig adminConfig;

    @Autowired
    public MailCreatorService(@Qualifier("templateEngine") TemplateEngine templateEngine, AdminConfig adminConfig) {
        this.templateEngine = templateEngine;
        this.adminConfig = adminConfig;
    }

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("preview_message", "[TASK ADDED] ");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Have a nice day:)");
        context.setVariable("company_name_and_admin_address", adminConfig.getCompanyNameAndAdminAddress());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
