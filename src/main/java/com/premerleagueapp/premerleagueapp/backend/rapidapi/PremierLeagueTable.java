package com.premerleagueapp.premerleagueapp.backend.rapidapi;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "rank",
    "team_id",
    "teamName",
    "matchsPlayed",
    "win",
    "draw",
    "lose",
    "goalsFor",
    "goalsAgainst",
    "goalsDiff",
    "points"
})
public class PremierLeagueTable {

    @JsonProperty("rank")
    private Long rank;
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("teamName")
    private String teamName;
    @JsonProperty("matchsPlayed")
    private Long matchsPlayed;
    @JsonProperty("win")
    private Long win;
    @JsonProperty("draw")
    private Long draw;
    @JsonProperty("lose")
    private Long lose;
    @JsonProperty("goalsFor")
    private Long goalsFor;
    @JsonProperty("goalsAgainst")
    private Long goalsAgainst;
    @JsonProperty("goalsDiff")
    private Long goalsDiff;
    @JsonProperty("points")
    private Long points;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("rank")
    public Long getRank() {
        return rank;
    }

    @JsonProperty("rank")
    public void setRank(Long rank) {
        this.rank = rank;
    }

    @JsonProperty("team_id")
    public Long getTeamId() {
        return teamId;
    }

    @JsonProperty("team_id")
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @JsonProperty("teamName")
    public String getTeamName() {
        return teamName;
    }

    @JsonProperty("teamName")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @JsonProperty("matchsPlayed")
    public Long getMatchsPlayed() {
        return matchsPlayed;
    }

    @JsonProperty("matchsPlayed")
    public void setMatchsPlayed(Long matchsPlayed) {
        this.matchsPlayed = matchsPlayed;
    }

    @JsonProperty("win")
    public Long getWin() {
        return win;
    }

    @JsonProperty("win")
    public void setWin(Long win) {
        this.win = win;
    }

    @JsonProperty("draw")
    public Long getDraw() {
        return draw;
    }

    @JsonProperty("draw")
    public void setDraw(Long draw) {
        this.draw = draw;
    }

    @JsonProperty("lose")
    public Long getLose() {
        return lose;
    }

    @JsonProperty("lose")
    public void setLose(Long lose) {
        this.lose = lose;
    }

    @JsonProperty("goalsFor")
    public Long getGoalsFor() {
        return goalsFor;
    }

    @JsonProperty("goalsFor")
    public void setGoalsFor(Long goalsFor) {
        this.goalsFor = goalsFor;
    }

    @JsonProperty("goalsAgainst")
    public Long getGoalsAgainst() {
        return goalsAgainst;
    }

    @JsonProperty("goalsAgainst")
    public void setGoalsAgainst(Long goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    @JsonProperty("goalsDiff")
    public Long getGoalsDiff() {
        return goalsDiff;
    }

    @JsonProperty("goalsDiff")
    public void setGoalsDiff(Long goalsDiff) {
        this.goalsDiff = goalsDiff;
    }

    @JsonProperty("points")
    public Long getPoints() {
        return points;
    }

    @JsonProperty("points")
    public void setPoints(Long points) {
        this.points = points;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
