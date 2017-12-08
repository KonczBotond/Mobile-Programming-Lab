package com.example.botond.helloworld2.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by Botond on 05.12.2017.
 */

@Database(version = 1, entities = {Quote.class})
public abstract class AppDatabase extends RoomDatabase{
    private static AppDatabase INSTANCE;

    // QuoteDao is a class annotated with @Dao.
    abstract public QuoteDao quoteDao();

    public static AppDatabase getAppDatabase(Context context){
        if(INSTANCE==null){
            INSTANCE= Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,"quote-database"
                    )
                    .allowMainThreadQueries()
                    .build();
        }

        return INSTANCE;
    }
    public static void destroyInstance() {
        INSTANCE = null;
    }
}