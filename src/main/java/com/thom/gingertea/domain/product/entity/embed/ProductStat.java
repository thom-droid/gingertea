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
public class ProductStat {

    private int viewCount;
    private int weeklyViewCount;
    private int likeCount;
    private int soldCount;
    private int accumulatedSoldCount;

}


