package com.pli.codes.platepal.cookbook.model.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for {@link com.pli.codes.platepal.accounts.model.entity.Account}
 */
@AllArgsConstructor
@Getter
public class AccountDto implements Serializable {

    private final Long accountId;
    private final String emailAddress;
}
