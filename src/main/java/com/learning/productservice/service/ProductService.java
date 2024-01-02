package com.learning.productservice.service;

import com.learning.productservice.exception.GeneralException;
import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product getProductById(Long productId);

    Product saveOrUpdateProduct(ProductRequest productRequest);

    Page<Product> getAllProducts(int page, int size);

    void updateProductQuantity(Long productId, Long quantity) throws GeneralException;
}
