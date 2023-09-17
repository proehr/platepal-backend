package com.pli.codes.platepal.cookbook.model.entity;

import io.hypersistence.utils.hibernate.type.interval.PostgreSQLIntervalType;
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
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

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
    @Type(PostgreSQLIntervalType.class)
    private Duration cookTime;

    @Column(name = "prep_time")
    @Type(PostgreSQLIntervalType.class)
    private Duration prepTime;

    @Column(name = "active_time")
    @Type(PostgreSQLIntervalType.class)
    private Duration activeTime;

    @Column(name = "total_time")
    @Type(PostgreSQLIntervalType.class)
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
    @JoinTable(
        name = "recipe_image",
        joinColumns = @JoinColumn(name = "recipe_id"),
        inverseJoinColumns = @JoinColumn(name = "image_id"),
        schema = "platepal_recipes"
    )
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<RecipeIngredientList> recipeIngredientLists = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<RecipeNote> recipeNotes = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<RecipeStep> recipeSteps = new ArrayList<>();

    @OneToMany(mappedBy = "recipe")
    private List<RecipeTag> recipeTags = new ArrayList<>();

}
