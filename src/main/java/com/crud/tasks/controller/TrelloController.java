package com.crud.tasks.controller;


import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.card.CreatedTrelloCard;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    private final TrelloClient trelloClient;

    @Autowired
    public TrelloController(TrelloClient trelloClient) {
        this.trelloClient = trelloClient;
    }

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {


        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards = trelloBoards
                .stream()
                .filter(trelloBoardDto -> trelloBoardDto.getId() != null
                        && trelloBoardDto.getName()
                        .contains("Kodilla"))
                .collect(Collectors.toList());
        return trelloBoards;
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createdNewCard(trelloCardDto);
    }
}