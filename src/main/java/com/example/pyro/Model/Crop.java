package com.example.pyro.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("crops")
public class Crop {
    
    @Id
    private String id;

    private String name;
    @DBRef
    private Farmer farmer;
    private String current_stage;
    private String progress;
    private List<Object> tasks;
    private List<Object> planner;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Farmer getFarmer() {
        return farmer;
    }
    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }
    public String getCurrent_stage() {
        return current_stage;
    }
    public void setCurrent_stage(String current_stage) {
        this.current_stage = current_stage;
    }
    public String getProgress() {
        return progress;
    }
    public void setProgress(String progress) {
        this.progress = progress;
    }
    public List<Object> getTasks() {
        return tasks;
    }
    public void setTasks(List<Object> tasks) {
        this.tasks = tasks;
    }
    public List<Object> getPlanner() {
        return planner;
    }
    public void setPlanner(List<Object> planner) {
        this.planner = planner;
    }

    

}
