package com.example.pyro.Services;

import com.example.pyro.Model.Shares;
import com.example.pyro.Model.User;
import com.example.pyro.Repositories.SharesRepository;
import com.example.pyro.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private SharesRepository sharesRepository;
    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean userLogin(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user.getPassword().equals(password); 
        }
        return false;
    }   

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(String userId, User updatedUser) {
        return userRepository.findById(userId)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setEmail(updatedUser.getEmail());
                    user.setPhone(updatedUser.getPhone());
                    user.setAddress(updatedUser.getAddress());
                    user.setWalletBalance(updatedUser.getWalletBalance());
                    user.setPurchasedShares(updatedUser.getPurchasedShares());
                    user.setPurchasedProducts(updatedUser.getPurchasedProducts());
                    return userRepository.save(user);
                }).orElse(null);
    }

    public boolean deleteUser(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
   
    public Shares buyShare(User user, Shares share, int numberOfShares) {
    if (user == null || share == null) {
        throw new RuntimeException("Invalid user or share.");
    }

    if (user.getWalletBalance() < (share.getCost_of_share() * numberOfShares)) {
        throw new RuntimeException("Insufficient balance to buy shares.");
    }
    if (share.getShareholders() == null) {
        share.setShareholders(new ArrayList<>());
    }

    user.setWalletBalance(user.getWalletBalance() - (share.getCost_of_share() * numberOfShares));
    user.getPurchasedShares().add(share);
    share.setNo_of_shares(share.getNo_of_shares() - numberOfShares);
    share.getShareholders().add(user);

    userRepository.save(user);
    sharesRepository.save(share);

    return share;
}


}
