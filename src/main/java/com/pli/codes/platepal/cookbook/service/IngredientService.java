package com.pli.codes.platepal.cookbook.service;

import com.pli.codes.platepal.cookbook.model.entity.Ingredient;
import com.pli.codes.platepal.cookbook.model.repository.IngredientRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {


    private final IngredientRepository repository;

    public IngredientService(IngredientRepository repository) {
        this.repository = repository;
    }

    public Ingredient createIngredient(String ingredientName) {
        Optional<Ingredient> ingredientOptional = repository.findByIngredientName(ingredientName);
        if (ingredientOptional.isPresent()) {
            return ingredientOptional.get();
        }
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName(ingredientName);
        repository.save(ingredient);
        return ingredient;
    }
}
