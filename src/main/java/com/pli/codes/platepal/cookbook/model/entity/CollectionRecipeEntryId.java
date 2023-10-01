package com.pli.codes.platepal.cookbook.model.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollectionRecipeEntryId implements Serializable {

    private static final long serialVersionUID = -7518052542229450723L;

    private Long collection;
    private Long recipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CollectionRecipeEntryId entity = (CollectionRecipeEntryId) o;
        return Objects.equals(this.collection, entity.collection) && Objects.equals(this.recipe, entity.recipe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection, recipe);
    }

}
