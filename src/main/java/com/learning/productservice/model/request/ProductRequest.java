package com.learning.productservice.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.util.Optional;

public record ProductRequest(Optional<Long> productId,
                             @NotBlank String productName,
                             @NotNull @Positive BigDecimal productPrice,
                             @NotNull @PositiveOrZero Long productQuantity) {
}
