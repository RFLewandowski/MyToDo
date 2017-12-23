package com.crud.tasks.domain;

import com.crud.tasks.domain.board.TrelloBoard;
import com.crud.tasks.domain.board.TrelloBoardDto;
import com.crud.tasks.domain.board.TrelloList;
import com.crud.tasks.domain.board.TrelloListDto;
import com.crud.tasks.domain.card.TrelloCard;
import com.crud.tasks.domain.card.TrelloCardDto;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void Should_mapToBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("dummyBoardId", "dummyBoardName", new ArrayList<>());
        trelloBoardDtoList.add(trelloBoardDto);
        //When
        List<TrelloBoard> trelloBoardList = trelloMapper.mapToBoards(trelloBoardDtoList);
        //Then
        assertEquals(trelloBoardDtoList.get(0).getName(),
                trelloBoardList.get(0).getName());
    }

    @Test
    public void Should_mapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoardList = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("dummyBoardId", "dummyBoardName", new ArrayList<>());
        trelloBoardList.add(trelloBoard);
        //When
        List<TrelloBoardDto> trelloBoardDtoList = trelloMapper.mapToBoardsDto(trelloBoardList);
        //Then
        assertEquals(trelloBoardList.get(0).getName(),
                trelloBoardDtoList.get(0).getName());
    }

    @Test
    public void Should_mapToList() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("dummyListId", "dummyListName", false);
        trelloListDtoList.add(trelloListDto);
        //When
        List<TrelloList> trelloListList = trelloMapper.mapToList(trelloListDtoList);
        //Then
        assertEquals(trelloListDtoList.get(0).getName(), trelloListList.get(0).getName());
    }

    @Test
    public void Should_mapToListDto() {
        //Given
        List<TrelloList> trelloListList = new ArrayList<>();
        TrelloList trelloList = new TrelloList("dummyListId", "dummyListName", false);
        trelloListList.add(trelloList);
        //When
        List<TrelloListDto> trelloListDtoList = trelloMapper.mapToListDto(trelloListList);
        //Then
        assertEquals(trelloListList.get(0).getName(), trelloListDtoList.get(0).getName());
    }

    @Test
    public void Should_mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("dummyCardName", "dummyCardDescription", "top", "dummyListId");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(trelloCard.getName(), trelloCardDto.getName());
    }

    @Test
    public void Should_mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("dummyCardName", "dummyCardDescription", "top", "dummyListId");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(trelloCardDto.getName(), trelloCard.getName());
    }
}