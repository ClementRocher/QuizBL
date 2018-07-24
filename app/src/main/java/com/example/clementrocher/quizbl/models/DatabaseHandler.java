package com.example.clementrocher.quizbl.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String PROFIL_KEY = "id";
    public static final String PROFIL_NOM = "nom";
    public static final String PROFIL_PRENOM = "prenom";
    public static final String PROFIL_TABLE_NAME = "Profil";
    public static final String PROFIL_TABLE_CREATE =
            "CREATE TABLE " +PROFIL_TABLE_NAME+ "(" +
                    PROFIL_KEY +" INTEGER PRIMARY KEY AUTOINCREMENT, "+

                    PROFIL_NOM + " TEXT, " +

                    PROFIL_PRENOM + " TEXT);";

    public static final String PROFIL_TABLE_DROP = "DROP TABLE IF EXISTS " + PROFIL_TABLE_NAME + ";";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROFIL_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(PROFIL_TABLE_DROP);

        onCreate(db);
    }
}
