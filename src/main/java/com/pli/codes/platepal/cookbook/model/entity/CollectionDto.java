package com.pli.codes.platepal.cookbook.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link Collection}
 */
@AllArgsConstructor
@Getter
public class CollectionDto {

    private final String title;
    private final Integer position;
}
