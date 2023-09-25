package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.RecipeStep}
 */
@AllArgsConstructor
@Getter
public class RecipeStepRequestDto implements Serializable {

    private final Integer stepNumber;
    private final String stepText;
    private final String stepHeader;
}
