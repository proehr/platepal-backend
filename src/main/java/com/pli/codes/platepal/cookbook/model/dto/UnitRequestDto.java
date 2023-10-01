package com.pli.codes.platepal.cookbook.model.dto;

import com.pli.codes.platepal.cookbook.model.type.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.cookbook.model.entity.Unit}
 */
@Getter
@AllArgsConstructor
public class UnitRequestDto {

    private final UnitType unitType;
    private final String fulltext;
    private final String abbreviation;
}
