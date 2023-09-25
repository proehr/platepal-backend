package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.RecipeIngredientList}
 */
@AllArgsConstructor
@Getter
public class RecipeIngredientListDto implements Serializable {

    private final Long id;
    private final String listTitle;
    private final List<RecipeIngredientDto> recipeIngredients;
}
