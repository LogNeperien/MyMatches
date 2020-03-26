package com.edu.ck.mymatches;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

/* DOCUMENTATION :
https://developer.android.com/reference/android/database/sqlite/SQLiteOpenHelper
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //version
    public static final int DATABASE_VERSION = 1;
    //nom database
    public static final String DATABASE_NAME = "Information_Match.db";


    /* Inner class that defines the table contents */
        public static class FeedEntry implements BaseColumns {

            public static final String TABLE_EQUIPE_NAME = "equipe";

            public static final String COLUMN_EQUIPEE_ID = "id";
            public static final String COLUMN_EQUIPE_NOM= "nom";
            public static final String COLUMN_EQUIPE_ENTRAINEUR= "entraineur";
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

            public static final String TABLE_MATCH_NAME = "match";

            public static final String COLUMN_MATCH_ID = "id";
            public static final String COLUMN_MATCH_NOM = "nom";
            public static final String COLUMN_MATCH_ID_EQUIPE1 = "id_equipe1";
            public static final String COLUMN_MATCH_ID_EQUIPE2 = "id_equipe2";
            public static final String COLUMN_MATCH_ID_PHOTO = "id_photo";
            public static final String COLUMN_MATCH_LONGITUDE = "longitude";
            public static final String COLUMN_MATCH_LATITUDE = "latitude";

        }

    //requete SQL
    public static final String EQUIPE_TABLE_CREATE =
            "CREATE TABLE " + FeedEntry.TABLE_EQUIPE_NAME + " (" +
                    FeedEntry.COLUMN_EQUIPEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FeedEntry.COLUMN_EQUIPE_NOM + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_ENTRAINEUR + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR1 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR2 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR3 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR4 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR5 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR6 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR7 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR8 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR9 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR11 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR12 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR13 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR14 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR15 + " TEXT, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR10 + " TEXT);";

    public static final String MATCH_TABLE_CREATE;

    private static final String SQL_DELETE_EQUIPE = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_EQUIPE_NAME;
    private static final String SQL_DELETE_MATCH;

    static {
        MATCH_TABLE_CREATE = "CREATE TABLE " + FeedEntry.TABLE_MATCH_NAME + " (" +
                FeedEntry.COLUMN_MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FeedEntry.COLUMN_MATCH_NOM + " TEXT, " +
                FeedEntry.COLUMN_MATCH_ID_EQUIPE1 + " INTEGER FOREIGN KEY (" + FeedEntry.COLUMN_MATCH_ID_EQUIPE1 + ") REFERENCES " + FeedEntry.TABLE_EQUIPE_NAME + "(" + FeedEntry.COLUMN_EQUIPEE_ID + ")" + ", " +
                FeedEntry.COLUMN_MATCH_ID_EQUIPE1 + "INTEGER FOREIGN KEY (" + FeedEntry.COLUMN_MATCH_ID_EQUIPE2 + ") REFERENCES " + FeedEntry.TABLE_EQUIPE_NAME + "(" + FeedEntry.COLUMN_EQUIPEE_ID + ")" + ", " +
                FeedEntry.COLUMN_MATCH_LATITUDE + "INTEGER," +
                FeedEntry.COLUMN_MATCH_LONGITUDE + "INTEGER;";
        SQL_DELETE_MATCH = "DROP TABLE IF EXISTS " + FeedEntry.TABLE_MATCH_NAME;
    }


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EQUIPE_TABLE_CREATE);
        //db.execSQL(MATCH_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EQUIPE);
        //db.execSQL(SQL_DELETE_MATCH);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }



    public Cursor getDataEquipe(String nomEquipe)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = { FeedEntry.COLUMN_EQUIPE_NOM, FeedEntry.COLUMN_EQUIPE_ENTRAINEUR};
        String[] params = new String[]{nomEquipe};
        Cursor res = db.query(FeedEntry.TABLE_EQUIPE_NAME, columns, "nom=?", params, null, null, null);
        return res;
    }
}
