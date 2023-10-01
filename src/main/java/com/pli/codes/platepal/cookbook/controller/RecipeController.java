package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.model.repository.AccountRepository;
import com.pli.codes.platepal.cookbook.model.dto.RecipeDtoSmall;
import com.pli.codes.platepal.cookbook.model.dto.RecipeRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.RecipeResponseDto;
import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import com.pli.codes.platepal.cookbook.model.mapper.RecipeMapper;
import com.pli.codes.platepal.cookbook.model.repository.IngredientRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeRepository;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recipe")
@CrossOrigin
public class RecipeController {

    private final RecipeRepository repository;
    private final IngredientRepository ingredientRepository;
    private final AccountRepository accountRepository;

    public RecipeController(
        RecipeRepository repository,
        IngredientRepository ingredientRepository,
        AccountRepository accountRepository
    ) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
        this.accountRepository = accountRepository;
    }

    // TODO: replace with recipes in collections
    @GetMapping
    public ResponseEntity<List<RecipeDtoSmall>> getRecipesSmall(Principal principal) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        List<Recipe> recipes = repository.findByCreatedBy(account.getAccountId(), PageRequest.of(0, 5));
        List<RecipeDtoSmall> dtos = recipes.stream().map(RecipeMapper.INSTANCE::toDtoSmall).toList();
        return ResponseEntity.ok(dtos);
    }

    // TODO: add isPublic check and 401 response
    @GetMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipe(@PathVariable Long id) {
        Optional<Recipe> byId = repository.findById(id);
        if (byId.isPresent()) {
            Recipe recipe = byId.get();
            return ResponseEntity.ok(RecipeMapper.INSTANCE.toDto(recipe));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<RecipeResponseDto> createRecipe(
        @RequestBody RecipeRequestDto requestDto,
        Principal principal
    ) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Recipe recipe = RecipeMapper.INSTANCE.toEntity(requestDto, ingredientRepository);
        recipe.setCreatedBy(account.getAccountId());
        repository.save(recipe);
        return ResponseEntity.ok(RecipeMapper.INSTANCE.toDto(recipe));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecipeResponseDto> getRecipe(
        @PathVariable Long id,
        @RequestBody RecipeRequestDto requestDto,
        Principal principal
    ) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Optional<Recipe> byId = repository.findById(id);
        if (byId.isPresent()) {
            Recipe recipe = byId.get();
            if (Objects.equals(recipe.getCreatedBy(), account.getAccountId())) {
                RecipeMapper.INSTANCE.partialUpdate(requestDto, recipe, ingredientRepository);
                repository.save(recipe);
                return ResponseEntity.ok(RecipeMapper.INSTANCE.toDto(recipe));
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id, Principal principal) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Optional<Recipe> byId = repository.findById(id);
        if (byId.isPresent()) {
            Recipe recipe = byId.get();
            if (Objects.equals(recipe.getCreatedBy(), account.getAccountId())) {
                repository.delete(recipe);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
