package com.edu.ck.mymatches.model;

public class TableEquipe {

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

    private int id;
    private String entraineur;
    private String nom_equipe;
    private String joueur1;
    private String joueur2;
    private String joueur3;
    private String joueur4;
    private String joueur5;
    private String joueur6;
    private String joueur7;
    private String joueur8;
    private String joueur9;
    private String joueur10;
    private String joueur11;
    private String joueur12;
    private String joueur13;
    private String joueur14;
    private String joueur15;

    //requete SQL
    public static final String EQUIPE_TABLE_CREATE =
            "CREATE TABLE " + TABLE_EQUIPE_NAME + " (" +
                    COLUMN_EQUIPEE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EQUIPE_NOM + " TEXT, " +
                    COLUMN_EQUIPE_ENTRAINEUR + " TEXT, " +
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
}
