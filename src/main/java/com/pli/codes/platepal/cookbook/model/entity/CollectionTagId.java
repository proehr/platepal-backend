package com.pli.codes.platepal.cookbook.model.entity;

import java.io.Serializable;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

@Getter
@Setter
public class CollectionTagId implements Serializable {

    private static final long serialVersionUID = 964089307616701952L;

    private Long collection;
    private String tagTitle;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CollectionTagId entity = (CollectionTagId) o;
        return Objects.equals(this.tagTitle, entity.tagTitle) && Objects.equals(this.collection, entity.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagTitle, collection);
    }

}
