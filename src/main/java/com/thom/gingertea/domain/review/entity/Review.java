package com.thom.gingertea.domain.review.entity;


import com.thom.gingertea.domain.product.entity.Product;
import com.thom.gingertea.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int rating;

    @JoinColumn(name = "PRODUCT_ID")
    @ManyToOne
    private Product product;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;
}
