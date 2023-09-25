package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.RecipeIngredient}
 */
@AllArgsConstructor
@Getter
public class RecipeIngredientRequestDto implements Serializable {

    private final Long ingredientId;
    private final Integer quantity;
    private final Integer unitId;
    private final String process;
    private final String color;
    private final String part;
    private final String taste;
    private final String purpose;
}
