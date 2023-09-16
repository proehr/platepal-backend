package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "recipe_ingredient_list", schema = "platepal_recipes")
public class RecipeIngredientList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_ingredient_list_id_gen")
    @SequenceGenerator(name = "recipe_ingredient_list_id_gen", sequenceName = "recipe_ingredient_list_list_id_seq",
            allocationSize = 1)
    @Column(name = "list_id", nullable = false)
    private Long id;

    @Column(name = "list_title", length = Integer.MAX_VALUE)
    private String listTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @OneToMany(mappedBy = "ingredientList")
    private List<RecipeIngredient> recipeIngredients = new ArrayList<>();

}
