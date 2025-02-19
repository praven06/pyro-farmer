package com.example.pyro.Services;

import com.example.pyro.Model.Shares;
import com.example.pyro.Model.User;
import com.example.pyro.Repositories.FarmerRepository;
import com.example.pyro.Repositories.SharesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SharesService {

    @Autowired
    private SharesRepository sharesRepository;
    private FarmerRepository farmerRepository;

    public List<Shares> getShares() {
        List<Shares> sharesList = sharesRepository.findAll();
        for (Shares share : sharesList) {
            if (share.getCreater() != null) {
                share.setCreater(farmerRepository.findById(share.getCreater().getId()).orElse(null));
            }
        }
        return sharesList;
    }    
  
    public Shares createShare(Shares share) {
        return sharesRepository.save(share);
    }

    public List<Shares> getSharesByFarmer(String id) {
        return sharesRepository.findByCreater_Id(id);
    }

    public List<Shares> getSharesByUser(String userId) {
        return sharesRepository.findByShareholders_Id(userId);
    }

    public Optional<Shares> getShareById(String shareId) {
        return sharesRepository.findById(shareId);
    }
    public Shares updateShare(String shareId, Shares updatedShare) {
        Optional<Shares> existingShareOpt = sharesRepository.findById(shareId);

        if (!existingShareOpt.isPresent()) {
            throw new RuntimeException("Share not found.");
        }

        Shares existingShare = existingShareOpt.get();

    
        if (updatedShare.getTotal_amount() > 0) {
            existingShare.setTotal_amount(updatedShare.getTotal_amount());
        }
        if (updatedShare.getTotal_split() > 0) {
            existingShare.setTotal_split(updatedShare.getTotal_split());
        }
        if (updatedShare.getNo_of_shares() > 0) {
            existingShare.setNo_of_shares(updatedShare.getNo_of_shares());
        }
        if (updatedShare.getAddress() != null) {
            existingShare.setAddress(updatedShare.getAddress());
        }
        if (updatedShare.getExpected_yeild() != null) {
            existingShare.setExpected_yeild(updatedShare.getExpected_yeild());
        }
        if (updatedShare.getArea() != null) {
            existingShare.setArea(updatedShare.getArea());
        }
        if (updatedShare.getStatus() != null) {
            existingShare.setStatus(updatedShare.getStatus());
        }

        return sharesRepository.save(existingShare);
    }

    public Shares addShareholder(String shareId, User user) {
        Optional<Shares> optionalShare = sharesRepository.findById(shareId);
        if (optionalShare.isPresent()) {
            Shares share = optionalShare.get();
            share.getShareholders().add(user);
            return sharesRepository.save(share);
        }
        return null;
    }

    public void deleteShare(String shareId) {
        sharesRepository.deleteById(shareId);
    }
    
    
}
