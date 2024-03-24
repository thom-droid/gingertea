package com.thom.gingertea.domain.review.dto;

import com.thom.gingertea.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface ReviewResponse {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    class Detail {
        private Long id;
        private String content;
        private int rating;
        private Long productId;
        private Long reviewerId;
        private String reviewerName;

        public static Detail toReviewDetailView(com.thom.gingertea.domain.review.entity.Review entity) {
            return Detail.builder()
                    .id(entity.getId())
                    .content(entity.getContent())
                    .rating(entity.getRating())
                    .productId(entity.getProduct().getId())
                    .reviewerId(entity.getUser().getId())
                    .reviewerName(entity.getUser().getUsername())
                    .build();
        }

        public static List<Detail> toReviewDetailViewsFrom(Product entity) {
            return entity.getReviews().stream().map(ReviewResponse.Detail::toReviewDetailView).toList();
        }
    }
}
