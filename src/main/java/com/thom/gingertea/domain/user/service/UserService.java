package com.thom.gingertea.domain.user.service;

import com.thom.gingertea.domain.user.entity.User;

public interface UserService {
    User join(User request);

    User login(User request);

    User getUser(Long id);

    User updateUser(User request);

    void deleteUser(Long id);

    void duplicates(String username);

    User getIfExists(Long id);
}
