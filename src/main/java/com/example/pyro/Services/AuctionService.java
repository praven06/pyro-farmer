package com.example.pyro.Services;

import com.example.pyro.Model.Auctions;
import com.example.pyro.Repositories.AuctionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import java.io.File;

import java.util.UUID;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }
     public Auctions saveAuction(String auctionJson, MultipartFile file) throws IOException {
        Auctions auction = objectMapper.readValue(auctionJson, Auctions.class);

        // Handle Image Upload
        if (file != null && !file.isEmpty()) {
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            String filePath = "uploads/" + fileName;
            
            // Ensure directory exists
            File uploadDir = new File("uploads");
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            
            // Save file locally
            file.transferTo(Paths.get(filePath));

            // Set Image URL
            auction.setAuction_image(filePath);
        }

        return auctionRepository.save(auction);
    }

    public Auctions saveAuction(Auctions auction) {
        return auctionRepository.save(auction);
    }

    public Optional<Auctions> getAuctionById(String auctionId) {
        return auctionRepository.findById(auctionId);
    }

    public List<Auctions> getAllAuctions() {
        return auctionRepository.findAll();
    }

    public List<Auctions> getAuctionsByFarmerId(String farmerId) {
        return auctionRepository.findByFarmer_Id(farmerId);
    }

    public Auctions updateAuction(String auctionId, Auctions updatedAuction) {
        return auctionRepository.findById(auctionId).map(existingAuction -> {
            existingAuction.setAuction_name(updatedAuction.getAuction_name());
            existingAuction.setStart_bid(updatedAuction.getStart_bid());
            existingAuction.setQuality_available(updatedAuction.getQuantity_available());
            existingAuction.setdescription(updatedAuction.getdescription());
            existingAuction.setProperties(updatedAuction.getProperties());
            existingAuction.setAuction_image(updatedAuction.getAuction_image());
            existingAuction.setFarmer(updatedAuction.getFarmer());
            return auctionRepository.save(existingAuction);
        }).orElse(null);
    }

    public boolean deleteAuction(String auctionId) {
        if (auctionRepository.existsById(auctionId)) {
            auctionRepository.deleteById(auctionId);
            return true;
        }
        return false;
    }
}
