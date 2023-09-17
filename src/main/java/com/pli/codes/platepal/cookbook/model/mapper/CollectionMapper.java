package com.pli.codes.platepal.cookbook.model.mapper;


import com.pli.codes.platepal.cookbook.model.dto.CollectionRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.CollectionResponseDto;
import com.pli.codes.platepal.cookbook.model.entity.Collection;
import com.pli.codes.platepal.cookbook.model.entity.CollectionRecipeEntry;
import java.util.List;
import java.util.stream.Collectors;
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

    void updateEntityFromDto(CollectionRequestDto collectionRequestDto, @MappingTarget Collection entity);

    @Mapping(source = "parent.id", target = "parentId")
    @Mapping(target = "recipeEntryPositions", expression = "java(recipeEntriesToRecipeEntryPositions(collection.getRecipeEntries()))")
    CollectionResponseDto toDto(Collection collection);

    default List<Integer> recipeEntriesToRecipeEntryPositions(List<CollectionRecipeEntry> recipeEntries) {
        return recipeEntries.stream().map(CollectionRecipeEntry::getPosition).collect(Collectors.toList());
    }


}
