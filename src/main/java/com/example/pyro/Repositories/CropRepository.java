package com.example.pyro.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pyro.Model.Crop;

@Repository
public interface CropRepository extends MongoRepository<Crop, String> {
    List<Crop> findByFarmer_Id(String farmerId);

}
