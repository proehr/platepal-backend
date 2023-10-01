package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.cookbook.model.entity.RecipeTag;
import com.pli.codes.platepal.cookbook.model.entity.RecipeTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeTagRepository extends JpaRepository<RecipeTag, RecipeTagId> {

}
