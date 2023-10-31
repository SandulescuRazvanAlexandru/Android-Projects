package com.example.your_personal_agenda_app.util;

import java.io.Serializable;

public class Activitate implements Serializable {

    private String tipActivitate;
    private int oreActivitate;
    private int minuteActivitate;
    private double caloriiArseActivitate;
    private int idPersoanaActivitate;

    public Activitate(String tipActivitate, int oreActivitate, int minuteActivitate, double caloriiArseActivitate, int idPersoanaActivitate) {
        this.tipActivitate = tipActivitate;
        this.oreActivitate = oreActivitate;
        this.minuteActivitate = minuteActivitate;
        this.caloriiArseActivitate = caloriiArseActivitate;
        this.idPersoanaActivitate = idPersoanaActivitate;
    }

    public String getTipActivitate() {
        return tipActivitate;
    }

    public void setTipActivitate(String tipActivitate) {
        this.tipActivitate = tipActivitate;
    }

    public int getOreActivitate() {
        return oreActivitate;
    }

    public void setOreActivitate(int oreActivitate) {
        this.oreActivitate = oreActivitate;
    }

    public int getMinuteActivitate() {
        return minuteActivitate;
    }

    public void setMinuteActivitate(int minuteActivitate) {
        this.minuteActivitate = minuteActivitate;
    }

    public double getCaloriiArseActivitate() {
        return caloriiArseActivitate;
    }

    public void setCaloriiArseActivitate(double caloriiArseActivitate) {
        this.caloriiArseActivitate = caloriiArseActivitate;
    }

    public int getIdPersoanaActivitate() {
        return idPersoanaActivitate;
    }

    public void setIdPersoanaActivitate(int idPersoanaActivitate) {
        this.idPersoanaActivitate = idPersoanaActivitate;
    }

    @Override
    public String toString() {
        return "Activitate{" +
                "tipActivitate='" + tipActivitate + '\'' +
                ", oreActivitate=" + oreActivitate +
                ", minuteActivitate=" + minuteActivitate +
                ", caloriiArseActivitate=" + caloriiArseActivitate +
                ", idPersoanaActivitate=" + idPersoanaActivitate +
                '}';
    }
}
