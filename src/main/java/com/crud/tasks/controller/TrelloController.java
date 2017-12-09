package com.crud.tasks.controller;


import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.card.CreatedTrelloCard;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    private final TrelloService trelloService;

    @Autowired
    public TrelloController(TrelloService trelloService) {
        this.trelloService = trelloService;
    }

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }
}