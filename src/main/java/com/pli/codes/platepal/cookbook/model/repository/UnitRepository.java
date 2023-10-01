package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.cookbook.model.entity.Unit;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitRepository extends JpaRepository<Unit, Integer> {

    Optional<Unit> findByFulltext(String fulltext);
}
