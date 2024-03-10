package com.thom.gingertea.domain.supplier.entity;

import com.thom.gingertea.common.role.Role;
import com.thom.gingertea.common.role.UserRole;
import com.thom.gingertea.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Supplier implements UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String businessNumber;

    private String companyName;

    private SupplierContact supplierContact;

    @Enumerated(EnumType.STRING)
    private Role role = Role.SUPPLIER;

    @Override
    public String getRoleName() {
        return this.role.getRoleName();
    }

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY, cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

}
