package com.thom.gingertea.domain.hashtag.dto;

import com.thom.gingertea.domain.hashtag.entity.HashTag;
import com.thom.gingertea.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

public interface HashTagResponse {

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    class HashTagDetail {
        private Long id;
        private String hashTagName;

        public static HashTagDetail toHashTagDetail(HashTag entity) {
            return HashTagDetail.builder()
                    .hashTagName(entity.getHashTagName())
                    .build();
        }

        public static List<HashTagDetail> toHashTagDetailViewFrom(Product entity) {
            return entity.getHashTags().stream()
                    .map(HashTagResponse.HashTagDetail::toHashTagDetail)
                    .toList();
        }
    }
}
