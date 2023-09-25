package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.RecipeTag;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link RecipeTag}
 */
@AllArgsConstructor
@Getter
public class RecipeTagDto implements Serializable {

    private final String idTagTitle;
}
