package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link Recipe}
 */
@AllArgsConstructor
@Getter
public class RecipeResponseDto {

    private final Long id;
    private final String title;
    private final String yields;
    private final Integer serves;
    private final String description;
    private final Duration cookTime;
    private final Duration prepTime;
    private final Duration activeTime;
    private final Duration totalTime;
    private final Instant createdAt;
    private final Long createdBy;
    private final Instant updatedAt;
    private final List<String> imagePaths;
    private final List<RecipeIngredientListDto> recipeIngredientLists;
    private final List<RecipeNoteDto> recipeNotes;
    private final List<RecipeStepDto> recipeSteps;
    private final List<String> recipeTags;
}
