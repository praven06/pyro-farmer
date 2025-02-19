package com.example.pyro.Services;

import com.example.pyro.Model.Auctions;
import com.example.pyro.Model.Bid;
import com.example.pyro.Repositories.AuctionRepository;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuctionRoomService {
    private final AuctionRepository auctionRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Bid> highestBids = new ConcurrentHashMap<>();
    private final Map<String, Integer> activeUsers = new ConcurrentHashMap<>();
    private final Map<String, Auctions> auctionRooms = new ConcurrentHashMap<>();

    public AuctionRoomService(SimpMessagingTemplate messagingTemplate, AuctionRepository auctionRepository) {
        this.messagingTemplate = messagingTemplate;
        this.auctionRepository = auctionRepository;
    }

   

    public void processBid(String auctionId, Bid bid) {
    
        if (bid.getBidAmount() <= 0) {
            throw new IllegalArgumentException("Bid amount must be greater than zero");
        }
        Auctions old = auctionRepository.findByAuctionId(auctionId);
        if (old == null) {
            throw new RuntimeException("Auction not found with ID: " + auctionId);
        }
        old.setTop_bid(bid.getBidAmount());
        auctionRepository.save(old);
    
        System.out.println("Processing bid for auction " + auctionId + ": " + bid.getBidderName() + " bid " + bid.getBidAmount());
    }
    
    
  
}
