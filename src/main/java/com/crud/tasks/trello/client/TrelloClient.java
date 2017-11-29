package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TrelloClient {
    @Autowired
    RestTemplate restTemplate;

//    @Value("${trello.api.endpoint.prod}")
//    String trelloApiEndpoint;
//
//    @Value("${trello.app.key}")
//    String trelloAppKey;
//
//    @Value("${trello.app.token}")
//    String trelloToken;
//
//    public List<TrelloBoardDto> getTrelloBoards() {
//
//        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/rdim1/boards")
//                .queryParam("key", trelloAppKey)
//                .queryParam("token", trelloToken).build().encode().toUri();
//
//        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
//
//        if (boardsResponse != null) {
//            return Arrays.asList(boardsResponse);
//        }
//        return new ArrayList<>();
//
//
//    }
}
