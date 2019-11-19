package com.Alnajim.osama.StarLanguage;

public class sqliteDatabase {

    public static final String TABLE_NAME = "QuestionsIds";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTIONS_ID= "QUESTIONSID";


    private  int  id ;
    private  String questionId;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_QUESTIONS_ID + " TEXT"
                    + ")";

    public sqliteDatabase() {
    }
    public sqliteDatabase(int id , String questionId)
    {
        this.id = id ;
        this.questionId = questionId ;
    }

    public int  getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }
}
