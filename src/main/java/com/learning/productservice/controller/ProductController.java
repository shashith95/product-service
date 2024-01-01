package com.learning.productservice.controller;

import com.learning.productservice.mapper.EntityDtoMapper;
import com.learning.productservice.model.common.ApiResponse;
import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;
import com.learning.productservice.service.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
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
        logger.info("Get product by ID: {} API triggered", productId);
        Product product = productService.getProductById(productId);

        return generateResponse(HttpStatus.OK, "Product Returned Successfully", "100",
                entityDtoMapper.productEntityToDto(product));
    }

    @GetMapping("all-products")
    public ResponseEntity<ApiResponse> getAllProducts(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "1000") Integer resultSize) {
        logger.info("Get all products API triggered");
        Page<Product> productList = productService.getAllProducts(page, resultSize);

        return generateResponse(HttpStatus.OK, "Products Returned Successfully", "100",
                entityDtoMapper.productEntityListToDtoList(productList));
    }

    @RequestMapping(method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseEntity<ApiResponse> saveOrUpdateProduct(@Valid @RequestBody ProductRequest productRequest) {
        logger.info("Save or Update employer API triggered with request body: {}", productRequest);
        Product product = productService.saveOrUpdateProduct(productRequest);

        return generateResponse(productRequest.productId().isEmpty() ? HttpStatus.CREATED : HttpStatus.OK,
                "Product " + (productRequest.productId().isEmpty() ? "Created" : "Updated") + " Successfully",
                "100",
                entityDtoMapper.productEntityToDto(product));
    }
}
