package com.pli.codes.platepal.cookbook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.accounts.model.entity.Account}
 */
@AllArgsConstructor
@Getter
public class AccountDto {

    private final Long accountId;
    private final String emailAddress;
}
