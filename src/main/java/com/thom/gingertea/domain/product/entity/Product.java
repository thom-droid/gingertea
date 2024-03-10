package com.thom.gingertea.domain.product.entity;

import com.thom.gingertea.domain.file.entity.ProductImage;
import com.thom.gingertea.domain.hashtag.entity.ProductHashTag;
import com.thom.gingertea.domain.like.entity.ProductLike;
import com.thom.gingertea.domain.product.entity.embed.ProductMeta;
import com.thom.gingertea.domain.product.entity.embed.ProductStat;
import com.thom.gingertea.domain.review.entity.Review;
import com.thom.gingertea.domain.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProductMeta productMeta;

    private ProductStat productStat;

    private String name;

    private String description;

    private int price;

    private int stock;

    private boolean isDeleted;

    private boolean isSoldOut;

    private boolean isDisplay;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<ProductHashTag> productHashTags = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<ProductLike> productLikes = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private List<ProductImage> productImages = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public void addSupplier(Supplier supplier) {
        this.supplier = supplier;
        if (!this.supplier.getProducts().contains(this)) this.supplier.getProducts().add(this);
    }

    public void addReview(Review review) {
        this.getReviews().add(review);
        if (review.getProduct() != this) review.setProduct(this);
    }

    public void addProductHashTag(ProductHashTag productHashTag) {
        this.getProductHashTags().add(productHashTag);
        if (productHashTag.getProduct() != this) productHashTag.setProduct(this);
    }

    public void addProductLike(ProductLike productLike) {
        this.getProductLikes().add(productLike);
        if (productLike.getProduct() != this) productLike.setProduct(this);
    }

    public void addProductImage(ProductImage productImage) {
        this.getProductImages().add(productImage);
    }

}
