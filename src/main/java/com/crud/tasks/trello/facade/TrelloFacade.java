package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.TrelloMapper;
import com.crud.tasks.domain.board.TrelloBoard;
import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.card.CreatedTrelloCardDto;
import com.crud.tasks.domain.card.TrelloCard;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TrelloFacade {

    private final TrelloService trelloService;

    private final TrelloMapper trelloMapper;

    private final TrelloValidator trelloValidator;

    @Autowired
    public TrelloFacade(TrelloService trelloService, TrelloMapper trelloMapper, TrelloValidator trelloValidator) {
        this.trelloService = trelloService;
        this.trelloMapper = trelloMapper;
        this.trelloValidator = trelloValidator;
    }

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto creteCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
