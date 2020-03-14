package com.edu.ck.mymatches;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/* DOCUMENTATION :
https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    //version
    public static final int DATABASE_VERSION = 1;

    //nom database, table et attributs
    public static final String DATABASE_NAME = "Information_Match.db";
    public static final String TABLE_EQUIPE_NAME = "equipe_table";
    public static final String COLUMN_EQUIPEE_ID = "id";
    public static final String COLUMN_EQUIPE_JOUEUR1= "joueur1";
    public static final String COLUMN_EQUIPE_JOUEUR2= "joueur2";
    public static final String COLUMN_EQUIPE_JOUEUR3= "joueur3";
    public static final String COLUMN_EQUIPE_JOUEUR4= "joueur4";
    public static final String COLUMN_EQUIPE_JOUEUR5= "joueur5";
    public static final String COLUMN_EQUIPE_JOUEUR6= "joueur6";
    public static final String COLUMN_EQUIPE_JOUEUR7= "joueur7";
    public static final String COLUMN_EQUIPE_JOUEUR8= "joueur8";
    public static final String COLUMN_EQUIPE_JOUEUR9= "joueur9";
    public static final String COLUMN_EQUIPE_JOUEUR10= "joueur10";
    public static final String COLUMN_EQUIPE_JOUEUR11= "joueur11";
    public static final String COLUMN_EQUIPE_JOUEUR12= "joueur12";
    public static final String COLUMN_EQUIPE_JOUEUR13= "joueur13";
    public static final String COLUMN_EQUIPE_JOUEUR14= "joueur14";
    public static final String COLUMN_EQUIPE_JOUEUR15= "joueur15";


    //requete SQL
    public static final String EQUIPE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_EQUIPE_NAME + " (" +
                    COLUMN_EQUIPEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EQUIPE_JOUEUR1 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR2 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR3 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR4 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR5 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR6 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR7 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR8 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR9 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR11 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR12 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR13 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR14 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR15 + " TEXT, " +
                    COLUMN_EQUIPE_JOUEUR10 + " TEXT);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
