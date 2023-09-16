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
public class RecipeTagId implements Serializable {

    private static final long serialVersionUID = -8162430754199063698L;
    @Column(name = "tag_title", nullable = false, length = Integer.MAX_VALUE)
    private String tagTitle;

    @Column(name = "recipe_id", nullable = false)
    private Long recipeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        RecipeTagId entity = (RecipeTagId) o;
        return Objects.equals(this.tagTitle, entity.tagTitle) && Objects.equals(this.recipeId, entity.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagTitle, recipeId);
    }

}
