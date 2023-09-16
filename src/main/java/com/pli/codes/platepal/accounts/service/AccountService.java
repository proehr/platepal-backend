package com.pli.codes.platepal.accounts.service;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.model.mapping.PlatePalUserDetailsMapper;
import com.pli.codes.platepal.accounts.model.repository.AccountRepository;
import com.pli.codes.platepal.accounts.security.PlatePalUserDetails;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
public class AccountService implements UserDetailsManager {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Mappers.getMapper(PlatePalUserDetailsMapper.class).accountToUserDetails(
                repository.findByEmailAddress(username).orElseThrow(
                        () -> new UsernameNotFoundException("No user found with username = " + username)));
    }

    @Override
    public void createUser(UserDetails user) {
        createUser(Mappers.getMapper(PlatePalUserDetailsMapper.class).userDetailsToAccount((PlatePalUserDetails) user));
    }

    public void createUser(@Validated Account account) {
        repository.save(account);
    }

    @Override
    public void updateUser(UserDetails user) {
        updateUser(Mappers.getMapper(PlatePalUserDetailsMapper.class).userDetailsToAccount((PlatePalUserDetails) user));
    }

    public void updateUser(@Validated Account account) {
        repository.save(account);
    }

    @Override
    public void deleteUser(String username) {
        Account account = repository.findByEmailAddress(username).orElseThrow(
                () -> new UsernameNotFoundException("No User found for username -> " + username));
        repository.delete(account);
    }

    /**
     * This method assumes that both oldPassword and the newPassword params are encoded with configured passwordEncoder
     *
     * @param oldPassword the old password of the user
     * @param newPassword the new password of the user
     */
    @Override
    @Transactional
    public void changePassword(String oldPassword, String newPassword) {
        Account userDetails = repository.findByPassword(oldPassword)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid password "));
        userDetails.setPassword(newPassword);
        repository.save(userDetails);
    }

    @Override
    public boolean userExists(String username) {
        return repository.findByEmailAddress(username).isPresent();
    }

}
