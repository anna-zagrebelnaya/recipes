package com.example.recipes.service;

import com.example.recipes.model.Product;
import com.example.recipes.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product findProductByName(String name) {
        List<Product> products = productRepository.findByName(name);
        return products.isEmpty() ? null : products.get(0);
    }

    public Product findOrCreateProduct(Product product) {
        if (product.getId() != null) {
            return findProductById(product.getId());
        }
        Product foundByName = findProductByName(product.getName());
        if (foundByName != null) {
            return foundByName;
        }
        return saveProduct(product);
    }
}
