package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.cookbook.model.entity.Ingredient;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByIngredientName(String ingredientName);

}
