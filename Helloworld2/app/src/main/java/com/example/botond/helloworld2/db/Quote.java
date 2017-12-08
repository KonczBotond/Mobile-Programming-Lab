package com.example.botond.helloworld2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Botond on 05.12.2017.
 */
@Entity(tableName = "quotes")
public class Quote {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "quote")
    private String quote;

    @ColumnInfo(name = "author")
    private String author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString(){
        return quote+" by "+author;
    }
    // Getters and setters are ignored for brevity,
    // but they're required for Room to work.
}
