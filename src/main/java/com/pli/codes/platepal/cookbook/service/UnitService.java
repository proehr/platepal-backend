package com.pli.codes.platepal.cookbook.service;

import com.pli.codes.platepal.cookbook.model.entity.Unit;
import com.pli.codes.platepal.cookbook.model.repository.UnitRepository;
import com.pli.codes.platepal.cookbook.model.type.UnitType;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UnitService {

    private final UnitRepository repository;

    public UnitService(UnitRepository repository) {
        this.repository = repository;
    }

    public Unit createUnit(UnitType unitType, String fullText, String abbreviation) {
        Optional<Unit> unitOptional = repository.findByFulltext(fullText);
        if (unitOptional.isPresent()) {
            return unitOptional.get();
        }
        Unit unit = new Unit();
        unit.setUnitType(unitType);
        unit.setFulltext(fullText);
        unit.setAbbreviation(abbreviation);
        repository.save(unit);
        return unit;
    }
}
