package com.example.pyro.Repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.pyro.Model.Products;

@Repository
public interface ProductsRepository  extends MongoRepository<Products,String>{
    List<Products> findByFarmer_Id(String farmerId);

    List<Products> findByBuyer_Id(String id);
}
