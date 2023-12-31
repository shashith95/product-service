package com.learning.productservice.model.response;

import java.math.BigDecimal;

public record ProductResponse(Long productId,
                              String productName,
                              BigDecimal productPrice,
                              Long productQuantity) {
}
