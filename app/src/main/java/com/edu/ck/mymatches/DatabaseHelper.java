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

public class DatabaseHelper extends SQLiteOpenHelper {

    //version
    private static final int DATABASE_VERSION = 1;
    //nom database
    private static final String DATABASE_NAME = "MyMatches.db";

    //Noms des tables
    private static final String TABLE_EQUIPE_NAME = "equipe";

    //Supprimer SQL
    private static final String SQL_DELETE_EQUIPE = "DROP TABLE IF EXISTS " + TABLE_EQUIPE_NAME;
    //private static final String SQL_DELETE_MATCH = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null , DATABASE_VERSION);
    }

    //Colonnes de la table
    public static class FeedEntry implements BaseColumns
    {
        public static final String COLUMN_EQUIPE_ID = "id";
        public static final String COLUMN_EQUIPE_NOM= "nom";
        public static final String COLUMN_EQUIPE_ENTRAINEUR= "entraineur";
        public static final String COLUMN_EQUIPE_JOUEUR1= "joueur1";
        public static final String COLUMN_EQUIPE_JOUEUR2= "joueur2";
        public static final String COLUMN_EQUIPE_JOUEUR3= "joueur3";
        public static final String COLUMN_EQUIPE_JOUEUR4= "joueur4";
        public static final String COLUMN_EQUIPE_JOUEUR5= "joueur5";
    }

    //Creation de la table (requete SQL)
    public static final String EQUIPE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_EQUIPE_NAME + "(" +
                    FeedEntry.COLUMN_EQUIPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FeedEntry.COLUMN_EQUIPE_NOM + " Nom, " +
                    FeedEntry.COLUMN_EQUIPE_ENTRAINEUR + " Entraineur, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR1 + " Joueur1, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR2 + " TEXT2, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR3 + " TEXT3, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR4 + " TEXT5, " +
                    FeedEntry.COLUMN_EQUIPE_JOUEUR5 + " TEXT6)";


    //Insertion dans la table equipe
    public boolean insertDataEquipe(String nom_equipe, String entraineur, String joueur1, String joueur2,
                                 String joueur3, String joueur4, String joueur5)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FeedEntry.COLUMN_EQUIPE_NOM, nom_equipe);
        values.put(FeedEntry.COLUMN_EQUIPE_ENTRAINEUR, entraineur);
        values.put(FeedEntry.COLUMN_EQUIPE_JOUEUR1, joueur1);
        values.put(FeedEntry.COLUMN_EQUIPE_JOUEUR2, joueur2);
        values.put(FeedEntry.COLUMN_EQUIPE_JOUEUR3, joueur3);
        values.put(FeedEntry.COLUMN_EQUIPE_JOUEUR4, joueur4);
        values.put(FeedEntry.COLUMN_EQUIPE_JOUEUR5, joueur5);

        long result = db.insert(TABLE_EQUIPE_NAME, null, values);

        if(result == -1) { return false; }
        else { return true; }

    }

    //Afficher toutes les données
    public Cursor getAllData()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM " + TABLE_EQUIPE_NAME, null);
        return result;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EQUIPE_TABLE_CREATE );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_EQUIPE);
        onCreate(db);
    }
}
