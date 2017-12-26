package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.board.TrelloBoard;
import com.crud.tasks.domain.card.TrelloCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TrelloValidator {
    public void validateCard(final TrelloCard trelloCard) {
        if (trelloCard
                .getName()
                .toLowerCase()
                .contains("test")) {
            log.info("Someone is testing my application!");
        } else {
            log.info("Seems that my application is used in propper way.");
        }
    }

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        log.info("Starting filtering boards...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
                .filter(trelloBoard -> !trelloBoard
                        .getName()
                        .toLowerCase()
                        .contains("test"))
                .collect(Collectors.toList());
        log.info("Boards has been filtered. Current list size: " + filteredBoards.size());
        return filteredBoards;
    }

}