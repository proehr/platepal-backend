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
@Table(name = "recipe_step", schema = "platepal_recipes")
public class RecipeStep {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_step_id_gen")
    @SequenceGenerator(
        name = "recipe_step_id_gen",
        sequenceName = "recipe_step_step_id_seq",
        allocationSize = 1,
        schema = "platepal_recipes"
    )
    @Column(name = "step_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recipe_id")
    @Setter
    private Recipe recipe;

    @Column(name = "step_number")
    private Integer stepNumber;

    @Column(name = "step_text", length = Integer.MAX_VALUE)
    private String stepText;

    @Column(name = "step_header", length = Integer.MAX_VALUE)
    private String stepHeader;

}
