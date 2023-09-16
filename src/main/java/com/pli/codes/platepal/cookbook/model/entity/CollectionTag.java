package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "collection_tag", schema = "platepal_collections")
public class CollectionTag {

    @SequenceGenerator(name = "collection_tag_id_gen", sequenceName = "collection_collection_id_seq", allocationSize
            = 1)
    @EmbeddedId
    private CollectionTagId id;

    @MapsId("collectionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

}
