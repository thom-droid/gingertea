package com.thom.gingertea.domain.product.controller;

import com.thom.gingertea.domain.product.dto.ProductRequest;
import com.thom.gingertea.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Long> save(
            @RequestPart("register") ProductRequest.Post.Register register,
            @RequestPart List<MultipartFile> image) {
        return new ResponseEntity<>(productService.saveOne(register.toEntity(), image), HttpStatus.CREATED);
    }

}
