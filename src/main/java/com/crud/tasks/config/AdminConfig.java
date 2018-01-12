package com.crud.tasks.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AdminConfig {
    @Value("${admin.name}")
    private String adminName;
    @Value("${admin.mail}")
    private String adminMail;
    @Value("${info.company.name}")
    private String companyName;
    @Value("${info.app.administrator.address.city}")
    private String administratorCity;
    @Value("${info.app.administrator.address.street}")
    private String administratorStreet;
    @Value("${info.app.administrator.address.number}")
    private String administratorStreetNumber;

    public String getCompanyNameAndAdminAddress() {
        return companyName+", "+administratorCity + ", " + administratorStreet + " " + administratorStreetNumber;
    }
}
