package com.example.pyro.Repositories;

import com.example.pyro.Model.Auctions;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuctionRepository extends MongoRepository<Auctions, String> {
    List<Auctions> findByFarmer_Id(String farmerId);
    Auctions findByAuctionId(String auctionId);
}
