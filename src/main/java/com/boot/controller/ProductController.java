package com.boot.controller;
import com.boot.entity.Product;
import com.boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
    private final ProductService productService;
    public ProductController(@Autowired ProductService productService)
    {
        this.productService = productService;
    }

    // Create a new product
    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }
    // Get all products
    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }
    // Get a product by ID
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id)
    {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/products/name/{name}")
    public List<Product> getProductsByName(@PathVariable String name)
    {
        return productService.getProductsByName(name);
    }
    @GetMapping("/products/price/{price}")
    public List<Product> getProductsByPrice(@PathVariable double price)
    {
        return productService.getProductsByPrice(price);
    }
    // Update a product
    @PutMapping(path = "/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "productId")
                                                 Long productId, @RequestBody Product product)
    {
        return productService.updateProduct(productId, product);
    }
    // Delete a product
    @DeleteMapping(value = "/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
    {
        return productService.deleteProduct(productId);
    }

}


