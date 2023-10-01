package com.pli.codes.platepal.cookbook.model.entity;

import com.pli.codes.platepal.accounts.model.entity.Account;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "collection", schema = "platepal_collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_id_gen")
    @SequenceGenerator(
        name = "collection_id_gen",
        sequenceName = "collection_collection_id_seq",
        allocationSize = 1,
        schema = "platepal_collections"
    )
    @Column(name = "collection_id", nullable = false)
    private Long id;

    @Comment("account_id")
    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "public")
    private Boolean isPublic;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Collection parent;

    @Column(name = "\"position\"")
    private Integer position;

    @ManyToMany
    @JoinTable(schema = "platepal_collections", name = "account_collection",
        joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns = @JoinColumn(name = "account_id"))
    private List<Account> accounts = new ArrayList<>();

    @OneToMany(mappedBy = "parent")
    private List<Collection> childCollections = new ArrayList<>();

    @OneToMany(mappedBy = "collection", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionRecipeEntry> recipeEntries = new ArrayList<>();

    @OneToMany(mappedBy = "collection", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CollectionTag> tags = new ArrayList<>();

}
