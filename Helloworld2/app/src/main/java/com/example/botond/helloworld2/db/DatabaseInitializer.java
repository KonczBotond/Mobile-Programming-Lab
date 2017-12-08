package com.example.botond.helloworld2.db;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by Botond on 05.12.2017.
 */


public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static Quote addQuote(final AppDatabase db, Quote quote) {
        db.quoteDao().insertQuotes(quote);
        return quote;
    }

    private static void populateWithTestData(AppDatabase db) {
        Quote quote = new Quote();
        quote.setQuote("Ajay");
        quote.setAuthor("Saini");
        addQuote(db, quote);

        List<Quote> quoteList = db.quoteDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Rows Count: " + quoteList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}