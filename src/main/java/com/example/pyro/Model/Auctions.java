package com.example.pyro.Model;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document("auctions")
public class Auctions {
    
    @Id
    private String auctionId;
    private String auction_name;
    @JsonProperty("starting_bid")
    private double start_bid;
    private double quantity_available;
    private String description;
    private String properties;
    private String auction_image;
    private LocalDateTime createdAt = LocalDateTime.now();
    private double top_bid;
    private String farmerId;
    public String getFarmerId() {
        return farmerId;
    }
    public void setFarmerId(String farmerId) {
        this.farmerId = farmerId;
    }
    @DBRef
    private Farmer farmer;


    public double getTop_bid() {
        return top_bid;
    }
    public void setTop_bid(double top_bid) {
        this.top_bid = top_bid;
    }
    public String getauctionId() {
        return auctionId;
    }
    public void setauctionId(String auctionId) {
        this.auctionId = auctionId;
    }
    public String getAuction_name() {
        return auction_name;
    }
    public void setAuction_name(String auction_name) {
        this.auction_name = auction_name;
    }
    
    public double getStart_bid() {
        return start_bid;
    }
    public void setStart_bid(double start_bid) {
        
        this.start_bid = start_bid;
    }
    public double getQuantity_available() {
        return quantity_available;
    }
    public void setQuality_available(double quality_available) {
        this.quantity_available = quality_available;
    }
    public String getdescription() {
        return description;
    }
    public void setdescription(String description) {
        this.description = description;
    }
    public String getProperties() {
        return properties;
    }
    public void setProperties(String properties) {
        this.properties = properties;
    }
    public String getAuction_image() {
        return auction_image;
    }
    public void setAuction_image(String auction_image) {
        this.auction_image = auction_image;
    }
    public Farmer getFarmer() {
        return farmer;
    }
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
    

    
    
}
