package com.example.correctionefm;

import java.io.Serializable;

public class Entreprise implements Serializable {

    private int id;
    private String rs;
    private String adresse;
    private double capital;

    public Entreprise() {

    }

    public Entreprise(int id, String rs, String adresse, double capital) {
        this.id = id;
        this.rs = rs;
        this.adresse = adresse;
        this.capital = capital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }
}
