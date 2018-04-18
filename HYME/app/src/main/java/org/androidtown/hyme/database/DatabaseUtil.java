package org.androidtown.hyme.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;

import java.io.File;

public class DatabaseUtil {

    static SQLiteDatabase sqliteDB;

    public static final String USER_ID = "user_id";
    public static final String CONFERENCE_ID = "conderence_id";
    public static final String SPEECH_TYPE = "speech_type";
    public static final String SPEECH_CONTENT = "speech_content";


    public void createTable() {
        sqliteDB = init_database();
        init_tables();
    }

    private SQLiteDatabase init_database() {
        SQLiteDatabase db = null; // File file = getDatabasePath("contact.db") ;
        File file = new File(Environment.getDataDirectory(), "contact.db");
        System.out.println("PATH : " + file.toString());
        try {
            db = SQLiteDatabase.openOrCreateDatabase(file, null);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        if (db == null) {
            System.out.println("DB creation failed. " + file.getAbsolutePath());
        }
        return db;
    }

    private void init_tables() {

        if (sqliteDB != null) {
            String sqlCreateTbl = "CREATE TABLE IF NOT EXISTS CONTACT_T (" +
                    USER_ID + "INTEGER NOT NULL," +
                    CONFERENCE_ID + "TEXT," +
                    SPEECH_TYPE + "TEXT," +
                    SPEECH_CONTENT + "TEXT" + ")";

            System.out.println(sqlCreateTbl);

            sqliteDB.execSQL(sqlCreateTbl);
        }
    }

    //put value
    public static synchronized void put(Context context, String key, String value) {

        sqliteDB.execSQL(key);
    }


}
