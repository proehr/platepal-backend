package com.pli.codes.platepal.accounts.model.mapping;

import com.pli.codes.platepal.accounts.model.entity.Account;
import com.pli.codes.platepal.accounts.security.PlatePalUserDetails;
import org.mapstruct.Mapper;

@Mapper
public interface PlatePalUserDetailsMapper {

    PlatePalUserDetails accountToUserDetails(Account account);

    Account userDetailsToAccount(PlatePalUserDetails userDetails);
}
