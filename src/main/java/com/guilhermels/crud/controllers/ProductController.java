package com.guilhermels.crud.controllers;

import com.guilhermels.crud.dtos.requests.ProductRequestDto;
import com.guilhermels.crud.dtos.responses.ProductResponseDto;
import com.guilhermels.crud.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(
            summary = "Salvar um produto",
            description = "Salva um produto no banco de dados.",
            tags = {"produto"}
    )
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductRequestDto productRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productRequestDto));
    }

    @GetMapping(value = "{id}")
    @Operation(
            summary = "Obter um produto pelo id",
            description = "Busca um produto baseado id no banco de dados.",
            tags = {"produto"}
    )
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findProductById(id));
    }

    @DeleteMapping(value = "{id}")
    @Operation(
            summary = "Deletar um produto pelo id",
            description = "Deleta um produto baseado id no banco de dados.",
            tags = {"produto"}
    )
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Item deletado com sucesso");
    }

    @PutMapping(value = "{id}")
    @Operation(
            summary = "Atualizar um produto pelo id",
            description = "Atualiza um produto baseado no id.",
            tags = {"produto"}
    )
    public ResponseEntity<ProductResponseDto> updateProductById(@PathVariable(value = "id") Integer id,
                                                                @RequestBody ProductRequestDto productRequestDto) {

        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductById(id, productRequestDto));
    }

    @GetMapping
    @Operation(
            summary = "Listar produtos por filtro",
            description = "Lista produtos com base nos filtro fornecidos e retorna uma paginação.",
            tags = {"produto"},
            parameters = {
                    @Parameter(name = "page", description = "Número da página", required = false),
                    @Parameter(name = "size", description = "Número de itens por página", required = false)
            }
    )
    public ResponseEntity<Page<ProductResponseDto>> findAllProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts(page, size));
    }
}
