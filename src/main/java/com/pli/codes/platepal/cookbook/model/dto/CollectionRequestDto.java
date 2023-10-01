package com.pli.codes.platepal.cookbook.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Collection}
 */
@AllArgsConstructor
@Getter
public class CollectionRequestDto {

    private final String title;
    private final Boolean isPublic;
    private final Integer position;
    private final ParentCollectionDto parent;
    private final List<String> tags;

    @AllArgsConstructor
    @Getter
    public static class ParentCollectionDto {

        private final Long id;
    }
}
