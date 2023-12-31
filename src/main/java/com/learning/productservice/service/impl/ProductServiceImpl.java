package com.learning.productservice.service.impl;

import com.learning.productservice.exception.DataNotFoundException;
import com.learning.productservice.mapper.EntityDtoMapper;
import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;
import com.learning.productservice.repository.ProductRepository;
import com.learning.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final EntityDtoMapper entityDtoMapper;

    public ProductServiceImpl(ProductRepository productRepository, EntityDtoMapper entityDtoMapper) {
        this.productRepository = productRepository;
        this.entityDtoMapper = entityDtoMapper;
    }

    @Override
    public Product getProductById(Long productId) {

        return productRepository.findById(productId)
                .orElseThrow(() -> new DataNotFoundException("No product found for id: " + productId, "1000"));
    }

    @Override
    public Product saveOrUpdateProduct(ProductRequest productRequest) {
        Product savedProduct = productRepository.save(entityDtoMapper.productDtoToEntity(productRequest));
        logger.info("Product saved successfully with ID: {}", savedProduct.getProductId());

        return savedProduct;
    }
}