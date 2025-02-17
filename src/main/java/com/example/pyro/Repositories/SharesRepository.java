package com.example.pyro.Repositories;

import com.example.pyro.Model.Shares;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SharesRepository extends MongoRepository<Shares, String> {
    List<Shares> findByCreater_Id(String id);

    List<Shares> findByShareholders_Id(String id);
}
