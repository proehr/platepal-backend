package com.pli.codes.platepal.cookbook.model.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientId implements Serializable {

    private static final long serialVersionUID = -7771896747101219505L;
    private Long ingredient;
    private Long ingredientList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecipeIngredientId that = (RecipeIngredientId) o;
        return Objects.equals(ingredient, that.ingredient) && Objects.equals(ingredientList,
            that.ingredientList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, ingredientList);
    }
}
