package com.edu.ck.mymatches;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.BaseColumns;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //version
    private static final int DATABASE_VERSION = 5;
    //nom database
    private static final String DATABASE_NAME = "MyMatches.db";

    //Noms des tables
    private static final String TABLE_EQUIPE_NAME = "equipe";

    //Supprimer SQL
    private static final String SQL_DELETE_EQUIPE = "DROP TABLE equipe ";
    //private static final String SQL_DELETE_MATCH = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    //Colonnes de la table
    public static class FeedEntry implements BaseColumns
    {
        public static final String COLUMN_MATCH_ID = "id";
        public static final String COLUMN_MATCH_EQUIPE1= "equipe1";
        public static final String COLUMN_MATCH_EQUIPE2= "equipe2";
        public static final String COLUMN_MATCH_SCORE_EQUIPE1 = "score_equipe1";
        public static final String COLUMN_MATCH_SCORE_EQUIPE2 = "score_equipe2";
        public static final String COLUMN_MATCH_ENTRAINEUR1= "entraineur1";
        public static final String COLUMN_MATCH_ENTRAINEUR2= "entraineur2";

        public static final String COLUMN_EQUIPE1_JOUEUR1= "joueur1_e1";
        public static final String COLUMN_EQUIPE1_JOUEUR2= "joueur2_e1";
        public static final String COLUMN_EQUIPE1_JOUEUR3= "joueur3_e1";
        public static final String COLUMN_EQUIPE2_JOUEUR1= "joueur1_e2";
        public static final String COLUMN_EQUIPE2_JOUEUR2= "joueur2_e2";
        public static final String COLUMN_EQUIPE2_JOUEUR3= "joueur3_e2";

        public static final String COLUMN_MATCH_PHOTO = "photo";
        public static final String COLUMN_MATCH_LONGITUDE = "longitude";
        public static final String COLUMN_MATCH_LATITUDE = "latitude";
        public static final String COLUMN_MATCH_DATE = "date";

    }

    //Creation de la table (requete SQL)
    public static final String EQUIPE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_EQUIPE_NAME + "(" +
                    FeedEntry.COLUMN_MATCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FeedEntry.COLUMN_MATCH_EQUIPE1 + " TEXT, " +
                    FeedEntry.COLUMN_MATCH_EQUIPE2 + " TEXT, " +
                    FeedEntry.COLUMN_MATCH_SCORE_EQUIPE1 + " TEXT, " +
                    FeedEntry.COLUMN_MATCH_SCORE_EQUIPE2 + " TEXT, " +
                    FeedEntry.COLUMN_MATCH_ENTRAINEUR1 + " Entraineur, " +
                    FeedEntry.COLUMN_MATCH_ENTRAINEUR2 + " Entraineur, " +
                    FeedEntry.COLUMN_EQUIPE1_JOUEUR1 + " Joueur1, " +
                    FeedEntry.COLUMN_EQUIPE1_JOUEUR2 + " TEXT2, " +
                    FeedEntry.COLUMN_EQUIPE1_JOUEUR3 + " TEXT3, " +
                    FeedEntry.COLUMN_EQUIPE2_JOUEUR1 + " Joueur1, " +
                    FeedEntry.COLUMN_EQUIPE2_JOUEUR2 + " TEXT2, " +
                    FeedEntry.COLUMN_EQUIPE2_JOUEUR3 + " TEXT3, " +
                    FeedEntry.COLUMN_MATCH_PHOTO + " TEXT3, " +
                    FeedEntry.COLUMN_MATCH_LONGITUDE + " TEXT5, " +
                    FeedEntry.COLUMN_MATCH_LATITUDE + " TEXT6, " +
                    FeedEntry.COLUMN_MATCH_DATE + " TEXTE)";


    //Insertion dans la table equipe
    public boolean insertDataMatch(String equipe1, String equipe2, String score1, String score2,
                                    String entraineur1, String entraineur2, String joueur1, String joueur2,
                                    String joueur3,String joueur4, String joueur5, String joueur6, String photo,
                                    String longitude, String latitude, String date)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_MATCH_EQUIPE1, equipe1);
        values.put(FeedEntry.COLUMN_MATCH_EQUIPE2, equipe2);
        values.put(FeedEntry.COLUMN_MATCH_SCORE_EQUIPE1, score1);
        values.put(FeedEntry.COLUMN_MATCH_SCORE_EQUIPE2, score2);
        values.put(FeedEntry.COLUMN_MATCH_ENTRAINEUR1, entraineur1);
        values.put(FeedEntry.COLUMN_MATCH_ENTRAINEUR2, entraineur2);
        values.put(FeedEntry.COLUMN_EQUIPE1_JOUEUR1, joueur1);
        values.put(FeedEntry.COLUMN_EQUIPE1_JOUEUR2, joueur2);
        values.put(FeedEntry.COLUMN_EQUIPE1_JOUEUR3, joueur3);
        values.put(FeedEntry.COLUMN_EQUIPE2_JOUEUR1, joueur4);
        values.put(FeedEntry.COLUMN_EQUIPE2_JOUEUR2, joueur5);
        values.put(FeedEntry.COLUMN_EQUIPE2_JOUEUR3, joueur6);
        values.put(FeedEntry.COLUMN_MATCH_PHOTO, photo);
        values.put(FeedEntry.COLUMN_MATCH_LONGITUDE, longitude);
        values.put(FeedEntry.COLUMN_MATCH_LATITUDE, latitude);
        values.put(FeedEntry.COLUMN_MATCH_DATE, date);

        long result = db.insert(TABLE_EQUIPE_NAME, null, values);

        if(result == -1) { return false; }
        else { return true; }

    }

    //Récupération de toutes les données
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_EQUIPE_NAME, null);
        return result;
    }

    //Récupération des noms d'équipes et scores avec id
    public Cursor getScores(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + FeedEntry.COLUMN_MATCH_EQUIPE1 + ", " +
                FeedEntry.COLUMN_MATCH_EQUIPE2 + ", " +
                FeedEntry.COLUMN_MATCH_SCORE_EQUIPE1 + ", " +
                FeedEntry.COLUMN_MATCH_SCORE_EQUIPE2 + " FROM " + TABLE_EQUIPE_NAME + " WHERE ("+
                FeedEntry.COLUMN_MATCH_ID + " = " + id + ")", null);
        return result;
    }

    //Récupération des entraineurs et joueurs avec id
    public Cursor getEquipes(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + FeedEntry.COLUMN_MATCH_ENTRAINEUR1 + ", " +
                FeedEntry.COLUMN_MATCH_ENTRAINEUR2 + ", " +
                FeedEntry.COLUMN_EQUIPE1_JOUEUR1 + ", " +
                FeedEntry.COLUMN_EQUIPE1_JOUEUR2 + ", " +
                FeedEntry.COLUMN_EQUIPE1_JOUEUR3 + ", " +
                FeedEntry.COLUMN_EQUIPE2_JOUEUR1 + ", " +
                FeedEntry.COLUMN_EQUIPE2_JOUEUR2 + ", " +
                FeedEntry.COLUMN_EQUIPE2_JOUEUR3 + " FROM " + TABLE_EQUIPE_NAME + " WHERE ("+
                FeedEntry.COLUMN_MATCH_ID + " = " + id + ")", null);
        return result;
    }

    //Récupération des 5 derniers matchs pour MainActivity
    //A REMPLIR !!!
    public List<MatchC> getPreviousMatchs()
    {
        List<MatchC> matchs = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT " + FeedEntry.COLUMN_MATCH_ID + ", " +
                FeedEntry.COLUMN_MATCH_EQUIPE1 + ", " +
                FeedEntry.COLUMN_MATCH_EQUIPE2 + ", " +
                FeedEntry.COLUMN_MATCH_DATE + " FROM " + TABLE_EQUIPE_NAME +
                " ORDER BY " + FeedEntry.COLUMN_MATCH_DATE + " DESC LIMIT 5", null);

        return matchs;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EQUIPE_TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EQUIPE);
        onCreate(db);
    }
}
