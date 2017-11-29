package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
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
    @Autowired
    private RestTemplate restTemplate;

    //@Value("${trello.app.username}")
    private String trelloUsername = "rdim1"; //@Value not working

    //@Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint = "${trello.api.endpoint.prod}"; //= "https://api.trello.com/1"; //@Value not working
    ;

    //@Value("${trello.app.key}")
    private String trelloAppKey = "87f5af154471db449dcf1174bf34890f"; //@Value not working

    //@Value("${trello.app.token}")
    private String trelloToken = "7e0d86ef6f2816b6d0c92ae9ce54ef5a691e171515a0f2fd4e66395a38fbb01d"; //@Value not working

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken).build().encode().toUri();

        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);

        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>();
    }
}
