package com.pli.codes.platepal.cookbook.model.mapper;

import com.pli.codes.platepal.cookbook.model.dto.IngredientDto;
import com.pli.codes.platepal.cookbook.model.entity.Ingredient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IngredientMapper {

    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);

    IngredientDto toDto(Ingredient ingredient);
}
