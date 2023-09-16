package com.pli.codes.platepal.cookbook.model.entity;

import com.pli.codes.platepal.accounts.model.entity.Account;
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
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "collection", schema = "platepal_collections")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "collection_id_gen")
    @SequenceGenerator(name = "collection_id_gen", sequenceName = "collection_collection_id_seq", allocationSize = 1)
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
    private Boolean publicField;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Collection parent;

    @Column(name = "\"position\"")
    private Integer position;

    @ManyToMany
    @JoinTable(name = "account_collection", joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns =
    @JoinColumn(name = "account_id"))
    private Set<Account> accounts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "parent")
    private Set<Collection> childCollections = new LinkedHashSet<>();

    @OneToMany(mappedBy = "collection")
    private Set<CollectionRecipeEntry> recipeEntries = new LinkedHashSet<>();

    @OneToMany(mappedBy = "collection")
    private Set<CollectionTag> tags = new LinkedHashSet<>();

}
