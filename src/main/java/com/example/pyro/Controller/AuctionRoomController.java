package com.example.pyro.Controller;

import com.example.pyro.Model.Bid;
import com.example.pyro.Services.AuctionRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@Controller
public class AuctionRoomController {
    
    private final AuctionRoomService auctionRoomService;
    
    @Autowired
    public AuctionRoomController(AuctionRoomService auctionRoomService) {
        this.auctionRoomService = auctionRoomService;
    }

    @MessageMapping("/join/{auctionId}")
    @SendTo("/all/responses/{auctionId}")
    public Bid sendMessage(@DestinationVariable String auctionId, @Payload Bid bid) {
        try {
            // Process the bid (e.g., validate, store in DB, etc.)
            auctionRoomService.processBid(auctionId, bid);
            return bid;
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return null; // Handle the error appropriately
        }
    }
}

