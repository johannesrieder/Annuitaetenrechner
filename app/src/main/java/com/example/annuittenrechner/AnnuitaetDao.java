package com.example.annuittenrechner;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnnuitaetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Annuitaet annuitaet);

    @Query("SELECT * from annuitaet")
    public List<Annuitaet> getAll();

    @Delete
    public void delete(Annuitaet annuitaet);

}
