package com.thom.gingertea.domain.product.dto;

import com.thom.gingertea.domain.product.entity.Product;
import com.thom.gingertea.domain.product.entity.embed.ProductMeta;
import com.thom.gingertea.domain.supplier.entity.Supplier;
import lombok.*;

public interface ProductRequest {

    interface Get {

    }

    interface Post {

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        @Builder
        @Data
        class Register {
            private Supplier supplier;
            private ProductMeta productMeta;
            private String name;
            private String description;
            private int price;
            private int stock;

            public Product toEntity () {
                return Product.builder()
                        .supplier(supplier)
                        .productMeta(productMeta)
                        .name(name)
                        .description(description)
                        .price(price)
                        .stock(stock)
                        .build();
            }
        }
    }
}
