package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.cookbook.model.dto.UnitDto;
import com.pli.codes.platepal.cookbook.model.dto.UnitRequestDto;
import com.pli.codes.platepal.cookbook.model.entity.Unit;
import com.pli.codes.platepal.cookbook.model.mapper.UnitMapper;
import com.pli.codes.platepal.cookbook.service.UnitService;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unit")
@CrossOrigin
public class UnitController {

    private final UnitService service;

    public UnitController(UnitService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UnitDto> createUnit(@RequestBody UnitRequestDto unitRequestDto) {
        Unit unit = service.createUnit(
            unitRequestDto.getUnitType(),
            unitRequestDto.getFulltext(),
            unitRequestDto.getAbbreviation()
        );
        return ResponseEntity.ok(UnitMapper.INSTANCE.toDto(unit));
    }


    @PostMapping("/batch")
    public ResponseEntity<List<UnitDto>> createUnits(
        @RequestBody Collection<UnitRequestDto> unitRequestDtos
    ) {
        List<UnitDto> unitDtos = unitRequestDtos.stream()
            .map(dto -> service.createUnit(dto.getUnitType(), dto.getFulltext(), dto.getAbbreviation()))
            .map(UnitMapper.INSTANCE::toDto)
            .toList();
        return ResponseEntity.ok(unitDtos);
    }
}
