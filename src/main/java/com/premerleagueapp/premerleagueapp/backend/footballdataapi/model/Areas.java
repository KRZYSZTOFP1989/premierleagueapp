package com.premerleagueapp.premerleagueapp.backend.footballdataapi.model;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Areas {

    private List<Area> areas = null;

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Areas [areas=");
        builder.append(areas);
        builder.append("]");
        return builder.toString();
    }

}
