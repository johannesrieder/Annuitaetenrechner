package com.example.annuittenrechner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Annuitaetsparameter.class}, version = 1, exportSchema = false)
public abstract class AnnuitaetsparameterRoomDatabase extends RoomDatabase {
    public abstract AnnuitaetsparameterDao annuitaetsparameterDao();
    private static AnnuitaetsparameterRoomDatabase INSTANCE;
    static AnnuitaetsparameterRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AnnuitaetsparameterRoomDatabase.class,"annuitaetsparameter_database")
                    .build();
        }
        return INSTANCE;
    }

}