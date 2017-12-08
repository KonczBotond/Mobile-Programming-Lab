package com.example.botond.helloworld2.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by Botond on 05.12.2017.
 */

@Dao
public interface QuoteDao {

    @Query("SELECT * FROM quotes")
    List<Quote> getAll();

    @Insert
    void insertQuotes(Quote... quotes);

    @Delete
    public void deleteQuotes(Quote... quotes);

    @Update
    public void updateQuotes(Quote... quotes);

    @Query("DELETE FROM quotes WHERE 1")
    void deleteAll();
}
