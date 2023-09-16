package com.pli.codes.platepal.accounts.model.repository;

import com.pli.codes.platepal.accounts.model.entity.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailAddress(String emailAddress);

    Optional<Account> findByPassword(String password);
}
