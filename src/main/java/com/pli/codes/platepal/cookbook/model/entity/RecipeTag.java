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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "recipe_tag", schema = "platepal_recipes")
public class RecipeTag {

    @SequenceGenerator(name = "recipe_tag_id_gen", sequenceName = "image_image_id_seq", allocationSize = 1)
    @EmbeddedId
    private RecipeTagId id;

    @MapsId("recipeId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

}
