package com.thom.gingertea.domain.user.controller;

import com.google.gson.Gson;
import com.thom.gingertea.domain.user.dto.UserRequest;
import com.thom.gingertea.domain.user.dto.UserResponse;
import com.thom.gingertea.domain.user.entity.User;
import com.thom.gingertea.domain.user.repository.UserRepository;
import com.thom.gingertea.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    private final static String requestMapping = "api/v1/user";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    private final Gson gson = new Gson();

    @Autowired
    private UriComponentsBuilder baseUrl;

    @Autowired
    private User testUser;

    @Test
    void signUp() throws Exception {
        UserRequest.Post.Join join = UserRequest.Post.Join.builder()
                .email("mitsuki@gmail.com")
                .username("mitsuki")
                .password("1232457689")
                .nickname("mitsu")
                .build();

        when(userService.join(Mockito.any(User.class))).thenReturn(testUser);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(testUser);

        String uriString = baseUrl.path(requestMapping).path("/signup").build().toUriString();

        mockMvc.perform(post(uriString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(join)))
                .andExpect(status().isCreated());
    }

    @Test
    void signIn() throws Exception {
        UserRequest.Post.Login login = UserRequest.Post.Login.builder()
                .username("mitsuki")
                .password("1232457689")
                .build();

        String requestBody = gson.toJson(login);

        when(userRepository.findByUsernameAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(testUser));
        when(userService.login(login.toEntity())).thenReturn(testUser);

        mockMvc.perform(post(baseUrl.path(requestMapping).path("/signin").build().toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    void getUser() throws Exception {
        Long id = 1L;
        when(userRepository.findById(id)).thenReturn(Optional.of(testUser));
        when(userService.getUser(id)).thenReturn(testUser);

        MockHttpServletResponse response = mockMvc.perform(get(baseUrl.path(requestMapping).path("/{id}").build(id))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse();

        assertTrue(response.getContentAsString().contains("mitsuki"));
    }

    @Test
    void updateUser() throws Exception {
        UserRequest.Patch.Update update = UserRequest.Patch.Update.builder()
                .id(1L)
                .nickname("mitsuki")
                .build();

        User updated = testUser;
        testUser.setNickname(update.getNickname());

        when(userService.updateUser(update.toEntity())).thenReturn(updated);

        mockMvc.perform(patch(baseUrl.path(requestMapping).path("/update").toUriString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(update)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser() throws Exception {
           mockMvc.perform(delete(baseUrl.path(requestMapping).path("/{id}").build(Mockito.anyLong())))
                .andExpect(status().isNoContent());
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public UriComponentsBuilder baseUrl() {
            return UriComponentsBuilder.newInstance().scheme("http").host("localhost").port(8080);
        }

        @Bean
        public User testUser() {
            return User.builder()
                    .username("mitsuki")
                    .nickname("mitsu")
                    .password("1232457689")
                    .build();
        }
    }
}