package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

@NoArgsConstructor
@Getter
@Embeddable
public class RecipeIngredientId implements Serializable {

    private static final long serialVersionUID = -7771896747101219505L;
    @Column(name = "ingredient_id", nullable = false)
    private Long ingredientId;

    @Column(name = "ingredient_list_id", nullable = false)
    private Long ingredientListId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RecipeIngredientId entity = (RecipeIngredientId) o;
        return Objects.equals(this.ingredientId, entity.ingredientId) &&
                Objects.equals(this.ingredientListId, entity.ingredientListId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredientListId);
    }

}
