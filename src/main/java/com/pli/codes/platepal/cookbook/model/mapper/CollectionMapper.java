package com.pli.codes.platepal.cookbook.model.mapper;


import com.pli.codes.platepal.cookbook.model.dto.CollectionRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.CollectionResponseDto;
import com.pli.codes.platepal.cookbook.model.dto.RecipeDtoSmall;
import com.pli.codes.platepal.cookbook.model.entity.Collection;
import com.pli.codes.platepal.cookbook.model.entity.CollectionRecipeEntry;
import com.pli.codes.platepal.cookbook.model.entity.CollectionTag;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface CollectionMapper {

    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    Collection toEntity(CollectionRequestDto collectionRequestDto);

    @AfterMapping
    default void linkCollectionTags(@MappingTarget Collection collection) {
        collection.getTags().forEach(tag -> tag.setCollection(collection));
    }

    default CollectionTag collectionTagTitleToCollectionTagEntity(String collectionTagTitle) {
        CollectionTag collectionTag = new CollectionTag();
        collectionTag.setTagTitle(collectionTagTitle);
        return collectionTag;
    }

    void updateEntityFromDto(CollectionRequestDto collectionRequestDto, @MappingTarget Collection entity);

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(
        target = "recipeEntryPositions",
        expression = "java(recipeEntriesToRecipeEntryPositions(collection.getRecipeEntries()))"
    )
    @Mapping(
        target = "recipeEntryRecipes",
        expression = "java(recipeEntriesToRecipeEntryRecipes(collection.getRecipeEntries()))"
    )
    @Mapping(target = "tags", expression = "java(tagsToTagTitles(collection.getTags()))")
    CollectionResponseDto toDto(Collection collection);

    default List<Integer> recipeEntriesToRecipeEntryPositions(List<CollectionRecipeEntry> recipeEntries) {
        return recipeEntries.stream().map(CollectionRecipeEntry::getPosition).toList();
    }

    default List<RecipeDtoSmall> recipeEntriesToRecipeEntryRecipes(List<CollectionRecipeEntry> recipeEntries) {
        return recipeEntries
            .stream()
            .map(CollectionRecipeEntry::getRecipe)
            .map(RecipeMapper.INSTANCE::toDtoSmall)
            .toList();
    }

    default List<String> tagsToTagTitles(java.util.Collection<CollectionTag> tags) {
        return tags.stream().map(CollectionTag::getTagTitle).toList();
    }


}
