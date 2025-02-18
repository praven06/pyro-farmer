package com.example.pyro.Services;

import com.example.pyro.Model.Crop;
import com.example.pyro.Model.Farmer;
import com.example.pyro.Repositories.FarmerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FarmerService {

    private final FarmerRepository farmerRepository;

    @Autowired
    public FarmerService(FarmerRepository farmerRepository) {
        this.farmerRepository = farmerRepository;
    }

    public Farmer saveFarmer(Farmer farmer) {
        return farmerRepository.save(farmer);
    }

    public Optional<Farmer> getFarmerById(String farmerId) {
        return farmerRepository.findById(farmerId);
    }

    public Farmer addCropToFarmer(String farmerId, Crop crop) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            List<Crop> crops = farmer.getCrops();
            crops.add(crop);
            farmer.setCrops(crops);
            return farmerRepository.save(farmer);
        }
        return null;
    }
    public Farmer loginFarmer( Farmer farmer){ 
        System.out.println(farmer + "idhu dhaan farmer");; 

        return farmerRepository.findByUsernameAndPassword(farmer.getUsername(),farmer.getPassword());
    }
    public Farmer updateCropForFarmer(String farmerId, String cropId, Crop updatedCrop) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            List<Crop> crops = farmer.getCrops();

            List<Crop> updatedCrops = crops.stream()
                    .map(crop -> crop.getId().equals(cropId) ? updatedCrop : crop)
                    .collect(Collectors.toList());

            farmer.setCrops(updatedCrops);
            return farmerRepository.save(farmer);
        }
        return null;
    }

    public Farmer deleteCropFromFarmer(String farmerId, String cropId) {
        Optional<Farmer> farmerOptional = farmerRepository.findById(farmerId);
        if (farmerOptional.isPresent()) {
            Farmer farmer = farmerOptional.get();
            List<Crop> crops = farmer.getCrops();

            List<Crop> updatedCrops = crops.stream()
                    .filter(crop -> !crop.getId().equals(cropId))
                    .collect(Collectors.toList());

            farmer.setCrops(updatedCrops);
            return farmerRepository.save(farmer);
        }
        return null;
    }
    public ResponseEntity<Object> deleteFarmer(String farmerId){
        try {
            
            farmerRepository.deleteById(farmerId);
            return ResponseEntity.ok().body("user deleted by rooban");
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
