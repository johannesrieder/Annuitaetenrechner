package com.example.annuittenrechner;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Annuitaetsparameter {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String annuität;
    private final String darlehenssumme;
    private final String zinssatz;
    private final String laufzeit;
    private final String kommentar;

    public Annuitaetsparameter(String annuität, String darlehenssumme, String zinssatz, String laufzeit, String kommentar) {
        this.annuität = annuität;
        this.darlehenssumme = darlehenssumme;
        this.zinssatz = zinssatz;
        this.laufzeit = laufzeit;
        this.kommentar = kommentar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnuität() {
        return annuität;
    }

    public String getDarlehenssumme() {
        return darlehenssumme;
    }

    public String getZinssatz() {
        return zinssatz;
    }

    public String getLaufzeit() {
        return laufzeit;
    }

    public String getKommentar() {
        return kommentar;
    }

}

