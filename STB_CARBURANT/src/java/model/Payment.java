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
public class Payment {
    private int id;
    private String date;
    private String distance;
    private String unitPrice;
    private String cost;
    private String latitude;
    private String longitude;
    private String paymentType;
    private String comment;
    
    private String bonPaiement;
    private String compteurKilométrage;

    public int getId() {
        return id;
    }

    public String getBonPaiement() {
        return bonPaiement;
    }

    public void setBonPaiement(String bonPaiement) {
        this.bonPaiement = bonPaiement;
    }

    public String getCompteurKilométrage() {
        return compteurKilométrage;
    }

    public void setCompteurKilométrage(String compteurKilométrage) {
        this.compteurKilométrage = compteurKilométrage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
