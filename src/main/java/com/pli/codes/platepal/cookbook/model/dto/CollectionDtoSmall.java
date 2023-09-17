package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link Collection}
 */
@AllArgsConstructor
@Getter
public class CollectionDtoSmall {

    private final String title;
    private final Integer position;
}
