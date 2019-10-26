package com.example.annuittenrechner;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AnnuitaetsparameterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Annuitaetsparameter annuitaetsparameter);

    @Query("SELECT * from Annuitaetsparameter")
    List<Annuitaetsparameter> getAll();

    @Delete
    void delete(Annuitaetsparameter annuitaetsparameter);

}
