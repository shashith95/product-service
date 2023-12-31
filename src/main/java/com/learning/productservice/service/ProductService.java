package com.learning.productservice.service;

import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;

public interface ProductService {
    Product getProductById(Long productId);

    Product saveOrUpdateProduct(ProductRequest productRequest);
}
