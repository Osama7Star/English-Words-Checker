package com.Alnajim.osama.StarLanguage.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.Alnajim.osama.StarLanguage.sqliteDatabase;


/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelperWords extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "QUESTIONSID_DB";


    public DatabaseHelperWords(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(sqliteDatabase.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + sqliteDatabase.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }
}