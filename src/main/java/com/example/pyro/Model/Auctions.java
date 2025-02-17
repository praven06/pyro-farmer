package com.example.pyro.Model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("auctions")
public class Auctions {
    
    @Id
    private String auction_id;

    private String name;
    private double start_bid;
    private double quality_available;
    private String descripton;
    private String properties;
    private String auction_image;
    @DBRef
    private Farmer farmer;


    public String getAuction_id() {
        return auction_id;
    }
    public void setAuction_id(String auction_id) {
        this.auction_id = auction_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getStart_bid() {
        return start_bid;
    }
    public void setStart_bid(double start_bid) {
        this.start_bid = start_bid;
    }
    public double getQuality_available() {
        return quality_available;
    }
    public void setQuality_available(double quality_available) {
        this.quality_available = quality_available;
    }
    public String getDescripton() {
        return descripton;
    }
    public void setDescripton(String descripton) {
        this.descripton = descripton;
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
