package com.example.pyro.Controller;

import com.example.pyro.Model.Auctions;
import com.example.pyro.Services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
    public ResponseEntity<Auctions> createAuction(
            @RequestPart("data") String auctionJson,
            @RequestPart(value = "itemImage", required = false) MultipartFile file
    ) throws IOException {
        Auctions savedAuction = auctionService.saveAuction(auctionJson, file);
        return ResponseEntity.ok(savedAuction);
    }

    @GetMapping("/{auctionId}")
    public ResponseEntity<Auctions> getAuctionById(@PathVariable String auctionId) {
        Optional<Auctions> auction = auctionService.getAuctionById(auctionId);
        return auction.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Auctions>> getAllAuctions() {
        return ResponseEntity.ok(auctionService.getAllAuctions());
    }

    @GetMapping("/farmer/{farmerId}")
    public ResponseEntity<List<Auctions>> getAuctionsByFarmerId(@PathVariable String farmerId) {
        List<Auctions> auctions = auctionService.getAuctionsByFarmerId(farmerId);
        return !auctions.isEmpty() ? ResponseEntity.ok(auctions) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{auctionId}")
    public ResponseEntity<Auctions> updateAuction(@PathVariable String auctionId,
                                                  @RequestBody Auctions updatedAuction) {
        Auctions auction = auctionService.updateAuction(auctionId, updatedAuction);
        return auction != null ? ResponseEntity.ok(auction) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{auctionId}")
    public ResponseEntity<String> deleteAuction(@PathVariable String auctionId) {
        return auctionService.deleteAuction(auctionId)
                ? ResponseEntity.ok("Auction deleted successfully")
                : ResponseEntity.notFound().build();
    }
}
