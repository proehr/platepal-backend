package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import java.time.Duration;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Recipe}
 */
@AllArgsConstructor
@Getter
public class RecipeRequestDto implements Serializable {

    private final String title;
    private final String yields;
    private final Integer serves;
    private final String description;
    private final Duration cookTime;
    private final Duration prepTime;
    private final Duration activeTime;
    private final Duration totalTime;
    private final List<Long> imageIds;
    private final List<RecipeIngredientListRequestDto> recipeIngredientLists;
    private final List<String> recipeNoteTexts;
    private final List<RecipeStepRequestDto> recipeSteps;
    private final List<String> recipeTags;
}
