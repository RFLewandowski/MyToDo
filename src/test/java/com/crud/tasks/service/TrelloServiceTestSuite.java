package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.board.TrelloListDto;
import com.crud.tasks.domain.card.CreatedTrelloCardDto;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;


    @Test
    public void fetchTrelloBoards() throws Exception {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "dummy_list", false));
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "dummy", trelloLists));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> actualResult = trelloService.fetchTrelloBoards();

        //Then
        assertEquals(trelloBoards.get(0).getLists().get(0).getName(), actualResult.get(0).getLists().get(0).getName());
    }

    @Test
    public void createTrelloCard() throws Exception {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("dummyCardName", "dummyDescName", "top", "dummyList");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("dummyCardId", "dummyCardName", "dummyCardURL");
        when(trelloClient.createdNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto actualCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        //Then
        assertEquals(createdTrelloCardDto.getName(),actualCreatedTrelloCardDto.getName());
        verify(adminConfig,times(1)).getAdminMail();
        verify(emailService,times(1)).send(any());
    }
}