package com.example.annuittenrechner;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Annuitaet {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private final String annuitaet;


    public Annuitaet(String annuitaet) {
        this.annuitaet = annuitaet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnuitaet() {
        return annuitaet;
    }
}