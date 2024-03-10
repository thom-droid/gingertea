package com.thom.gingertea.domain.product.entity.embed;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProductMeta {

    private String brandName;
    private String productId;
    private String sex;
    private String category;
    private String subCategory;

}
