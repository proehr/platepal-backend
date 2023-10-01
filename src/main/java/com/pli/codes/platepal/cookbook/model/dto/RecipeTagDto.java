package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.RecipeTag;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link RecipeTag}
 */
@AllArgsConstructor
@Getter
public class RecipeTagDto {

    private final String idTagTitle;
}
