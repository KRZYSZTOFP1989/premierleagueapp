package com.premerleagueapp.premerleagueapp.backend.mapper;

import com.premerleagueapp.premerleagueapp.backend.domain.Position;
import com.premerleagueapp.premerleagueapp.backend.dto.PositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class PositionMapper {

    private final PlayerMapper playerMapper;

    public Position MapToPosition(final PositionDto positionDto) throws Exception {
        Position position = new Position();
        position.setId(positionDto.getId());
        position.setName(positionDto.getName());
        return position;
    }

    public PositionDto MapToPositionDto(final Position position) {
        PositionDto positionDto = new PositionDto();
        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        positionDto.setPlayers(playerMapper.MapToPlayerDtoList(position.getPlayers()));
        return positionDto;
    }

    public List<PositionDto> MapToPositionDtoList(List<Position> positionList) {
        return positionList.stream()
                .map(this::MapToPositionDto)
                .collect(Collectors.toList());
    }
}
