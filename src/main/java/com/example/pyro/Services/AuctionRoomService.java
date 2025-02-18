package com.example.pyro.Services;

import com.example.pyro.Model.Auctions;
import com.example.pyro.Model.Bid;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuctionRoomService {

    private final SimpMessagingTemplate messagingTemplate;
    private final Map<String, Bid> highestBids = new ConcurrentHashMap<>();

    public AuctionRoomService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void placeBid(String auctionId, Bid newBid) {
        Bid currentHighest = highestBids.getOrDefault(auctionId, new Bid("", 0.0));

        if (newBid.getBidAmount() > currentHighest.getBidAmount()) {
            highestBids.put(auctionId, newBid);
            messagingTemplate.convertAndSend("/topic/auction/" + auctionId, newBid);
        }
    }

    public Bid getHighestBid(String auctionId) {
        return highestBids.getOrDefault(auctionId, new Bid("", 0.0));
    }

    public void endAuction(String auctionId) {
        highestBids.remove(auctionId);
    }
}
