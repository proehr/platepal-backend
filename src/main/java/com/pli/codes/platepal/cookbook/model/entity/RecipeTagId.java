package com.pli.codes.platepal.cookbook.model.entity;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
public class RecipeTagId {

    private String tagTitle;
    private Long recipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RecipeTagId entity = (RecipeTagId) o;
        return Objects.equals(this.tagTitle, entity.tagTitle) && Objects.equals(this.recipe, entity.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagTitle, recipe);
    }

}
