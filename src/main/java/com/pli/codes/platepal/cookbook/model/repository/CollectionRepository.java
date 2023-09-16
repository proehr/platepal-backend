package com.pli.codes.platepal.cookbook.model.repository;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.cookbook.model.entity.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

     List<Collection> findByAccountsContains(Account account);
}
