package com.thom.gingertea.domain.product.dto;

import com.thom.gingertea.domain.hashtag.dto.HashTagResponse;
import com.thom.gingertea.domain.product.entity.Product;
import com.thom.gingertea.domain.product.entity.embed.ProductStat;
import com.thom.gingertea.domain.review.dto.ReviewResponse;
import com.thom.gingertea.domain.supplier.dto.SupplierResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.thom.gingertea.domain.hashtag.dto.HashTagResponse.HashTagDetail.toHashTagDetailViewFrom;
import static com.thom.gingertea.domain.review.dto.ReviewResponse.Detail.toReviewDetailViewsFrom;
import static com.thom.gingertea.domain.supplier.dto.SupplierResponse.SupplierDetail.toSupplierDetailViewFrom;

public interface ProductResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class ProductDetail {
        private Long id;
        private String name;
        private String img;
        private String description;
        private int price;
        private int stock;
        private int likeCount;
        private int reviewCount;
        private Product.Status status;
        private ProductStat stat;
        private List<String> productImages;
        private List<ReviewResponse.Detail> reviews;
        private List<HashTagResponse.HashTagDetail> hashTags;
        private SupplierResponse.SupplierDetail supplier;

        public static ProductDetail toProductDetailViewFrom(Product entity) {
            return ProductDetail.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .price(entity.getPrice())
                    .stock(entity.getStock())
                    .likeCount(entity.getProductLikes().size())
                    .reviewCount(entity.getReviews().size())
                    .status(entity.getStatus())
                    .stat(entity.getProductStat())
                    .productImages(entity.getProductImageUrls())
                    .reviews(toReviewDetailViewsFrom(entity))
                    .hashTags(toHashTagDetailViewFrom(entity))
                    .supplier(toSupplierDetailViewFrom(entity.getSupplier()))
                    .build();
        }
    }
}
