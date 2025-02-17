package com.example.pyro.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Products {
    
    @Id
    private String id;
    private String username;
    private String location;
    private String crop;
    private double price;
    private String sales;
    private int rating;
    private String availablity;
    @DBRef
    private Farmer farmer;

    @DBRef
    private User buyer;
    private double quantity;
    private String image;

    
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getCrop() {
        return crop;
    }
    public void setCrop(String crop) {
        this.crop = crop;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getSales() {
        return sales;
    }
    public void setSales(String sales) {
        this.sales = sales;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getAvailablity() {
        return availablity;
    }
    public void setAvailablity(String availablity) {
        this.availablity = availablity;
    }
    public Farmer getFarmer() {
        return farmer;
    }
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
    public double getQuantity() {
        return quantity;
    }
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    
}
