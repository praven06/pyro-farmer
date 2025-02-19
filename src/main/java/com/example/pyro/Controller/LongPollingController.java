package com.example.pyro.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.concurrent.*;

@RestController
public class LongPollingController {

    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    @GetMapping("/poll")
    public ResponseEntity<String> poll() throws InterruptedException {
        String message = messageQueue.poll(); 
        if(message != null)
        return ResponseEntity.ok( message);
        else return ResponseEntity.noContent().build();
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        messageQueue.offer(message);
        return ResponseEntity.ok("Message sent!");
    }
}