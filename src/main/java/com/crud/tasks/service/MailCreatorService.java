package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    private final Context context;
    private final TemplateEngine templateEngine;
    private final AdminConfig adminConfig;

    @Autowired
    public MailCreatorService(TemplateEngine templateEngine, AdminConfig adminConfig, Context context) { //TemplateEngine templateEngine there is indeed one bean in spring context intellij lies:)
        this.templateEngine = templateEngine;
        this.adminConfig = adminConfig;
        this.context = context;
    }

    public String buildTrelloCardEmail(String message) {

        context.clearVariables();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://trello.com");
        context.setVariable("button", "Visit Trello");
        context.setVariable("preview_message", "[TASK ADDED] ");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Kind Regards,");
        context.setVariable("company_name_and_admin_address", adminConfig.getCompanyNameAndAdminAddress());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildDailySummaryEmail(String message) {
        List<String> funcionality = new ArrayList<>();
        funcionality.add("You can manage your tasks");
        funcionality.add("Provides connection with Trello Account");
        funcionality.add("Allows sending tasks to trello");

        context.clearVariables();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "http://localhost:8888/crud");
        context.setVariable("button", "Visit website");
        context.setVariable("preview_message", "[DAILY SUMMARY] ");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye_message", "Have a nice day:)");
        context.setVariable("company_name_and_admin_address", adminConfig.getCompanyNameAndAdminAddress());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("application_functionality", funcionality);
        return templateEngine.process("mail/daily-summary-mail", context);
    }
}
