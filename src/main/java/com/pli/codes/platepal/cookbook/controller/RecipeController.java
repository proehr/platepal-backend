package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.model.repository.AccountRepository;
import com.pli.codes.platepal.cookbook.model.dto.RecipeDtoSmall;
import com.pli.codes.platepal.cookbook.model.dto.RecipeRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.RecipeResponseDto;
import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import com.pli.codes.platepal.cookbook.model.mapper.RecipeMapper;
import com.pli.codes.platepal.cookbook.model.repository.IngredientRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeIngredientListRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeIngredientRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeNoteRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeStepRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeTagRepository;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeIngredientListRepository recipeIngredientListRepository;
    private final RecipeNoteRepository recipeNoteRepository;
    private final RecipeStepRepository recipeStepRepository;
    private final RecipeTagRepository recipeTagRepository;
    private final AccountRepository accountRepository;

    public RecipeController(
        RecipeRepository repository,
        IngredientRepository ingredientRepository,
        RecipeIngredientRepository recipeIngredientRepository,
        RecipeIngredientListRepository recipeIngredientListRepository, RecipeNoteRepository recipeNoteRepository,
        RecipeStepRepository recipeStepRepository, RecipeTagRepository recipeTagRepository,
        AccountRepository accountRepository
    ) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeIngredientListRepository = recipeIngredientListRepository;
        this.recipeNoteRepository = recipeNoteRepository;
        this.recipeStepRepository = recipeStepRepository;
        this.recipeTagRepository = recipeTagRepository;
        this.accountRepository = accountRepository;
    }

    // TODO: replace with recipes in collections
    @GetMapping
    public ResponseEntity<List<RecipeDtoSmall>> getRecipesSmall(Principal principal) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        List<Recipe> recipes = repository.findByCreatedBy(account.getAccountId(), PageRequest.of(0, 5));
        List<RecipeDtoSmall> dtos = recipes.stream().map(RecipeMapper.INSTANCE::toDtoSmall)
            .collect(Collectors.toList());
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
        recipeIngredientListRepository.saveAll(recipe.getRecipeIngredientLists());
        recipe.getRecipeIngredientLists().forEach(
            recipeIngredientList -> recipeIngredientRepository.saveAll(recipeIngredientList.getRecipeIngredients())
        );
        recipeNoteRepository.saveAll(recipe.getRecipeNotes());
        recipeStepRepository.saveAll(recipe.getRecipeSteps());
        recipeTagRepository.saveAll(recipe.getRecipeTags());

        return ResponseEntity.ok(RecipeMapper.INSTANCE.toDto(recipe));
    }
}
