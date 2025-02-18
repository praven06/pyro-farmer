package com.example.pyro.Controller;

import com.example.pyro.Model.Bid;
import com.example.pyro.Services.AuctionRoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auction-room")
public class AuctionRoomController {

    private final AuctionRoomService auctionRoomService;

    public AuctionRoomController(AuctionRoomService auctionRoomService) {
        this.auctionRoomService = auctionRoomService;
    }

    @MessageMapping("/bid/{auctionId}")
    public void placeBid(@PathVariable String auctionId, Bid bid) {
        auctionRoomService.placeBid(auctionId, bid);
    }

    @GetMapping("/{auctionId}/highest-bid")
    public Bid getHighestBid(@PathVariable String auctionId) {
        return auctionRoomService.getHighestBid(auctionId);
    }

    @PostMapping("/{auctionId}/end")
    public void endAuction(@PathVariable String auctionId) {
        auctionRoomService.endAuction(auctionId);
    }
}
