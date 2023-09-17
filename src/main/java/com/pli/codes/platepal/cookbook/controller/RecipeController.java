package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.model.repository.AccountRepository;
import com.pli.codes.platepal.cookbook.model.dto.RecipeDtoSmall;
import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import com.pli.codes.platepal.cookbook.model.mapper.RecipeMapper;
import com.pli.codes.platepal.cookbook.model.repository.RecipeRepository;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin
public class RecipeController {

    private final RecipeRepository repository;
    private final AccountRepository accountRepository;

    public RecipeController(RecipeRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    // TODO: replace with recipes in collections
    @GetMapping
    public ResponseEntity<List<RecipeDtoSmall>> getRecipesSmall(Principal principal) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        List<Recipe> recipes = repository.findByCreatedBy(account.getAccountId(), PageRequest.of(0, 5));
        List<RecipeDtoSmall> dtos = recipes.stream().map(RecipeMapper.INSTANCE::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
