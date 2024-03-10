package com.thom.gingertea.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface UserResponse {

    @AllArgsConstructor
    @Data
    @Builder
    class Default {
        private Long id;
        private String username;
    }

    interface Get {
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        @Builder
        class UserDetail {
            private Long id;
            private String username;
            private String nickname;
            private String img;
        }
    }

    interface Patch {
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        @Builder
        class Updated {
            private String nickname;
        }
    }
}
