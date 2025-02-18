package com.example.pyro.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pyro.Model.Farmer;

@Repository
public interface FarmerRepository extends MongoRepository<Farmer,String> {
    Farmer findByUsernameAndPassword(String username,String password);
} 
