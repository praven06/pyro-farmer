package com.example.pyro.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.pyro.Model.Bid;

import org.springframework.http.ResponseEntity;
import java.util.concurrent.*;

@RestController
public class LongPollingController {

    private final BlockingQueue<Bid> messageQueue = new LinkedBlockingQueue<>();

    @GetMapping("/poll")
    public ResponseEntity<Bid> poll() throws InterruptedException {
        Bid message = messageQueue.poll(); 
        if(message != null)
        return ResponseEntity.ok( message);
        else return ResponseEntity.noContent().build();
    }

    @PostMapping("/send")
    public ResponseEntity<Bid> sendMessage(@RequestBody Bid bid) {
        messageQueue.offer(bid);
        return ResponseEntity.ok(bid);
    }
}