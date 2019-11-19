package com.Alnajim.osama.StarLanguage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelperWords";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myWords";

    // Table Names
    private static final String TABLE_ENTERED_WORDS = "enteredwords";
    private static final String TABLE_SCORE = "score";


    // myWords Table - column nmaes
    private static final String KEY_MYWORDS_ID = "wordId";
    private static final String KEY_WORD  = "word";
    private static final String KEY_DATE  = "date";


    // score Table - column nmaes
    private static final String KEY_SCORE_ID   = "scoreId";
    private static final String KEY_SCORE = "score";









    // Table Create Statements

    // ENTEREDWORD	table create statement
    private static final String CREATE_TABLE_ENTERED_WORDS = "create table enteredwords (id integer PRIMARY key , word text  , date text)";
    private static final String CREATE_TABLE_SCORE =         "create table score        (id integer PRIMARY key , score text , date text)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_ENTERED_WORDS);
        db.execSQL(CREATE_TABLE_SCORE);




    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTERED_WORDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);



        // create new tables
        onCreate(db);
    }

    public void insertDeleget(){
        SQLiteDatabase db = this.getWritableDatabase();

    }

    public void insertWord(String word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_WORD,word);
        values.put(KEY_DATE,getDate());
         long todo_id = db.insert(TABLE_ENTERED_WORDS, null, values);

    }

    public void insertScore(int score) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(KEY_DATE,getDate());
        values.put(KEY_SCORE,score);
        long todo_id = db.insert(TABLE_SCORE, null, values);

    }


    String getDate()
    {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);


        String date= day+"/"+month+"/"+year;
        return date ;
    }

    public List<String> getWords()
    {

        List<String> todos = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM " + TABLE_ENTERED_WORDS;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();

        Log.i("number of Row ",count+" ");


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {


                c.getString((c.getColumnIndex(KEY_WORD)));


                todos.add(c.getString((c.getColumnIndex(KEY_WORD))));


            } while (c.moveToNext());
        }

        return todos;
    }



    public int getScore () {
        String selectQuery = "SELECT  * FROM " + TABLE_SCORE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {


                return c.getInt((c.getColumnIndex(KEY_SCORE)));


            } while (c.moveToNext());
        }
return    c.getInt((c.getColumnIndex(KEY_SCORE)));
    }


    public    void deleteWords()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ TABLE_ENTERED_WORDS);

    }

    public int getWordsCount (  )
    {
        String selectQuery = "SELECT  * FROM " + TABLE_ENTERED_WORDS;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();
        return count ;
    }

}
