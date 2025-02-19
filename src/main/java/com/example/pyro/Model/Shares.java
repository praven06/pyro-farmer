package com.example.pyro.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document
public class Shares {
    
    @Id
    private String share_id;
    @DBRef
    private Farmer creater;
    private double total_amount;
    private double total_split;
    private int no_of_shares;
    // private String farmer_id;
    @DBRef
    // @JsonIgnore
    private List<User> shareholders = new ArrayList<>(); 
    // private Integer cost_of_share;
    public Farmer getCreater() {
        return creater;
    }

    public void setCreater(Farmer creater) {
        this.creater = creater;
    }
    private double cost_of_share;
    private String address;
    private String expected_yeild;
    private String area;
    private String status;
    
    public List<User> getShareholders() {
        if (shareholders == null) {
            shareholders = new ArrayList<>(); 
        }
        return shareholders;
    }

    public void setShareholders(List<User> shareholders) {
        this.shareholders = shareholders;
    }
    public String getShare_id() {
        return share_id;
    }
    public void setShare_id(String share_id) {
        this.share_id = share_id;
    }
  
    public double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    public double getTotal_split() {
        return total_split;
    }
    public void setTotal_split(double total_split) {
        this.total_split = total_split;
    }
    public int getNo_of_shares() {
        return no_of_shares;
    }
    public void setNo_of_shares(int no_of_shares) {
        this.no_of_shares = no_of_shares;
    }
    
    public double getCost_of_share() {
        return cost_of_share;
    }
    public void setCost_of_share(double cost_of_share) {
        this.cost_of_share = cost_of_share;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getExpected_yeild() {
        return expected_yeild;
    }
    public void setExpected_yeild(String expected_yeild) {
        this.expected_yeild = expected_yeild;
    }
    public String getArea() {
        return area;
    }
    public void setArea(String area) {
        this.area = area;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    
    
}
