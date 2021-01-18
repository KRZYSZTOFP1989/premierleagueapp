package com.premerleagueapp.premerleagueapp.backend.service;

import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position getPositionById(final Long id) throws Exception {
        return positionRepository.findById(id).orElseThrow(()
                -> new Exception("Position not found"));
    }

    public Position savePosition(final Position position) {
        return positionRepository.save(position);
    }

    public void deletePosition(final Long positionId) {
        positionRepository.deleteById(positionId);
    }
}
