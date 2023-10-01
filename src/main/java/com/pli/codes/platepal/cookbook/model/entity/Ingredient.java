package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "ingredient", schema = "platepal_recipes")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_id_gen")
    @SequenceGenerator(
        name = "ingredient_id_gen",
        sequenceName = "ingredient_ingredient_id_seq",
        allocationSize = 1,
        schema = "platepal_recipes"
    )
    @Column(name = "ingredient_id", nullable = false)
    private Long id;

    @Column(name = "ingredient_name", length = Integer.MAX_VALUE)
    private String ingredientName;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "default_image_id")
    private Image defaultImage;

}
