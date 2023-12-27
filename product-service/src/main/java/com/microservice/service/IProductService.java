package com.microservice.service;

import com.microservice.dto.ProductDto;

import java.util.List;

public interface IProductService {
    ProductDto addProduct(ProductDto productDto);

    ProductDto getProductById(Long productId);

    List<ProductDto> getAllProducts();

    void reduceQuantity(Long productId, Long quantity);
}
