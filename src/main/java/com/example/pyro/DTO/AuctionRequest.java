package com.example.pyro.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AuctionRequest {
    private String userId;
    private String auction_name;
    private double starting_bid;
    private int quantity;
    private String description;
    private List<String> properties;
}
