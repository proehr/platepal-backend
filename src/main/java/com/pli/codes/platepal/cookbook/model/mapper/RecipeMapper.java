package com.pli.codes.platepal.cookbook.model.mapper;

import com.pli.codes.platepal.cookbook.model.dto.RecipeDtoSmall;
import com.pli.codes.platepal.cookbook.model.dto.RecipeIngredientRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.RecipeRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.RecipeResponseDto;
import com.pli.codes.platepal.cookbook.model.entity.Image;
import com.pli.codes.platepal.cookbook.model.entity.Ingredient;
import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import com.pli.codes.platepal.cookbook.model.entity.RecipeIngredient;
import com.pli.codes.platepal.cookbook.model.entity.RecipeIngredientList;
import com.pli.codes.platepal.cookbook.model.entity.RecipeNote;
import com.pli.codes.platepal.cookbook.model.entity.RecipeTag;
import com.pli.codes.platepal.cookbook.model.repository.IngredientRepository;
import java.util.Collection;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
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

    @Mapping(target = "imagePaths", expression = "java(imagesToImagePaths(recipe.getImages()))")
    RecipeDtoSmall toDtoSmall(
        Recipe recipe);

    default List<String> imagesToImagePaths(Collection<Image> images) {
        return images.stream().map(Image::getPath).toList();
    }

    @Mapping(target = "imagePaths", expression = "java(imagesToImagePaths(recipe.getImages()))")
    @Mapping(target = "recipeTags", expression = "java(recipeTagsToTagTitles(recipe.getRecipeTags()))")
    RecipeResponseDto toDto(Recipe recipe);

    default List<String> recipeTagsToTagTitles(Collection<RecipeTag> tags) {
        return tags.stream().map(RecipeTag::getTagTitle).toList();
    }

    @Mapping(target = "recipeNotes", source = "recipeNoteTexts")
    Recipe toEntity(RecipeRequestDto recipeRequestDto, @Context IngredientRepository ingredientRepository);

    @Mapping(target = "ingredient", source = "ingredientId")
    RecipeIngredient toRecipeIngredientEntity(RecipeIngredientRequestDto recipeIngredient,
        @Context IngredientRepository ingredientRepository);

    @AfterMapping
    default void linkRecipeIngredientLists(@MappingTarget Recipe recipe) {
        recipe.getRecipeIngredientLists().forEach(recipeIngredientList -> recipeIngredientList.setRecipe(recipe));
    }

    @AfterMapping
    default void linkRecipeIngredients(@MappingTarget RecipeIngredientList list) {
        list.getRecipeIngredients().forEach(recipeIngredient -> recipeIngredient.setIngredientList(list));
    }

    @AfterMapping
    default void linkRecipeSteps(@MappingTarget Recipe recipe) {
        recipe.getRecipeSteps().forEach(recipeStep -> recipeStep.setRecipe(recipe));
    }

    @AfterMapping
    default void linkRecipeNotes(@MappingTarget Recipe recipe) {
        recipe.getRecipeNotes().forEach(recipeNote -> recipeNote.setRecipe(recipe));
    }


    @AfterMapping
    default void linkRecipeTags(@MappingTarget Recipe recipe) {
        recipe.getRecipeTags().forEach(recipeTag -> recipeTag.setRecipe(recipe));
    }

    default Ingredient toIngredientEntity(Long ingredientId, @Context IngredientRepository ingredientRepository) {
        return ingredientRepository.findById(ingredientId).orElseThrow();
    }

    default RecipeNote noteTextToRecipeNoteEntity(String recipeNoteText) {
        RecipeNote recipeNote = new RecipeNote();
        recipeNote.setNoteText(recipeNoteText);
        return recipeNote;
    }

    default RecipeTag recipeTagTitleToRecipeTagEntity(String recipeTagTitle) {
        RecipeTag recipeTag = new RecipeTag();
        recipeTag.setTagTitle(recipeTagTitle);
        return recipeTag;
    }

    @Mapping(target = "recipeNotes", source = "recipeNoteTexts")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Recipe partialUpdate(RecipeRequestDto recipeRequestDto, @MappingTarget Recipe recipe,
        @Context IngredientRepository ingredientRepository);


}
