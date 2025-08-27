package com.justeam.justock_api.service;

import com.justeam.justock_api.model.product;
import com.justeam.justock_api.repository.productRepository;
import com.justeam.justock_api.request.productCreateRequest;
import com.justeam.justock_api.request.productUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;

    public List<product> listAllproducts() {
        return productRepository.findAll();
    }

    public product findproduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public product createproduct(productCreateRequest dto) {
        product product = new product();
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        product.setQuantidade(dto.getQuantidade());
        return productRepository.save(product);
    }

    public product updateproduct(Long id, productUpdateRequest dto) {
        Optional<product> opt = productRepository.findById(id);
        if (opt.isEmpty()) return null;

        product product = opt.get();
        if (dto.getNome() != null) product.setNome(dto.getNome());
        if (dto.getPreco() != null) product.setPreco(dto.getPreco());
        if (dto.getQuantidade() != null) product.setQuantidade(dto.getQuantidade());

        return productRepository.save(product);
    }

    public boolean deleteproduct(Long id) {
        Optional<product> opt = productRepository.findById(id);
        if (opt.isEmpty()) return false;

        productRepository.delete(opt.get());
        return true;
    }
}
