package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.cookbook.model.entity.RecipeNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeNoteRepository extends JpaRepository<RecipeNote, Long> {

}
