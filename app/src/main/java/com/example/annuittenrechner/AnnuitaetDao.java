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
    void insert(Annuitaet annuitaet);

    @Query("SELECT * from annuitaet")
    List<Annuitaet> getAll();

    @Delete
    void delete(Annuitaet annuitaet);

}
