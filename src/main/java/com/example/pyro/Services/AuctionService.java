package com.example.pyro.Services;

import com.example.pyro.Model.Auctions;
import com.example.pyro.Repositories.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;

    @Autowired
    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
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
            existingAuction.setName(updatedAuction.getName());
            existingAuction.setStart_bid(updatedAuction.getStart_bid());
            existingAuction.setQuality_available(updatedAuction.getQuality_available());
            existingAuction.setDescripton(updatedAuction.getDescripton());
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
