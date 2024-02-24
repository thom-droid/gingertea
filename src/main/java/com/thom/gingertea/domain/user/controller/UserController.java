package com.thom.gingertea.domain.user.controller;

import com.thom.gingertea.domain.user.dto.UserRequest;
import com.thom.gingertea.domain.user.dto.UserResponse;
import com.thom.gingertea.domain.user.entity.User;
import com.thom.gingertea.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@Valid @RequestBody UserRequest.Post.Join join) {
        User joinedUser = userService.join(join.toEntity());
        return new ResponseEntity<>(joinedUser, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<User> signIn(@Valid @RequestBody UserRequest.Post.Login login) {
        User signedInUser = userService.login(login.toEntity());
        return new ResponseEntity<>(signedInUser, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse.Get.UserDetail> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return new ResponseEntity<>(user.toDetailResponse(), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<UserResponse.Patch.Updated> updateUser(@Valid @RequestBody UserRequest.Patch.Update update) {
        User updatedUser = userService.updateUser(update.toEntity());
        return new ResponseEntity<>(updatedUser.toUpdatedResponse(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
