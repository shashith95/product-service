package com.learning.productservice.controller;

import com.learning.productservice.mapper.EntityDtoMapper;
import com.learning.productservice.model.common.ApiResponse;
import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;
import com.learning.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.learning.productservice.util.ResponseHandler.generateResponse;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final EntityDtoMapper entityDtoMapper;

    public ProductController(ProductService productService,
                             EntityDtoMapper entityDtoMapper) {
        this.productService = productService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getProductById(@RequestParam Long productId) {
        logger.info("Get employer by ID: {} API triggered", productId);
        Product employer = productService.getProductById(productId);

        return generateResponse(HttpStatus.OK, "Employer Returned Successfully", "100",
                entityDtoMapper.productEntityToDto(employer));
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<ApiResponse> saveOrUpdateProduct(@Valid @RequestBody ProductRequest productRequest) {
        logger.info("Save or Update employer API triggered with request body: {}", productRequest);
        Product product = productService.saveOrUpdateProduct(productRequest);

        return generateResponse(productRequest.productId().isEmpty() ? HttpStatus.CREATED : HttpStatus.OK,
                "Employer " + (productRequest.productId().isEmpty() ? "Created" : "Updated") + " Successfully",
                "100",
                entityDtoMapper.productEntityToDto(product));
    }
}
