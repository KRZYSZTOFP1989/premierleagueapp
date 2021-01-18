package com.premerleagueapp.premerleagueapp.backend.rapidapi.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;

import com.premerleagueapp.premerleagueapp.backend.rapidapi.PremierLeagueTable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PremierLeagueTableClient {

    @Value("${rapidapi.football.url}")
    String footballUrl;
    @Value("${rapidapi.key.name}")
    String apiKeyName;
    @Value("${rapidapi.key.value}")
    String apiKeyValue;
    @Value("${rapidapi.host.name}")
    String hostName;
    @Value("${rapidapi.host.football.value}")
    String hostValue;

    RestTemplate restTemplate;
    public PremierLeagueTableClient (RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder.build();
    }
    public PremierLeagueTable getTable() {
        PremierLeagueTable table = null;
        try {
            URI uri;
            uri = new URI(footballUrl);
            HttpHeaders headers = new HttpHeaders();
            headers.set(apiKeyName, apiKeyValue);
            headers.set(hostName, hostValue);
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<String> request = new HttpEntity<String>(headers);
            ResponseEntity<PremierLeagueTable[]> totalEntity = restTemplate.exchange(uri, HttpMethod.GET, request,
                    PremierLeagueTable[].class);
            table = totalEntity.getBody()[0];
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return table;
    }
}
