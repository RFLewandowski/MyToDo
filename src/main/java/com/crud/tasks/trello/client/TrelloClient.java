package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TrelloClient {

    private final RestTemplate restTemplate;

    //@Value("${trello.app.username}")//@Value annotation not working
    private String trelloUsername = "rdim1";

    //@Value("${trello.api.endpoint.prod}")//@Value annotation not working
    private String trelloApiEndpoint = "https://api.trello.com/1";

    //@Value("${trello.app.key}")//@Value annotation not working
    private String trelloAppKey = "87f5af154471db449dcf1174bf34890f";

    //@Value("${trello.app.token}")//@Value annotation not working
    private String trelloToken = "7e0d86ef6f2816b6d0c92ae9ce54ef5a691e171515a0f2fd4e66395a38fbb01d";

    @Autowired
    public TrelloClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = buildTrelloGetBoardsURI();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }

    public CreatedTrelloCard createdNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();
        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }


    private URI buildTrelloGetBoardsURI() {
        return UriComponentsBuilder
                .fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();
    }
}
