package com.premerleagueapp.premerleagueapp.backend.footballdataapi.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Competition {

    private long id;
    private String name;
    private String code;
    private String emblemUrl;
    private String plan;

    private String lastUpdated;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmblemUrl() {
        return emblemUrl;
    }

    public void setEmblemUrl(String emblemUrl) {
        this.emblemUrl = emblemUrl;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Competition [id=");
        builder.append(id);
        builder.append(", name=");
        builder.append(name);
        builder.append(", code=");
        builder.append(code);
        builder.append(", emblemUrl=");
        builder.append(emblemUrl);
        builder.append(", plan=");
        builder.append(plan);
        builder.append(", lastUpdated=");
        builder.append(lastUpdated);
        builder.append("]");
        return builder.toString();
    }
}
