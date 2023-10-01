package com.pli.codes.platepal.cookbook.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.RecipeIngredientList}
 */
@AllArgsConstructor
@Getter
public class RecipeIngredientListDto {

    private final Long id;
    private final String listTitle;
    private final List<RecipeIngredientDto> recipeIngredients;
}
