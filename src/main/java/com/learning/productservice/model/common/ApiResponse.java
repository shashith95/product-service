package com.learning.productservice.model.common;

import java.util.List;

public record ApiResponse(String message,
                          String messageCode,
                          Object data,
                          List<?> errorList) {
}
