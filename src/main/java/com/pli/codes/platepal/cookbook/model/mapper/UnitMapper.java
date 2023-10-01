package com.pli.codes.platepal.cookbook.model.mapper;

import com.pli.codes.platepal.cookbook.model.dto.UnitDto;
import com.pli.codes.platepal.cookbook.model.entity.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = ComponentModel.SPRING)
public interface UnitMapper {

    UnitMapper INSTANCE = Mappers.getMapper(UnitMapper.class);

    UnitDto toDto(Unit unit);
}
