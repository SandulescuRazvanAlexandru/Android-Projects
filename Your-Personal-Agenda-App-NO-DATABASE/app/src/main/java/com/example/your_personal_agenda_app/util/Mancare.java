package com.example.your_personal_agenda_app.util;

import java.io.Serializable;

public class Mancare implements Serializable {

        private String descriereMancare;
        private FelMancare felMancare;
        private double gramajMancare;
        private double proteineMancare;
        private double carbohidratiMancare;
        private double grasimiMancare;
        private double caloriiMancare;
        private int idPersoanaMancare;

        public Mancare(String descriereMancare, FelMancare felMancare, double gramajMancare, double proteineMancare, double carbohidratiMancare, double grasimiMancare, double caloriiMancare, int idPersoanaMancare) {
                this.descriereMancare = descriereMancare;
                this.felMancare = felMancare;
                this.gramajMancare = gramajMancare;
                this.proteineMancare = proteineMancare;
                this.carbohidratiMancare = carbohidratiMancare;
                this.grasimiMancare = grasimiMancare;
                this.caloriiMancare = caloriiMancare;
                this.idPersoanaMancare = idPersoanaMancare;
        }

        public String getDescriereMancare() {
                return descriereMancare;
        }

        public void setDescriereMancare(String descriereMancare) {
                this.descriereMancare = descriereMancare;
        }

        public FelMancare getFelMancare() {
                return felMancare;
        }

        public void setFelMancare(FelMancare felMancare) {
                this.felMancare = felMancare;
        }

        public double getGramajMancare() {
                return gramajMancare;
        }

        public void setGramajMancare(double gramajMancare) {
                this.gramajMancare = gramajMancare;
        }

        public double getProteineMancare() {
                return proteineMancare;
        }

        public void setProteineMancare(double proteineMancare) {
                this.proteineMancare = proteineMancare;
        }

        public double getCarbohidratiMancare() {
                return carbohidratiMancare;
        }

        public void setCarbohidratiMancare(double carbohidratiMancare) {
                this.carbohidratiMancare = carbohidratiMancare;
        }

        public double getGrasimiMancare() {
                return grasimiMancare;
        }

        public void setGrasimiMancare(double grasimiMancare) {
                this.grasimiMancare = grasimiMancare;
        }

        public double getCaloriiMancare() {
                return caloriiMancare;
        }

        public void setCaloriiMancare(double caloriiMancare) {
                this.caloriiMancare = caloriiMancare;
        }

        public int getIdPersoanaMancare() {
                return idPersoanaMancare;
        }

        public void setIdPersoanaMancare(int idPersoanaMancare) {
                this.idPersoanaMancare = idPersoanaMancare;
        }

        @Override
        public String toString() {
                return "Mancare{" +
                        "descriereMancare='" + descriereMancare + '\'' +
                        ", felMancare=" + felMancare +
                        ", gramajMancare=" + gramajMancare +
                        ", proteineMancare=" + proteineMancare +
                        ", carbohidratiMancare=" + carbohidratiMancare +
                        ", grasimiMancare=" + grasimiMancare +
                        ", caloriiMancare=" + caloriiMancare +
                        ", idPersoanaMancare=" + idPersoanaMancare +
                        '}';
        }
}
