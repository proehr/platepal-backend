package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class CollectionRecipeEntryId implements Serializable {

    private static final long serialVersionUID = -7518052542229450723L;
    @Column(name = "collection_id", nullable = false)
    private Long collectionId;

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
        CollectionRecipeEntryId entity = (CollectionRecipeEntryId) o;
        return Objects.equals(this.collectionId, entity.collectionId) && Objects.equals(this.recipeId, entity.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collectionId, recipeId);
    }

}
