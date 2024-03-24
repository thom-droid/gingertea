package com.thom.gingertea.domain.product.service;

import com.thom.gingertea.common.exception.BusinessException;
import com.thom.gingertea.domain.product.entity.Product;
import com.thom.gingertea.domain.product.repository.ProductRepository;
import com.thom.gingertea.domain.supplier.entity.Supplier;
import com.thom.gingertea.domain.supplier.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.thom.gingertea.common.exception.CustomBusinessExceptionCode.Supplier.NO_SUCH_SUPPLIER;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public Long saveOne(Product product, List<MultipartFile> images) {

        Long supplierId = product.getSupplier().getId();
        Supplier supplier = supplierRepository.findById(supplierId).orElseThrow(() -> new BusinessException(NO_SUCH_SUPPLIER));
        product.addSupplier(supplier);

        // handle images
        Product save = productRepository.save(product);
        return save.getId();
    }
}

