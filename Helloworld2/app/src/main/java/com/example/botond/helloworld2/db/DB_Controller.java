package com.example.botond.helloworld2.db;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Botond on 04.12.2017.
 */

public class DB_Controller{}/* extends SQLiteOpenHelper{
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "quotes.db", factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE QUOTES(ID INTEGER PRIMARY KEY AUTOINCREMENT,QUOTE TEXT,AUTHOR TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS QUOTES;");
        onCreate(sqLiteDatabase);
    }

    //public void insertQuote()
}
*/