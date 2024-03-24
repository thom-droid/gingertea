package com.thom.gingertea.domain.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thom.gingertea.domain.product.dto.ProductRequest;
import com.thom.gingertea.domain.product.repository.ProductRepository;
import com.thom.gingertea.domain.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @DisplayName("상품 저장")
    @Test
    void save() throws Exception {

        ProductRequest.Post.Register registerDto = ProductRequest.Post.Register.builder().productMeta(null).name("test").description("test").price(1000).stock(10).build();
        String json = objectMapper.writeValueAsString(registerDto);
        System.out.println("json = " + json);
        byte[] bytes = json.getBytes();
        MockMultipartFile register = new MockMultipartFile("register", null, "application/json", bytes);
        MockMultipartFile image2 = new MockMultipartFile("image", "image2.jpg", "image/jpeg", "test image 2".getBytes());

        mockMvc.perform(multipart("/api/v1/product/save")
                        .file(image2)
                        .file(register)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isCreated());
    }
}