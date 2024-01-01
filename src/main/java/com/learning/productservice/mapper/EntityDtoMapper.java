package com.learning.productservice.mapper;

import com.learning.productservice.model.entity.Product;
import com.learning.productservice.model.request.ProductRequest;
import com.learning.productservice.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface EntityDtoMapper {

    ProductResponse productEntityToDto(Product product);

    List<ProductResponse> productEntityListToDtoList(Page<Product> productList);

    Product productDtoToEntity(ProductRequest productRequest);

    /**
     * MapStruct will automatically use the map method when converting Optional<Long> to Long.
     *
     * @param value the optional value to map
     * @return the mapped Long value, or null if the optional is empty
     */
    default Long map(Optional<Long> value) {
        return value.orElse(null);
    }
}
