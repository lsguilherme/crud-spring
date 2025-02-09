package com.guilhermels.crud.services;

import com.guilhermels.crud.dtos.ProductRequestDto;
import com.guilhermels.crud.dtos.ProductResponseDto;
import com.guilhermels.crud.entities.ProductEntity;
import com.guilhermels.crud.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        ProductEntity product = new ProductEntity(productRequestDto.name(), productRequestDto.price(), productRequestDto.description());
        product = productRepository.save(product);
        return new ProductResponseDto(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getCreatedAt(), product.getUpdatedAt());
    }
}
