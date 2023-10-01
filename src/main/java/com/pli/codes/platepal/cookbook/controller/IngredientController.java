package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.cookbook.model.dto.IngredientDto;
import com.pli.codes.platepal.cookbook.model.dto.IngredientRequestDto;
import com.pli.codes.platepal.cookbook.model.entity.Ingredient;
import com.pli.codes.platepal.cookbook.model.mapper.IngredientMapper;
import com.pli.codes.platepal.cookbook.service.IngredientService;
import java.util.Collection;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ingredient")
@CrossOrigin
public class IngredientController {

    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<IngredientDto> createIngredient(@RequestBody IngredientRequestDto ingredientRequestDto) {
        Ingredient ingredient = service.createIngredient(ingredientRequestDto.getIngredientName());
        return ResponseEntity.ok(IngredientMapper.INSTANCE.toDto(ingredient));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<IngredientDto>> createIngredients(
        @RequestBody Collection<IngredientRequestDto> ingredientRequestDtos
    ) {
        List<IngredientDto> ingredientDtos = ingredientRequestDtos.stream()
            .map(dto -> service.createIngredient(dto.getIngredientName()))
            .map(IngredientMapper.INSTANCE::toDto)
            .toList();
        return ResponseEntity.ok(ingredientDtos);
    }
}
