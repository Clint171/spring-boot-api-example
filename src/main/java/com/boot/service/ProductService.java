package com.boot.service;

import com.boot.entity.Product;
import com.boot.repository.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    public ResponseEntity<List<Product>> fetchAllProducts() {
        return ResponseEntity.ok(productRepository.findAll());
    }

    // Get a product by ID
    public ResponseEntity<Optional<Product>> fetchProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Product> updateProduct(Long id, Product updatedProduct) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        Product Existingproduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        Existingproduct.setName(updatedProduct.getName());
        Existingproduct.setPrice(updatedProduct.getPrice());
        Existingproduct.setQuantity(updatedProduct.getQuantity());
        Product savedEntity = productRepository.save(Existingproduct);
        return ResponseEntity.ok(savedEntity);
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product Deleted Successfully");
    }


  public List<Product> getAllProducts() {

        return productRepository.findAll();
    }


public Product getProductById(Long id) {
    Optional<Product> product = productRepository.findById(id);
    return product.orElse(null);
}
public ResponseEntity<Product> saveProduct(Product product){
    Product newProduct = productRepository.save(product);
    return ResponseEntity.ok(newProduct);
}
public List<Product> getProductsByName(String name) {
    return productRepository.findByName(name);
}

public List<Product> getProductsByPrice(double price) {
    return productRepository.findByPrice(price);
}
}
