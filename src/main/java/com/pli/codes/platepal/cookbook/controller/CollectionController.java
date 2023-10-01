package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.model.repository.AccountRepository;
import com.pli.codes.platepal.cookbook.model.dto.CollectionDtoSmall;
import com.pli.codes.platepal.cookbook.model.dto.CollectionRecipeEntryRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.CollectionRequestDto;
import com.pli.codes.platepal.cookbook.model.dto.CollectionResponseDto;
import com.pli.codes.platepal.cookbook.model.entity.Collection;
import com.pli.codes.platepal.cookbook.model.entity.CollectionRecipeEntry;
import com.pli.codes.platepal.cookbook.model.entity.Recipe;
import com.pli.codes.platepal.cookbook.model.mapper.CollectionMapper;
import com.pli.codes.platepal.cookbook.model.repository.CollectionRepository;
import com.pli.codes.platepal.cookbook.model.repository.RecipeRepository;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin
public class CollectionController {

    private final CollectionRepository repository;
    private final RecipeRepository recipeRepository;
    private final AccountRepository accountRepository;

    public CollectionController(
        CollectionRepository repository,
        RecipeRepository recipeRepository,
        AccountRepository accountRepository
    ) {
        this.repository = repository;
        this.recipeRepository = recipeRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<CollectionDtoSmall>> getCollectionsSmall(Principal principal) {
        Optional<Account> account = accountRepository.findByEmailAddress(principal.getName());
        List<CollectionDtoSmall> collections = repository.findByAccountsContains(account.orElseThrow());
        return ResponseEntity.ok(collections);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionResponseDto> getCollection(
        @PathVariable Long id,
        Principal principal
    ) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Optional<Collection> byId = repository.findById(id);
        if (byId.isPresent()) {
            Collection collection = byId.get();
            if (Boolean.TRUE.equals(collection.getIsPublic()) || collection.getAccounts().contains(account)) {
                return ResponseEntity.ok(CollectionMapper.INSTANCE.toDto(collection));
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<CollectionResponseDto> createCollection(
        @RequestBody CollectionRequestDto requestDto,
        Principal principal
    ) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Collection collection = CollectionMapper.INSTANCE.toEntity(requestDto);
        collection.setCreatedBy(account.getAccountId());
        collection.getAccounts().add(account);
        if (hasParentPermissions(requestDto, account)) {
            repository.save(collection);
            return ResponseEntity.ok(CollectionMapper.INSTANCE.toDto(collection));
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CollectionResponseDto> updateCollection(
        @PathVariable Long id,
        @RequestBody CollectionRequestDto requestDto,
        Principal principal
    ) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Optional<Collection> byId = repository.findById(id);
        if (byId.isPresent()) {
            Collection collection = byId.get();
            if (
                Objects.equals(collection.getCreatedBy(), account.getAccountId())
                    && hasParentPermissions(requestDto, account)
            ) {
                CollectionMapper.INSTANCE.updateEntityFromDto(requestDto, collection);
                repository.save(collection);
                return ResponseEntity.ok(CollectionMapper.INSTANCE.toDto(collection));
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCollection(@PathVariable Long id, Principal principal) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Optional<Collection> byId = repository.findById(id);
        if (byId.isPresent()) {
            Collection collection = byId.get();
            if (Objects.equals(collection.getCreatedBy(), account.getAccountId())) {
                repository.delete(collection);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CollectionResponseDto> setRecipes(
        @PathVariable Long id,
        @RequestBody Iterable<CollectionRecipeEntryRequestDto> recipeEntryDtos,
        Principal principal
    ) {
        Account account = accountRepository.findByEmailAddress(principal.getName()).orElseThrow();
        Optional<Collection> byId = repository.findById(id);
        if (byId.isPresent()) {
            Collection collection = byId.get();
            if (Objects.equals(collection.getCreatedBy(), account.getAccountId())) {
                collection.getRecipeEntries().clear();
                for (CollectionRecipeEntryRequestDto dto : recipeEntryDtos) {
                    addRecipeEntry(dto.getRecipeId(), dto.getPosition(), collection);
                }
                repository.save(collection);
                return ResponseEntity.ok(CollectionMapper.INSTANCE.toDto(collection));
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void addRecipeEntry(Long recipeId, int position, Collection collection) {
        Optional<Recipe> recipeRepositoryById = recipeRepository.findById(recipeId);
        if (recipeRepositoryById.isPresent()) {
            Recipe recipe = recipeRepositoryById.get();
            CollectionRecipeEntry collectionRecipeEntry = new CollectionRecipeEntry();
            collectionRecipeEntry.setCollection(collection);
            collectionRecipeEntry.setRecipe(recipe);
            collectionRecipeEntry.setPosition(position);
            collection.getRecipeEntries().add(collectionRecipeEntry);
        }
    }

    private boolean hasParentPermissions(CollectionRequestDto requestDto, Account account) {
        return requestDto.getParent() == null
            || Objects.equals(repository.findById(requestDto.getParent().getId()).orElseThrow().getCreatedBy(),
            account.getAccountId());

    }

}
