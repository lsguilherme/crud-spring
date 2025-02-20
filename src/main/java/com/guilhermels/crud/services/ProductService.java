package com.guilhermels.crud.services;

import com.guilhermels.crud.dtos.requests.ProductRequestDto;
import com.guilhermels.crud.dtos.responses.ProductResponseDto;
import com.guilhermels.crud.entities.ProductEntity;
import com.guilhermels.crud.mappers.ProductMapper;
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
        ProductEntity productEntity = ProductMapper.INSTANCE.toEntity(productRequestDto);
        productEntity = productRepository.save(productEntity);
        return ProductMapper.INSTANCE.toDto(productEntity);
    }

    public ProductResponseDto findProductById(Integer id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        return ProductMapper.INSTANCE.toDto(productEntity);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteById(id);
    }

    public ProductResponseDto updateProductById(Integer id, ProductRequestDto productRequestDto) {
        ProductEntity productEntity = productRepository.findById(id).get();

        ProductMapper.INSTANCE.updateProductFromDto(productRequestDto, productEntity);

        productEntity = productRepository.save(productEntity);
        return ProductMapper.INSTANCE.toDto(productEntity);
    }

    public Page<ProductResponseDto> findAllProducts(int page, int size, String direction, String orderBy) {
        Page<ProductEntity> productEntities = productRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(direction), orderBy));

        return productEntities.map(ProductMapper.INSTANCE::toDto);
    }
}
