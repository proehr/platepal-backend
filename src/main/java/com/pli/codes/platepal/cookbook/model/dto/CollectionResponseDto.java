package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Collection}
 */
@AllArgsConstructor
@Getter
public class CollectionResponseDto {

    private final Long id;
    private final Long createdBy;
    private final String title;
    private final Instant createdAt;
    private final Boolean isPublic;
    private final Instant updatedAt;
    private final Long parentId;
    private final Integer position;
    private final List<AccountDto> accounts;
    private final List<ChildCollectionResponseDto> childCollections;
    private final List<Recipe> recipeEntryRecipes;
    private final List<Integer> recipeEntryPositions;
    private final List<CollectionTagResponseDto> tags;

    /**
     * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Collection}
     */
    @AllArgsConstructor
    @Getter
    public static class ChildCollectionResponseDto {

        private final Long id;
        private final Long createdBy;
        private final String title;
        private final Instant createdAt;
        private final Boolean isPublic;
        private final Instant updatedAt;
        private final Integer position;
    }
}
