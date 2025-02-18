package com.example.pyro.Controller;

import com.example.pyro.Model.Crop;
import com.example.pyro.Model.Farmer;
import com.example.pyro.Services.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

    private final FarmerService farmerService;

    @Autowired
    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @PostMapping("/update")
    public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.saveFarmer(farmer));
    }

    @PostMapping("/create")
    public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer farmer) {
        return ResponseEntity.ok(farmerService.saveFarmer(farmer));
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<Farmer> getFarmerById(@PathVariable String farmerId) {
        Optional<Farmer> farmer = farmerService.getFarmerById(farmerId);
        return farmer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{farmerId}/crops")
    public ResponseEntity<Farmer> addCropToFarmer(@PathVariable String farmerId, @RequestBody Crop crop) {
        Farmer updatedFarmer = farmerService.addCropToFarmer(farmerId, crop);
        return updatedFarmer != null ? ResponseEntity.ok(updatedFarmer) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{farmerId}/crops/{cropId}")
    public ResponseEntity<Farmer> updateCropForFarmer(@PathVariable String farmerId, @PathVariable String cropId,
            @RequestBody Crop updatedCrop) {
        Farmer updatedFarmer = farmerService.updateCropForFarmer(farmerId, cropId, updatedCrop);
        return updatedFarmer != null ? ResponseEntity.ok(updatedFarmer) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{farmerId}/crops/{cropId}")
    public ResponseEntity<Farmer> deleteCropFromFarmer(@PathVariable String farmerId, @PathVariable String cropId) {
        Farmer updatedFarmer = farmerService.deleteCropFromFarmer(farmerId, cropId);
        return updatedFarmer != null ? ResponseEntity.ok(updatedFarmer) : ResponseEntity.notFound().build();
    }
    @PostMapping("/login")
    public ResponseEntity<Farmer> loginFarmer(@RequestBody Farmer farmer) {
        Farmer loggedFarmer = farmerService.loginFarmer(farmer);
        return loggedFarmer != null ? ResponseEntity.ok(loggedFarmer) : ResponseEntity.notFound().build();  
    }
    @DeleteMapping("/{farmerId}")
    public ResponseEntity<Object> deleteFarmer(@PathVariable String farmerId){
        return farmerService.deleteFarmer(farmerId);
    }
}
