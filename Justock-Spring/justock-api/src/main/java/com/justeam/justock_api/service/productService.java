package com.justeam.justock_api.service;

import com.justeam.justock_api.model.Product;
import com.justeam.justock_api.repository.ProductRepository;
import com.justeam.justock_api.request.ProductCreateRequest;
import com.justeam.justock_api.request.ProductUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAllproducts() {
        return productRepository.findAll();
    }

    public Product findproduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product createproduct(ProductCreateRequest dto) {
        Product product = new Product();
        product.setNome(dto.getNome());
        product.setPreco(dto.getPreco());
        product.setQuantidade(dto.getQuantidade());
        return productRepository.save(product);
    }

    public Product updateproduct(Long id, ProductUpdateRequest dto) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isEmpty()) return null;

        Product product = opt.get();
        if (dto.getNome() != null) product.setNome(dto.getNome());
        if (dto.getPreco() != null) product.setPreco(dto.getPreco());
        if (dto.getQuantidade() != null) product.setQuantidade(dto.getQuantidade());

        return productRepository.save(product);
    }

    public boolean deleteproduct(Long id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isEmpty()) return false;

        productRepository.delete(opt.get());
        return true;
    }
}
