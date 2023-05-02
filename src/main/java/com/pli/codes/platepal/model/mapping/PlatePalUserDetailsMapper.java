package com.pli.codes.platepal.model.mapping;

import com.pli.codes.platepal.model.entity.Account;
import com.pli.codes.platepal.security.PlatePalUserDetails;
import org.mapstruct.Mapper;

@Mapper
public interface PlatePalUserDetailsMapper {

    PlatePalUserDetails accountToUserDetails(Account account);
    Account userDetailsToAccount(PlatePalUserDetails userDetails);
}
