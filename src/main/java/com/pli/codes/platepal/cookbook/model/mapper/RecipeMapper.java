package com.pli.codes.platepal.cookbook.model.mapper;

import com.pli.codes.platepal.cookbook.model.dto.RecipeDtoSmall;
import com.pli.codes.platepal.cookbook.model.entity.Image;
import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface RecipeMapper {

    RecipeMapper INSTANCE = Mappers.getMapper(RecipeMapper.class);

    Recipe toEntity(RecipeDtoSmall recipeDtoSmall);

    @Mapping(target = "imagePaths", expression = "java(imagesToImagePaths(recipe.getImages()))")
    RecipeDtoSmall toDto(
        Recipe recipe);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recipe partialUpdate(
        RecipeDtoSmall recipeDtoSmall, @MappingTarget Recipe recipe);

    default List<String> imagesToImagePaths(List<Image> images) {
        return images.stream().map(Image::getPath).collect(Collectors.toList());
    }
}
