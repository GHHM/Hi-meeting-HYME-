package org.androidtown.hyme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static java.sql.DriverManager.println;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "speechDB";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "speechTable";

    public static final String USER_ID = "user_id";
    public static final String CONFERENCE_ID = "conference_id";
    public static final String SPEECH_TYPE = "speech_type";
    public static final String SPEECH_CONTENT = "speech_content";

    public static final String TAG = "TAG";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        println("creating table [" + TABLE_NAME+ "].");

        //Drop table if exist
        String DROP_SQL = "drop table if exists "+ TABLE_NAME;
        exe_SQL(DROP_SQL, db, "DROP");

        //Create table
        String CREATE_SQL = "create table "+TABLE_NAME+ "("
                + USER_ID + " integer PRIMARY KEY autoincrement, "
                + CONFERENCE_ID + "integer autoincrement, "
                + SPEECH_TYPE + " text, "
                + SPEECH_CONTENT + " text)";
        exe_SQL(CREATE_SQL, db, "CREATE");

        //Insert records
        println("inserting records.");
        String INSERT_SQL = "insert into" + TABLE_NAME + "("
                +USER_ID+", "
                +CONFERENCE_ID+", "
                +SPEECH_TYPE+", "
                +SPEECH_CONTENT
                +") values (1, 100, 'Question', 'Can I go home?');" ;
        exe_SQL(INSERT_SQL,db, "INSERT");
    }

    private void exe_SQL(String sql, SQLiteDatabase db, String type){
        try{
            db.execSQL(sql);
        }catch(Exception ex){
            Log.e(TAG,"Exception in "+ type +"_SQL",ex);
        }
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        println("opened database ["+DATABASE_NAME+"].");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, "Upgrading database from version" +oldVersion + "to "+ newVersion+".");
    }
}
