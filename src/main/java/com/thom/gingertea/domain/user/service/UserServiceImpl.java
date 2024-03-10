package com.thom.gingertea.domain.user.service;

import com.thom.gingertea.common.exception.BusinessException;
import com.thom.gingertea.domain.user.dto.UserRequest;
import com.thom.gingertea.domain.user.entity.User;
import com.thom.gingertea.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.thom.gingertea.common.exception.CustomBusinessExceptionCode.User.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User join(User request) {
        duplicates(request.getUsername());
        checkPassword(request.getPassword());
        return userRepository.save(request);
    }

    @Override
    public User login(User request) {
        return getIfExists(request.getUsername(), request.getPassword());
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(NO_SUCH_USER));
    }

    @Override
    public User updateUser(User request) {
        User user = getIfExists(request.getId());
        user.update(request);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = getIfExists(id);
        userRepository.delete(user);
    }

    @Override
    public void duplicates(String username) {
        findUser(username).ifPresent(user -> {
            throw new BusinessException(DUPLICATED_USERNAME);
        });
    }

    private Optional<User> findUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getIfExists(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BusinessException(NO_SUCH_USER));
    }

    private void checkPassword(String password) {
        if (password.length() < 8) {
            throw new BusinessException(INVALID_PASSWORD);
        }
    }

    private User getIfExists(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new BusinessException(INVALID_INFORMATION));
    }
}
