package com.pli.codes.platepal.model.repository;

import com.pli.codes.platepal.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByEmailAddress(String emailAddress);
    Optional<Account> findByPassword(String password);
}
