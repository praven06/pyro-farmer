package com.example.pyro.Controller;

import com.example.pyro.DTO.BuyShareRequest;
import com.example.pyro.Model.Shares;
import com.example.pyro.Model.User;
import com.example.pyro.Services.SharesService;
import com.example.pyro.Services.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private final SharesService sharesService;
    private final UserService userService;

    public UserController(UserService userService,SharesService sharesService) {
        this.userService = userService;
        this.sharesService = sharesService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> userLogin(@RequestParam String email, @RequestParam String password) {
        boolean isAuthenticated = userService.userLogin(email, password);
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }
    

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        Optional<User> user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User updatedUser) {
        User user = userService.updateUser(userId, updatedUser);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
        return userService.deleteUser(userId) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    @PostMapping("/buy")
    public ResponseEntity<Shares> buyShare(@RequestBody BuyShareRequest request) {
        Optional<User> user = userService.getUserById(request.getUser());
        Optional<Shares> share = sharesService.getShareById(request.getShare());

        if (user.isEmpty() || share.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Shares updatedShare = userService.buyShare(user.get(), share.get(), request.getNumberOfShares());
        return ResponseEntity.ok(updatedShare);
    }
}
