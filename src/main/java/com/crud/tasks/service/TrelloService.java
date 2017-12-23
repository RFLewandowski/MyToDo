package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.card.CreatedTrelloCardDto;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class TrelloService {
    private static final String SUBJECT = "Tasks: New Trello Card";

    private final AdminConfig adminConfig;
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;

    @Autowired
    public TrelloService(AdminConfig adminConfig, TrelloClient trelloClient, SimpleEmailService emailService) {
        this.adminConfig = adminConfig;
        this.trelloClient = trelloClient;
        this.emailService = emailService;
    }


    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createdNewCard(trelloCardDto);

        Mail mail = new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account");

        ofNullable(newCard).ifPresent(card -> emailService.send(mail));

        return newCard;
    }
}
