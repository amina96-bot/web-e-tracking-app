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
public class Trajet {
    
    private int id;
    private String motif;  
    
    private String dateDepart;
    private String kilometrageDepart;
    private String lieuDepart;
    
    private String dateArrivee;
    private String kilometrageArrivee;
    private String lieuArrivee;
    
    private String note;
    private String type;   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(String dateDepart) {
        this.dateDepart = dateDepart;
    }

    public String getKilometrageDepart() {
        return kilometrageDepart;
    }

    public void setKilometrageDepart(String kilometrageDepart) {
        this.kilometrageDepart = kilometrageDepart;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getDateArrivee() {
        return dateArrivee;
    }

    public void setDateArrivee(String dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getKilometrageArrivee() {
        return kilometrageArrivee;
    }

    public void setKilometrageArrivee(String kilometrageArrivee) {
        this.kilometrageArrivee = kilometrageArrivee;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
}
