package com.example.pyro.Services;

import com.example.pyro.Model.Products;
import com.example.pyro.Model.User;
import com.example.pyro.Repositories.ProductsRepository;
import com.example.pyro.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductsRepository productsRepository;

    public Products createProduct(Products product) {
        try {
            return productsRepository.save(product);
        } catch (Exception e) {
            throw new Error(e.getMessage());
        }
        // return productsRepository.save(product);
    }
    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }
    public Optional<Products> getProductById(String productId) {
        return productsRepository.findById(productId);
    }

    public List<Products> getProductsByFarmer(String farmerId) {
        return productsRepository.findByFarmer_Id(farmerId);
    }

    public List<Products> getProductsByBuyer(String userId) {
        return productsRepository.findByBuyer_Id(userId);
    }

    public Products updateProduct(Products product) {
        return productsRepository.save(product);
    }

    public void deleteProduct(String productId) {
        productsRepository.deleteById(productId);
    }
    
    public Products buyProduct(User user, Products product, double quantity) {
        double totalCost = product.getPrice() * quantity;

        if (user.getWalletBalance() < totalCost) {
            throw new RuntimeException("Insufficient balance to buy product.");
        }

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock available.");
        }


        user.setWalletBalance(user.getWalletBalance() - totalCost);
        user.getPurchasedProducts().add(product);
        product.setQuantity(product.getQuantity() - quantity);
        userRepository.save(user);
        productsRepository.save(product);

        return product;
    }
}
