package com.pli.codes.platepal.cookbook.controller;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.model.repository.AccountRepository;
import com.pli.codes.platepal.cookbook.model.entity.Collection;
import com.pli.codes.platepal.cookbook.model.repository.CollectionRepository;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collection")
@CrossOrigin
public class CollectionController {

    private final CollectionRepository repository;
    private final AccountRepository accountRepository;

    public CollectionController(CollectionRepository repository, AccountRepository accountRepository) {
        this.repository = repository;
        this.accountRepository = accountRepository;
    }

    @GetMapping
    public ResponseEntity<List<Collection>> getCollectionsSmall(Principal principal) {
        Optional<Account> account = accountRepository.findByEmailAddress(principal.getName());
        List<Collection> collections = repository.findByAccountsContains(account.orElseThrow());
        return ResponseEntity.ok(collections);
    }
}
