package com.microservice.service.impl;

import com.microservice.dto.ProductDto;
import com.microservice.entity.Product;
import com.microservice.exception.ResourceNotFoundException;
import com.microservice.repository.IProductRepository;
import com.microservice.service.IProductService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Log4j2
public class ProductServiceImpl implements IProductService {

    private IProductRepository productRepository;

    private ModelMapper modelMapper;

    @Override
    public ProductDto addProduct(ProductDto productDto) {


        // convert ProductDto into Product Jpa entity
        Product product = modelMapper.map(productDto, Product.class);

        // Product Jpa entity
        Product savedProduct = productRepository.save(product);

        // Convert saved Todo Jpa entity object into TodoDto object

        log.info("Product Created");
        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        log.info("Get the product for the productId: {}", productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        log.info("Product exists");
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("Get all products... ");

        List<Product> products = productRepository.findAll();

        log.info("All products Listed");
        // Burayı öğren iyice
        return products.stream().map((product) -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("Reduce Quantity {} for Id: {}", quantity, productId);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        if (product.getQuantity() < quantity){
            throw new ResourceNotFoundException("Product not found with id: " + productId);
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);

        log.info("Product Quantity Updated Successfully");
    }
}
