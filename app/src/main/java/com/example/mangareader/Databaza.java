package com.example.mangareader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databaza  extends SQLiteOpenHelper {
    public  Databaza(Context context) {
        super(context, "FIEK", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE SIGNUP(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME VARCHAR(15),EMAIL TEXT,PASSWORD TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("drop table if exists SIGNUP");
    }
}

