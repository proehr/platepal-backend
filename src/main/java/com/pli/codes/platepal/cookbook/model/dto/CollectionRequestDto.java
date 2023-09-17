package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.Collection;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link Collection}
 */
@AllArgsConstructor
@Getter
public class CollectionRequestDto {

    private final String title;
    private final Boolean isPublic;
    private final Integer position;
    private final ParentCollectionDto parent;

    @AllArgsConstructor
    @Getter
    public static class ParentCollectionDto {

        Long id;
    }
}
