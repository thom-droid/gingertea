package com.thom.gingertea.domain.user.dto;

import com.thom.gingertea.domain.user.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface UserRequest extends DtoRequest<User> {

    interface Post {
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        @Builder
        class Join implements UserRequest {
            @NotNull
            private String username;
            @NotNull
            private String password;
            @NotNull
            private String email;
            @NotNull
            private String nickname;

            @Override
            public User toEntity() {
                return User.builder()
                        .username(username)
                        .password(password)
                        .email(email)
                        .nickname(nickname)
                        .build();
            }
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        @Builder
        class Login implements UserRequest {
            @NotNull
            private String username;
            @NotNull
            private String password;

            @Override
            public User toEntity() {
                return User.builder().username(username)
                        .password(password)
                        .build();
            }
        }
    }

    interface Get {
    }

    interface Patch {
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        @Builder
        class Update implements UserRequest {

            @NotNull
            private Long id;
            private String nickname;

            @Override
            public User toEntity() {
                return User.builder().id(id).nickname(nickname).build();
            }
        }
    }

    interface Delete {
    }
}
