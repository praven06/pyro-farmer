package com.example.pyro.Controller;

import com.example.pyro.Model.Bid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/auction")
public class LongPollingController {
    private final BlockingQueue<Bid> bidQueue = new LinkedBlockingQueue<>();

    @GetMapping("/poll")
    public ResponseEntity<Bid> pollBid() {
        
            Bid bid = bidQueue.poll();
            return bid != null 
                ? ResponseEntity.ok(bid)
                : ResponseEntity.noContent().build();
    
    }

    @PostMapping("/bid")
    public ResponseEntity<Bid> submitBid(@RequestBody Bid bid) {
        bidQueue.offer(bid);
        return ResponseEntity.ok(bid);
    }
}