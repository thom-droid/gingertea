package com.thom.gingertea.domain.hashtag.entity;

import com.thom.gingertea.domain.product.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class HashTag {

    @Id
    @GeneratedValue
    private Long id;

    private String hashTagName;

    @ManyToMany
    @JoinTable(name = "PRODUCT_HASTAG",
            joinColumns = @JoinColumn(name = "HASHTAG_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID"))
    @Builder.Default
    private List<Product> products = new ArrayList<>();

}
