package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.entity.Unit;
import com.pli.codes.platepal.cookbook.model.type.UnitType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link Unit}
 */
@AllArgsConstructor
@Getter
public class UnitDto implements Serializable {

    private final Integer id;
    private final UnitType unitType;
    private final String fulltext;
    private final String abbreviation;
}
