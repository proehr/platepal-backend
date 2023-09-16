package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "recipe", schema = "platepal_recipes")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_id_gen")
    @SequenceGenerator(name = "recipe_id_gen", sequenceName = "recipe_recipe_id_seq", allocationSize = 1)
    @Column(name = "recipe_id", nullable = false)
    private Long id;

    @Column(name = "title", length = Integer.MAX_VALUE)
    private String title;

    @Column(name = "yields", length = Integer.MAX_VALUE)
    private String yields;

    @Column(name = "serves")
    private Integer serves;

    @Column(name = "description", length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "cook_time")
    private Duration cookTime;

    @Column(name = "prep_time")
    private Duration prepTime;

    @Column(name = "active_time")
    private Duration activeTime;

    @Column(name = "total_time")
    private Duration totalTime;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Comment("account_id")
    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToMany
    @JoinTable(name = "recipe_image", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns =
    @JoinColumn(name = "image_id"))
    private Set<Image> images = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeIngredientList> recipeIngredientLists = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeNote> recipeNotes = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeStep> recipeSteps = new LinkedHashSet<>();

    @OneToMany(mappedBy = "recipe")
    private Set<RecipeTag> recipeTags = new LinkedHashSet<>();

}
