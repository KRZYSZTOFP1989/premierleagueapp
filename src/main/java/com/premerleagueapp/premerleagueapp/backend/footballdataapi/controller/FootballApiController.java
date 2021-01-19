package com.premerleagueapp.premerleagueapp.backend.footballdataapi.controller;

import com.premerleagueapp.premerleagueapp.backend.footballdataapi.client.FootballApiClient;
import com.premerleagueapp.premerleagueapp.backend.footballdataapi.model.CompetitionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/footballapi/")
public class FootballApiController {

    @Autowired
    private FootballApiClient footballApiClient;

    @RequestMapping(method = RequestMethod.GET, value = "getFootballApiTable")
    public List<CompetitionDto> getFootballApiTable() {
        return footballApiClient.getFootballApiTable();
    }
}
