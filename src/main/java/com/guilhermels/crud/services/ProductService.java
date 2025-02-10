package com.guilhermels.crud.services;

import com.guilhermels.crud.dtos.requests.ProductRequestDto;
import com.guilhermels.crud.dtos.responses.ProductResponseDto;
import com.guilhermels.crud.entities.ProductEntity;
import com.guilhermels.crud.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto){
        ProductEntity productEntity = new ProductEntity(productRequestDto.name(), productRequestDto.price(), productRequestDto.description());
        productEntity = productRepository.save(productEntity);
        return new ProductResponseDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getCreatedAt(),
                productEntity.getUpdatedAt()
        );
    }

    public ProductResponseDto findProductById(Integer id){
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return new ProductResponseDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getDescription(),
                productEntity.getCreatedAt(),
                productEntity.getUpdatedAt()
        );
    }
}
