package com.thom.gingertea.common.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN(1, "ADMIN"),
    SUPPLIER(2, "SUPPLIER"),
    CUSTOMER(3, "CUSTOMER");

    private final int id;
    private final String roleName;
}
