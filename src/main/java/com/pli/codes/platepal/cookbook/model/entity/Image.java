package com.pli.codes.platepal.cookbook.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "image", schema = "platepal_recipes")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "image_id_gen")
    @SequenceGenerator(name = "image_id_gen", sequenceName = "image_image_id_seq", allocationSize = 1, schema = "platepal_recipes")
    @Column(name = "image_id", nullable = false)
    private Long id;

    @Column(name = "path", length = Integer.MAX_VALUE)
    private String path;

}
