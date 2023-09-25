package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Ingredient}
 */
@AllArgsConstructor
@Getter
public class IngredientDto implements Serializable {

    private final Long id;
    private final String ingredientName;
    private final String defaultImagePath;
}
