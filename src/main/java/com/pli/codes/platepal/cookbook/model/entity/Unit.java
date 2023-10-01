package com.pli.codes.platepal.cookbook.model.entity;

import com.pli.codes.platepal.cookbook.model.type.UnitType;
import io.hypersistence.utils.hibernate.type.basic.PostgreSQLEnumType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "unit", schema = "platepal_recipes")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unit_id_gen")
    @SequenceGenerator(
        name = "unit_id_gen",
        sequenceName = "unit_unit_id_seq",
        allocationSize = 1,
        schema = "platepal_recipes"
    )
    @Column(name = "unit_id", nullable = false)
    private Integer id;

    @Column(name = "unit_type", columnDefinition = "unit_type")
    @Enumerated(EnumType.STRING)
    @Type(PostgreSQLEnumType.class)
    private UnitType unitType;

    @Column(name = "fulltext", length = Integer.MAX_VALUE)
    private String fulltext;

    @Column(name = "abbreviation", length = Integer.MAX_VALUE)
    private String abbreviation;

}
