package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.cookbook.model.entity.RecipeIngredient;
import com.pli.codes.platepal.cookbook.model.entity.RecipeIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId> {

}
