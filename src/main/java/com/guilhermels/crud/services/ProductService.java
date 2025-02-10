package com.guilhermels.crud.services;

import com.guilhermels.crud.dtos.requests.ProductRequestDto;
import com.guilhermels.crud.dtos.responses.ProductResponseDto;
import com.guilhermels.crud.entities.ProductEntity;
import com.guilhermels.crud.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public ProductResponseDto saveProduct(ProductRequestDto productRequestDto) {
        ProductEntity productEntity = new ProductEntity(productRequestDto.name(), productRequestDto.price(), productRequestDto.description());
        productEntity = productRepository.save(productEntity);
        return productEntity.toDto();
    }

    public ProductResponseDto findProductById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return productEntity.toDto();
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public ProductResponseDto updateProductById(Integer id, ProductRequestDto productRequestDto) {
        ProductEntity productEntity = productRepository.findById(id).get();

        productEntity.setName(productRequestDto.name());
        productEntity.setPrice(productRequestDto.price());
        productEntity.setDescription(productRequestDto.description());

        productEntity = productRepository.save(productEntity);
        return productEntity.toDto();
    }

    public Page<ProductResponseDto> findAllProducts(int page, int size) {
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, size));

        return productEntities.map(ProductEntity::toDto);
    }
}
