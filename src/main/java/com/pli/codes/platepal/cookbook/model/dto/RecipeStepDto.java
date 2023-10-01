package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.RecipeStep;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link RecipeStep}
 */
@AllArgsConstructor
@Getter
public class RecipeStepDto {

    private final Long id;
    private final Integer stepNumber;
    private final String stepText;
    private final String stepHeader;
}
