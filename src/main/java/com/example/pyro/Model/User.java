package com.example.pyro.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.ArrayList;
import java.util.List;

@Document("users")
public class User {

    @Id
    private String id;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String address;
    private double walletBalance;

    @DBRef
    private List<Products> purchasedProducts = new ArrayList<>(); 

    @DBRef
    private List<Shares> purchasedShares = new ArrayList<>();

    public User() {
    }

    public User(String username, String email, String phone, String password, String address, double walletBalance,
            List<Products> purchasedProducts, List<Shares> purchasedShares) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.walletBalance = walletBalance;
        this.purchasedProducts = purchasedProducts;
        this.purchasedShares = purchasedShares;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWalletBalance() {
        return walletBalance;
    }

    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    public List<Products> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(List<Products> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public List<Shares> getPurchasedShares() {
        return purchasedShares;
    }

    public void setPurchasedShares(List<Shares> purchasedShares) {
        this.purchasedShares = purchasedShares;
    }
}
