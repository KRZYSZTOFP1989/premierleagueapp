package com.premerleagueapp.premerleagueapp.rapidapi.controller;

import com.premerleagueapp.premerleagueapp.rapidapi.PremierLeagueTable;
import com.premerleagueapp.premerleagueapp.rapidapi.client.PremierLeagueTableClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v2/rapidapi")
public class RapidApiController {

    @Autowired
    PremierLeagueTableClient premierLeagueTableClient;

    @RequestMapping(method = RequestMethod.GET, value = "getRapidApiTable")
    public List<PremierLeagueTable> getTable() {
        return (List<PremierLeagueTable>) premierLeagueTableClient.getTable();
    }
}
