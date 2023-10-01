package com.pli.codes.platepal.cookbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.CollectionRecipeEntry}
 */
@AllArgsConstructor
@Getter
public class CollectionRecipeEntryRequestDto {

    private final Long recipeId;
    private final Integer position;
}
