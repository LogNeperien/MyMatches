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

    public static final String TABLE_MATCH_NAME = "match_table";

    public static final String COLUMN_MATCH_ID = "id";
    public static final String COLUMN_MATCH_NOM = "nom";
    public static final String COLUMN_MATCH_ID_EQUIPE1 = "id_equipe1";
    public static final String COLUMN_MATCH_ID_EQUIPE2 = "id_equipe2";
    public static final String COLUMN_MATCH_ID_PHOTO = "id_photo";
    public static final String COLUMN_MATCH_LONGITUDE = "longitude";
    public static final String COLUMN_MATCH_LATITUDE = "latitude";




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

    public static final String MATCH_TABLE_CREATE =
            "CREATE TABLE " + TABLE_MATCH_NAME + " (" +
                    COLUMN_MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_MATCH_NOM + " TEXT, " +
                    COLUMN_MATCH_ID_EQUIPE1 + " INTEGER FOREIGN KEY (" + COLUMN_MATCH_ID_EQUIPE1 + ") REFERENCES " + TABLE_EQUIPE_NAME + "(" + COLUMN_EQUIPEE_ID + ")" + ", " +
                    COLUMN_MATCH_ID_EQUIPE1 + "INTEGER FOREIGN KEY (" + COLUMN_MATCH_ID_EQUIPE2 + ") REFERENCES " + TABLE_EQUIPE_NAME + "(" + COLUMN_EQUIPEE_ID + ")" + ", " +
                    //COLUMN_MATCH_ID_PHOTO + " INTEGER FOREIGN KEY (" + COLUMN_MATCH_ID_PHOTO + ") REFERENCES " + TABLE_PHOTO_NAME + "(" + COLUMN_PHOTO_ID + ")" + ", " +
                    COLUMN_MATCH_LATITUDE + "INTEGER," +
                    COLUMN_MATCH_LONGITUDE + "INTEGER;";

    private static final String SQL_DELETE_EQUIPE = "DROP TABLE IF EXISTS " + TABLE_EQUIPE_NAME;
    private static final String SQL_DELETE_MATCH = "DROP TABLE IF EXISTS " + TABLE_MATCH_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EQUIPE_TABLE_CREATE);
        db.execSQL(MATCH_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EQUIPE);
        db.execSQL(SQL_DELETE_MATCH);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
