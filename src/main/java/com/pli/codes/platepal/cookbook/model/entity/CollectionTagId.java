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
public class CollectionTagId implements Serializable {

    private static final long serialVersionUID = 964089307616701952L;
    @Column(name = "collection_id", nullable = false)
    private Long collectionId;

    @Column(name = "tag_title", nullable = false, length = Integer.MAX_VALUE)
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
        return Objects.equals(this.tagTitle, entity.tagTitle) && Objects.equals(this.collectionId, entity.collectionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagTitle, collectionId);
    }

}
