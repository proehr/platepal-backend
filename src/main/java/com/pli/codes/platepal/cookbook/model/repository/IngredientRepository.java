package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.cookbook.model.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
