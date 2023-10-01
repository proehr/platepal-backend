package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.RecipeNote;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link RecipeNote}
 */
@AllArgsConstructor
@Getter
public class RecipeNoteDto {

    private final Long id;
    private final String noteText;
}
