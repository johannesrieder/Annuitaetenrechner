package com.example.annuittenrechner;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Annuitaet.class}, version = 1, exportSchema = false)
public abstract class AnnuitaetRoomDatabase extends RoomDatabase {
    public abstract AnnuitaetDao annuitaetDao();
    private static AnnuitaetRoomDatabase INSTANCE;
    static AnnuitaetRoomDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AnnuitaetRoomDatabase.class,"annuitaet_database")
            .build();
        }
        return INSTANCE;
    }

}
