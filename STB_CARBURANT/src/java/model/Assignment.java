/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author akhaldi
 */
public class Assignment {
    private int id;
    private User user;
    private Vehicle vehicle;
    private String affectationDate;
    private boolean active;
    private String affectationEndDate;   
    
    private String firstKm;
    private String lastKm;
    private String averageConsumption;

    public String getAffectationEndDate() {
        return affectationEndDate;
    }

    public void setAffectationEndDate(String affectationEndDate) {
        this.affectationEndDate = affectationEndDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getAffectationDate() {
        return affectationDate;
    }

    public void setAffectationDate(String affectationDate) {
        this.affectationDate = affectationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstKm() {
        return firstKm;
    }

    public void setFirstKm(String firstKm) {
        this.firstKm = firstKm;
    }

    public String getLastKm() {
        return lastKm;
    }

    public void setLastKm(String lastKm) {
        this.lastKm = lastKm;
    }

    public String getAverageConsumption() {
        return averageConsumption;
    }

    public void setAverageConsumption(String averageConsumption) {
        this.averageConsumption = averageConsumption;
    }
    
    
    
}
