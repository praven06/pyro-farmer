package com.example.pyro.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.pyro.Model.Crop;
import com.example.pyro.Repositories.CropRepository;

@Repository
public class CropService {
    @Autowired
    private CropRepository cropRepository;
    
   
    public Crop saveCrop(Crop crop) {
        return cropRepository.save(crop);
    }
    public List<Crop> getCropsByFarmerId(String farmerId) {
        return cropRepository.findByFarmer_Id(farmerId);
    }
    public Crop updateCrop(String cropId, Crop updatedCrop) {
        return cropRepository.findById(cropId).map(existingCrop -> {
            existingCrop.setName(updatedCrop.getName());
            existingCrop.setFarmer(updatedCrop.getFarmer());
            existingCrop.setCurrent_stage(updatedCrop.getCurrent_stage());
            existingCrop.setProgress(updatedCrop.getProgress());
            existingCrop.setTasks(updatedCrop.getTasks());
            existingCrop.setPlanner(updatedCrop.getPlanner());
            return cropRepository.save(existingCrop);
        }).orElse(null);
    }

    // Delete a Crop
    public boolean deleteCrop(String cropId) {
        if (cropRepository.existsById(cropId)) {
            cropRepository.deleteById(cropId);
            return true;
        }
        return false;
    }
}
