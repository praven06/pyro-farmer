package com.example.pyro.Controller;

import com.example.pyro.DTO.BuyProductsRequest;
import com.example.pyro.Model.Products;
import com.example.pyro.Model.User;
import com.example.pyro.Services.ProductsService;
import com.example.pyro.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductsService productsService;

    @PostMapping("/create")
    public ResponseEntity<Products> createProduct(@RequestBody Products product) {
        System.out.println(product.getId() + " idhu dhaan da product id ");
        if (product.getPrice() <= 0 || product.getQuantity() <= 0 || product.getCrop() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Products savedProduct = productsService.createProduct(product);

        return ResponseEntity.ok(savedProduct);  
    }

    @GetMapping("/allproducts")
    public ResponseEntity<List<Products>> getAllProducts(){
        List<Products> products = productsService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    @GetMapping("/{productId}")
    public ResponseEntity<Products> getProductById(@PathVariable String productId) {
        Optional<Products> product = productsService.getProductById(productId);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Products>> getProductsByFarmer(@PathVariable String farmerId) {
        return ResponseEntity.ok(productsService.getProductsByFarmer(farmerId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Products>> getProductsByBuyer(@PathVariable String userId) {
        return ResponseEntity.ok(productsService.getProductsByBuyer(userId));
    }

    @PutMapping("/update")
    public ResponseEntity<Products> updateProduct(@RequestBody Products product) {
        return ResponseEntity.ok(productsService.updateProduct(product));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        productsService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/buy")
    public ResponseEntity<Products> buyProduct(@RequestBody BuyProductsRequest request) {
        Optional<User> user = userService.getUserById(request.getUserId());
        Optional<Products> product = productsService.getProductById(request.getProductId());

        if (user.isEmpty() || product.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Products updatedProduct = productsService.buyProduct(user.get(), product.get(), request.getQuantity());
        return ResponseEntity.ok(updatedProduct);
    }
}
