package com.edu.ck.mymatches;

public class MatchC {

    private int id;
    private String equipe1, equipe2, score1, score2, entraineur1, entraineur2;
    private String joueur1, joueur2, joueur3, joueur4, joueur5, joueur6;
    private int lng, lat;

    public MatchC() {}

    public MatchC(int id, String equipe1, String equipe2, String score1, String score2, String entraineur1,
                  String entraineur2, String joueur1, String joueur2, String joueur3, String joueur4,
                  String joueur5, String joueur6, int lng, int lat) {
        this.id = id;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.score1 = score1;
        this.score2 = score2;
        this.entraineur1 = entraineur1;
        this.entraineur2 = entraineur2;
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueur3 = joueur3;
        this.joueur4 = joueur4;
        this.joueur5 = joueur5;
        this.joueur6 = joueur6;
        this.lng = lng;
        this.lat = lat;
    }

    //Tous les getters
    public int getId() { return id; }
    public String getEquipe1() {return equipe1; }
    public String getEquipe2() {return equipe2; }
    public String getScore1() {return score1; }
    public String getScore2() {return score2; }
    public String getEntraineur1() {return entraineur1; }
    public String getEntraineur2() {return entraineur2; }
    public String getJoueur1() {return joueur1; }
    public String getJoueur2() {return joueur2; }
    public String getJoueur3() {return joueur3; }
    public String getJoueur4() {return joueur4; }
    public String getJoueur5() {return joueur5; }
    public String getJoueur6() {return joueur6; }
    public int getLng() {return lng; }
    public int getLat() {return lat; }

    //Tous les setters
    public String setEquipe1(String equipe1) {this.equipe1 = equipe1; return equipe1; }
    public String setEquipe2(String equipe2) {this.equipe2 = equipe2; return equipe2;}
    public String setScore1(String score1) {this.score1 = score1; return score1;}
    public String setScore2(String score2) {this.score2 = score2; return score2; }
    public String setEntraineur1(String entraineur1) {this.entraineur1 = entraineur1; return entraineur1;}
    public String setEntraineur2(String entraineur2) {this.entraineur2 = entraineur2; return entraineur2;}
    public String setJoueur1(String joueur1) {this.joueur1 = joueur1; return joueur1;}
    public String setJoueur2(String joueur2) {this.joueur2 = joueur2; return joueur2; }
    public String setJoueur3(String joueur3) {this.joueur3 = joueur3; return joueur3; }
    public String setJoueur4(String joueur4) {this.joueur4 = joueur4; return joueur4;}
    public String setJoueur5(String joueur5) {this.joueur5 = joueur5; return joueur5; }
    public String setJoueur6(String joueur6) {this.joueur6 = joueur6; return joueur6; }
    public int setLng(int lng) {this.lng = lng; return lng; }
    public int setLat(int lat) {this.lat = lat; return lat;}

}
