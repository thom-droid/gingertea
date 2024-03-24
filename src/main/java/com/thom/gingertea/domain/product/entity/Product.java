package com.thom.gingertea.domain.product.entity;

import com.thom.gingertea.config.audit.Auditing;
import com.thom.gingertea.domain.file.entity.ProductImage;
import com.thom.gingertea.domain.hashtag.entity.HashTag;
import com.thom.gingertea.domain.like.entity.ProductLike;
import com.thom.gingertea.domain.product.entity.embed.ProductMeta;
import com.thom.gingertea.domain.product.entity.embed.ProductStat;
import com.thom.gingertea.domain.review.entity.Review;
import com.thom.gingertea.domain.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class Product extends Auditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ProductMeta productMeta;

    private ProductStat productStat;

    private String name;

    private String description;

    private int price;

    private int stock;

    @Builder.Default
    private Status status = Status.ON_SALE;

    @Getter
    @RequiredArgsConstructor
    public enum Status {

        ON_SALE(1, "ON_SALE", "판매중"),
        SOLD_OUT(2, "SOLD_OUT", "품절"),
        DELETED(3, "DELETED", "삭제됨"),
        ;

        final int value;
        final String desc;
        final String descKr;
    }

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @Builder.Default
    private List<HashTag> hashTags = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @Builder.Default
    private List<ProductLike> productLikes = new ArrayList<>();

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @Builder.Default
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

    public void addHashTag(HashTag hashTag) {
        this.getHashTags().add(hashTag);
        if (hashTag.getProducts().contains(this)) hashTag.getProducts().add(this);
    }

    public void addProductLike(ProductLike productLike) {
        this.getProductLikes().add(productLike);
        if (productLike.getProduct() != this) productLike.setProduct(this);
    }

    public void addProductImage(ProductImage productImage) {
        this.getProductImages().add(productImage);
    }

    public List<String> getProductImageUrls() {
        return this.getProductImages().stream().map(ProductImage::getUrl).toList();
    }
}
