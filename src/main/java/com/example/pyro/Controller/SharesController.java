package com.example.pyro.Controller;

import com.example.pyro.Model.Shares;
import com.example.pyro.Model.User;
import com.example.pyro.Services.SharesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shares")
public class SharesController {

    @Autowired
    private SharesService sharesService;

    @PostMapping("/create")
    public ResponseEntity<Shares> createShare(@RequestBody Shares share) {
        return ResponseEntity.ok(sharesService.createShare(share));
    }
    
    @PutMapping("/{shareId}")
    public ResponseEntity<Shares> updateShare(@PathVariable String shareId, @RequestBody Shares updatedShare) {
        Shares share = sharesService.updateShare(shareId, updatedShare);
        return ResponseEntity.ok(share);
    }

    @GetMapping("/{shareId}")
    public ResponseEntity<Shares> getShareById(@PathVariable String shareId) {
        Optional<Shares> share = sharesService.getShareById(shareId);
        return share.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Shares>> getSharesByFarmer(@PathVariable String farmerId) {
        return ResponseEntity.ok(sharesService.getSharesByFarmer(farmerId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Shares>> getSharesByUser(@PathVariable String userId) {
        return ResponseEntity.ok(sharesService.getSharesByUser(userId));
    }

    @PostMapping("/{shareId}/addShareholder")
    public ResponseEntity<Shares> addShareholder(@PathVariable String shareId, @RequestBody User user) {
        Shares updatedShare = sharesService.addShareholder(shareId, user);
        return updatedShare != null ? ResponseEntity.ok(updatedShare) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{shareId}")
    public ResponseEntity<Void> deleteShare(@PathVariable String shareId) {
        sharesService.deleteShare(shareId);
        return ResponseEntity.noContent().build();
    }
}
