package com.pli.codes.platepal.cookbook.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.RecipeIngredientList}
 */
@AllArgsConstructor
@Getter
public class RecipeIngredientListRequestDto {

    private final String listTitle;
    private final List<RecipeIngredientRequestDto> recipeIngredients;
}
