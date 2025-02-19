package com.example.pyro.Model;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document("farmers")
public class Farmer {
    
    @Id
    private String id;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private int age;
    private String mail;
    private String phone;
    private String land_ownership;
    private double total_land_area;
    private String location;
    private String soil_type;
    
    private List<String> previous_crops;
    private List<String> water_resources;
    private String irrigation;
    // @JsonIgnore
    private List<Crop> crop;
    private int no_of_water_sources;
    
    private String aadhar;
    private String land_ownership_card;
    private String soil_test;
    private String bank_passbook;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public List<Crop> getCrops() {
        return crop;
    }
    public void setCrops(List<Crop> crop) {
        this.crop = crop;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLand_ownership() {
        return land_ownership;
    }
    public void setLand_ownership(String land_ownership) {
        this.land_ownership = land_ownership;
    }
    public double getTotal_land_area() {
        return total_land_area;
    }
    public void setTotal_land_area(double total_land_area) {
        this.total_land_area = total_land_area;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getSoil_type() {
        return soil_type;
    }
    public void setSoil_type(String soil_type) {
        this.soil_type = soil_type;
    }
    public List<String> getPrevious_crops() {
        return previous_crops;
    }
    public void setPrevious_crops(List<String> previous_crops) {
        this.previous_crops = previous_crops;
    }
    public List<String> getWater_resources() {
        return water_resources;
    }
    public void setWater_resources(List<String> water_resources) {
        this.water_resources = water_resources;
    }
    public String getIrrigation() {
        return irrigation;
    }
    public void setIrrigation(String irrigation) {
        this.irrigation = irrigation;
    }
    
    public int getNo_of_water_sources() {
        return no_of_water_sources;
    }
    public void setNo_of_water_sources(int no_of_water_sources) {
        this.no_of_water_sources = no_of_water_sources;
    }
    public String getAadhar() {
        return aadhar;
    }
    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }
    public String getLand_ownership_card() {
        return land_ownership_card;
    }
    public void setLand_ownership_card(String land_ownership_card) {
        this.land_ownership_card = land_ownership_card;
    }
    public String getSoil_test() {
        return soil_test;
    }
    public void setSoil_test(String soil_test) {
        this.soil_test = soil_test;
    }
    public String getBank_passbook() {
        return bank_passbook;
    }
    public void setBank_passbook(String bank_passbook) {
        this.bank_passbook = bank_passbook;
    }
    
}
