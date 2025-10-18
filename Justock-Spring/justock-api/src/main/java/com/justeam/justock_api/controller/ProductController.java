package com.justeam.justock_api.controller;

import com.justeam.justock_api.dto.ApiResponseDTO;
import com.justeam.justock_api.dto.ProductResponseDTO;
import com.justeam.justock_api.model.Product;
import com.justeam.justock_api.request.ProductCreateRequest;
import com.justeam.justock_api.request.ProductUpdateRequest;
import com.justeam.justock_api.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ApiResponseDTO<List<ProductResponseDTO>> index() {
        List<ProductResponseDTO> products = productService.listAllproducts()
            .stream()
            .map(p -> new ProductResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()))
            .collect(Collectors.toList());

        return new ApiResponseDTO<>(200, "Produtos encontrados!!", products);
    }

    @GetMapping("/{id}")
    public ApiResponseDTO<ProductResponseDTO> show(@PathVariable Long id) {
        Product p = productService.findproduct(id);
        if (p == null) return new ApiResponseDTO<>(404, "Produto não encontrado!", null);

        return new ApiResponseDTO<>(200, "Produto encontrado!!",
                new ProductResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()));
    }

    @PostMapping
    public ApiResponseDTO<ProductResponseDTO> store(@Valid @RequestBody ProductCreateRequest dto) {
        Product p = productService.createproduct(dto);
        return new ApiResponseDTO<>(200, "Produto cadastrado com sucesso!!",
                new ProductResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()));
    }

    @PutMapping("/{id}")
    public ApiResponseDTO<ProductResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody ProductUpdateRequest dto) {
        Product p = productService.updateproduct(id, dto);
        if (p == null) return new ApiResponseDTO<>(404, "Produto não encontrado!", null);

        return new ApiResponseDTO<>(200, "Produto atualizado!!",
                new ProductResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()));
    }

    @DeleteMapping("/{id}")
    public ApiResponseDTO<Void> destroy(@PathVariable Long id) {
        boolean deleted = productService.deleteproduct(id);
        if (!deleted) return new ApiResponseDTO<>(404, "Produto não encontrado!", null);

        return new ApiResponseDTO<>(200, "Produto deletado!!", null);
    }
}
