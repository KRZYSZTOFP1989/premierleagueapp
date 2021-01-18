package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.Position;

import java.util.List;

public interface PositionService {

    List<Position> getAllPositions();
    Position getPositionById(final Long positionId) throws Exception;
    void deletePosition(final Long positionId);
    Position savePosition(final Position position);
}
