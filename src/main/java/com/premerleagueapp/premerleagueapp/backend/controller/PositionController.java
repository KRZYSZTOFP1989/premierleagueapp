package com.premerleagueapp.premerleagueapp.backend.controller;

import com.premerleagueapp.premerleagueapp.backend.dto.PositionDto;
import com.premerleagueapp.premerleagueapp.backend.mapper.PositionMapper;
import com.premerleagueapp.premerleagueapp.backend.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/")
public class PositionController {

    private final PositionMapper positionMapper;
    private final PositionService positionService;

    @RequestMapping(method = RequestMethod.GET, value = "/position")
    public List<PositionDto> getPositions() {
        return positionMapper.MapToPositionDtoList(positionService.getAllPositions());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/position/{positionId}")
    public PositionDto getPosition(@PathVariable Long positionId) throws Exception {
        return positionMapper.MapToPositionDto(positionService.getPositionById(positionId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "position", consumes = APPLICATION_JSON_VALUE)
    public void createPosition(@RequestBody PositionDto positionDto) throws Exception {
        positionService.savePosition(positionMapper.MapToPosition(positionDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/position/{positionId}")
    public void deletePosition(@PathVariable Long positionId) {
        positionService.deletePosition(positionId);
    }
}
