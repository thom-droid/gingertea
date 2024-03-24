package com.thom.gingertea.domain.supplier.dto;

import com.thom.gingertea.domain.supplier.entity.SupplierContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public interface SupplierResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class SupplierDetail {
        private Long id;
        private String username;
        private String companyName;
        private SupplierContact contact;

        public static SupplierDetail toSupplierDetailViewFrom(com.thom.gingertea.domain.supplier.entity.Supplier entity) {
            return SupplierDetail.builder()
                    .id(entity.getId())
                    .username(entity.getUsername())
                    .companyName(entity.getCompanyName())
                    .contact(entity.getSupplierContact())
                    .build();
        }
    }
}
