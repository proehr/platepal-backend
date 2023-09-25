package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.RecipeIngredient}
 */
@AllArgsConstructor
@Getter
public class RecipeIngredientDto implements Serializable {

    private final IngredientDto ingredient;
    private final Integer quantity;
    private final UnitDto unit;
    private final String process;
    private final String color;
    private final String part;
    private final String taste;
    private final String purpose;
}
