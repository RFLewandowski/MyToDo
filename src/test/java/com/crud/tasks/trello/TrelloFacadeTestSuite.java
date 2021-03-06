package com.crud.tasks.trello;

import com.crud.tasks.domain.board.TrelloBoard;
import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.board.TrelloList;
import com.crud.tasks.domain.board.TrelloListDto;
import com.crud.tasks.domain.card.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloFacadeTestSuite {

    @InjectMocks
    private TrelloFacade trelloFacade;

    @Mock
    private TrelloService trelloService;

    @Mock
    private TrelloValidator trelloValidator;

    @Mock
    private TrelloMapper trelloMapper;

    @Test
    public void Should_FetchEmptyList() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "test_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "test_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloValidator.filterTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
        when(trelloMapper.mapToBoardsDto(new ArrayList<>())).thenReturn(new ArrayList<>());

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(0, trelloBoardDtos.size());
    }

    @Test
    public void Should_FetchTrelloBoards() {
        //Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "my_list", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloLists));

        List<TrelloList> mappedTrelloLists = new ArrayList<>();
        mappedTrelloLists.add(new TrelloList("1", "my_list", false));

        List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
        mappedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));

        when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
        when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
        when(trelloMapper.mapToBoardsDto(anyList())).thenReturn(trelloBoards);
        when(trelloValidator.filterTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);

        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
        TrelloBoardDto trelloBoardDto = trelloBoardDtos.get(0);

        //Then
        assertNotNull(trelloBoardDtos);
        assertEquals(1, trelloBoardDtos.size());

        assertEquals("1", trelloBoardDto.getId());
        assertEquals("my_task", trelloBoardDto.getName());

        assertEquals("1", trelloBoardDto.getLists().get(0).getId());
        assertEquals("my_list", trelloBoardDto.getLists().get(0).getName());
        assertEquals(false, trelloBoardDto.getLists().get(0).isClosed());
    }

    @Test
    public void Should_CreateCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("dummyCardName", "dummyDescName", "top", "dummyList");
        TrelloCard trelloCard = new TrelloCard("dummyCardName", "dummyDescName", "top", "dummyList");
        TrelloDto trelloDto = new TrelloDto(1, 1);
        AttachmentByTypeDto attachmentByTypeDto = new AttachmentByTypeDto(trelloDto);
        BadgesDto badgesDto = new BadgesDto(1, attachmentByTypeDto);
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("dummyCardId", "dummyCardName", "dummyCardURL", badgesDto);


        when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //When
        CreatedTrelloCardDto expectedTrelloCardDto = trelloFacade.createCard(trelloCardDto);

        //Then
        assertEquals(trelloCardDto.getName(), expectedTrelloCardDto.getName());
        verify(trelloValidator, times(1)).checkCard(trelloCard);
    }
}