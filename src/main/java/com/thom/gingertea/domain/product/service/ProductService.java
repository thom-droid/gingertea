package com.thom.gingertea.domain.product.service;

import com.thom.gingertea.domain.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Long saveOne(Product product, List<MultipartFile> images);

}
