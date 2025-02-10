package com.guilhermels.crud.controllers;

import com.guilhermels.crud.dtos.requests.ProductRequestDto;
import com.guilhermels.crud.dtos.responses.ProductResponseDto;
import com.guilhermels.crud.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRequestDto));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id));
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        productService.deleteProductById(id);
        return ResponseEntity.ok("Item deletado com sucesso");
    }
}
