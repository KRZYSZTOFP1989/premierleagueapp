package com.premerleagueapp.premerleagueapp.backend.footballdataapi.client;

import com.premerleagueapp.premerleagueapp.backend.footballdataapi.config.FootballApiConfig;
import com.premerleagueapp.premerleagueapp.backend.footballdataapi.model.Competition;
import com.premerleagueapp.premerleagueapp.backend.footballdataapi.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
public class FootballApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(FootballApiClient.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FootballApiConfig footballApiConfig;

    public List<Competition> getFootballApiTable() {

        URI url = getFootballApiTableUri();

        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("X-Auth-Token", "f86144f9f8f94a3e9f08a117203fd2e4");
            HttpEntity<?> entity = new HttpEntity<Object>(headers);
            Competition[] tableResponse = restTemplate.exchange(url, HttpMethod.GET, entity, Competition[].class).getBody();
            return Arrays.asList(Optional.ofNullable(tableResponse).orElse(new Competition[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI getFootballApiTableUri() {
        return UriComponentsBuilder.fromHttpUrl(footballApiConfig.getFootballApiEndpoint() + "competitions/PL/standings")
                .build().encode().toUri();
    }

    public List<Player> getFootballApiScorers() {

        URI url = getFootballApiScorersUri();

        try {
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            headers.add("X-Auth-Token", "f86144f9f8f94a3e9f08a117203fd2e4");
            HttpEntity<?> entity = new HttpEntity<Object>(headers);
            Player[] tableResponse = restTemplate.exchange(url, HttpMethod.GET, entity, Player[].class).getBody();
            return Arrays.asList(Optional.ofNullable(tableResponse).orElse(new Player[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    private URI getFootballApiScorersUri() {
        return UriComponentsBuilder.fromHttpUrl(footballApiConfig.getFootballApiEndpoint() + "competitions/PL/scorers")
                .build().encode().toUri();
    }
}
