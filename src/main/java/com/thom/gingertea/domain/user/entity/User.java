package com.thom.gingertea.domain.user.entity;

import com.thom.gingertea.common.role.Role;
import com.thom.gingertea.common.role.UserRole;
import com.thom.gingertea.config.audit.Auditing;
import com.thom.gingertea.domain.user.dto.UserResponse;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class User extends Auditing implements UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    private String nickname;

    private String img;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;

    @Override
    public String getRoleName() {
        return this.role.getRoleName();
    }

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
