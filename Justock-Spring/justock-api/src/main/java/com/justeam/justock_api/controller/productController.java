package com.justeam.justock_api.controller;

import com.justeam.justock_api.dto.apiResponseDTO;
import com.justeam.justock_api.dto.productResponseDTO;
import com.justeam.justock_api.model.product;
import com.justeam.justock_api.request.productCreateRequest;
import com.justeam.justock_api.request.productUpdateRequest;
import com.justeam.justock_api.service.productService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class productController {

    @Autowired
    private productService productService;

    @GetMapping
    public apiResponseDTO<List<productResponseDTO>> index() {
        List<productResponseDTO> products = productService.listAllproducts()
            .stream()
            .map(p -> new productResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()))
            .collect(Collectors.toList());

        return new apiResponseDTO<>(200, "Produtos encontrados!!", products);
    }

    @GetMapping("/{id}")
    public apiResponseDTO<productResponseDTO> show(@PathVariable Long id) {
        product p = productService.findproduct(id);
        if (p == null) return new apiResponseDTO<>(404, "Produto não encontrado!", null);

        return new apiResponseDTO<>(200, "Produto encontrado!!",
                new productResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()));
    }

    @PostMapping
    public apiResponseDTO<productResponseDTO> store(@Valid @RequestBody productCreateRequest dto) {
        product p = productService.createproduct(dto);
        return new apiResponseDTO<>(200, "Produto cadastrado com sucesso!!",
                new productResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()));
    }

    @PutMapping("/{id}")
    public apiResponseDTO<productResponseDTO> update(@PathVariable Long id,
                                                     @Valid @RequestBody productUpdateRequest dto) {
        product p = productService.updateproduct(id, dto);
        if (p == null) return new apiResponseDTO<>(404, "Produto não encontrado!", null);

        return new apiResponseDTO<>(200, "Produto atualizado!!",
                new productResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getQuantidade()));
    }

    @DeleteMapping("/{id}")
    public apiResponseDTO<Void> destroy(@PathVariable Long id) {
        boolean deleted = productService.deleteproduct(id);
        if (!deleted) return new apiResponseDTO<>(404, "Produto não encontrado!", null);

        return new apiResponseDTO<>(200, "Produto deletado!!", null);
    }
}
