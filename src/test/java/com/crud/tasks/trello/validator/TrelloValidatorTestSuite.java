package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.board.TrelloBoard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)

public class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    @Test
    public void validateTrelloBoards() {
        //Given
        List<TrelloBoard> trelloBoardsList = new ArrayList<>();
        TrelloBoard trelloBoard = new TrelloBoard("dummyBoardId", "dummyBoardId", new ArrayList<>());
        TrelloBoard testTrelloBoard = new TrelloBoard("dummyBoardId", "dummyBoardId_test", new ArrayList<>());

        trelloBoardsList.add(trelloBoard);
        trelloBoardsList.add(testTrelloBoard);

        //When
        List<TrelloBoard> resultBoardList = trelloValidator.validateTrelloBoards(trelloBoardsList);

        //Then
        assertTrue(resultBoardList.size() == 1);
    }
}