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
public class Kilometrage {
    
    private int id;
    private String date;
    private String kilometrage;
    private String latitude;
    private String longitude;
    private String compteurKilométrage;
     private boolean confirmed;
    private boolean accepted;
    private boolean processed; 
    private String montant_recharge;
    private String motif_refus; 
    private int usr_id;

    public int getId() {
        return id;
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

    public String getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(String kilometrage) {
        this.kilometrage = kilometrage;
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

    public String getCompteurKilométrage() {
        return compteurKilométrage;
    }

    public void setCompteurKilométrage(String compteurKilométrage) {
        this.compteurKilométrage = compteurKilométrage;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    public String getMontant_recharge() {
        return montant_recharge;
    }

    public void setMontant_recharge(String montant_recharge) {
        this.montant_recharge = montant_recharge;
    }

    public String getMotif_refus() {
        return motif_refus;
    }

    public void setMotif_refus(String motif_refus) {
        this.motif_refus = motif_refus;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }
    
    
}
