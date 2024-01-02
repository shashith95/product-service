package com.learning.productservice.service.impl;

import com.learning.productservice.exception.DataNotFoundException;
import com.learning.productservice.exception.GeneralException;
import com.learning.productservice.mapper.EntityDtoMapper;
import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;
import com.learning.productservice.repository.ProductRepository;
import com.learning.productservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void updateProductQuantity(Long productId, Long quantity) throws GeneralException {
        Product product = getProductById(productId);

        if (product.getProductQuantity() < quantity) {
            throw new GeneralException("Product does not have sufficient quantity", "INSUFFICIENT_QUANTITY");
        }

        product.setProductQuantity(product.getProductQuantity() - quantity);
        productRepository.save(product);
    }
}
