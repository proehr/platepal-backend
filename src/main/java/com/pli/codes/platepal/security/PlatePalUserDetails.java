package com.pli.codes.platepal.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
public class PlatePalUserDetails implements UserDetails {

    private static final long serialVersionUID = 2;
    @Getter
    private final boolean accountNonExpired = true;
    @Getter
    private final boolean accountNonLocked = true;
    @Getter
    private final boolean credentialsNonExpired = true;
    @Getter
    private final boolean enabled = true;

    @Getter
    private String password;
    private String emailAddress;


    public Set<GrantedAuthority> getAuthorities() {
        return Collections.emptySet();
    }

    @Override
    public String getUsername() {
        return emailAddress;
    }
}
