package com.nratnovsky.hqs.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    HR,
    GUMSMITH;

    @Override
    public String getAuthority() {
        return name();
    }
}
