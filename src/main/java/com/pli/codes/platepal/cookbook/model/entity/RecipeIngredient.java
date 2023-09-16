package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "recipe_ingredient", schema = "platepal_recipes")
public class RecipeIngredient {

    @SequenceGenerator(name = "recipe_ingredient_id_gen", sequenceName = "image_image_id_seq", allocationSize = 1)
    @EmbeddedId
    private RecipeIngredientId id;

    @MapsId("ingredientId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient;

    @MapsId("ingredientListId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ingredient_list_id", nullable = false)
    private RecipeIngredientList ingredientList;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @Column(name = "process", length = Integer.MAX_VALUE)
    private String process;

    @Column(name = "color", length = Integer.MAX_VALUE)
    private String color;

    @Column(name = "part", length = Integer.MAX_VALUE)
    private String part;

    @Column(name = "taste", length = Integer.MAX_VALUE)
    private String taste;

    @Column(name = "purpose", length = Integer.MAX_VALUE)
    private String purpose;

}
