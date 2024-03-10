package com.thom.gingertea.domain.user.entity;

import com.thom.gingertea.domain.user.dto.UserRequest;
import com.thom.gingertea.domain.user.dto.UserResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String img;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public void update(User request) {
        this.nickname = request.getNickname();
    }

    public UserResponse.Default toResponse() {
        return UserResponse.Default.builder().id(id).username(username).build();
    }

    public UserResponse.Get.UserDetail toDetailResponse() {
        return UserResponse.Get.UserDetail.builder().id(id).username(username).nickname(nickname).img(img).build();
    }

    public UserResponse.Patch.Updated toUpdatedResponse() {
        return UserResponse.Patch.Updated.builder().nickname(nickname).build();
    }
}
