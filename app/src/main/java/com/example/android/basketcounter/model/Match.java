package com.example.android.basketcounter.model;

public class Match {

    private Team homeTeam;
    private Team visitor;
    private int pointsHT;
    private int pointsV;

    public Match(Team homeTeam, Team visitor, int pointsHT, int pointsV) {
        this.homeTeam = homeTeam;
        this.visitor = visitor;
        this.pointsHT = pointsHT;
        this.pointsV = pointsV;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getVisitor() {
        return visitor;
    }

    public void setVisitor(Team visitor) {
        this.visitor = visitor;
    }

    public int getPointsHT() {
        return pointsHT;
    }

    public void setPointsHT(int pointsHT) {
        this.pointsHT = pointsHT;
    }

    public int getPointsV() {
        return pointsV;
    }

    public void setPointsV(int pointsV) {
        this.pointsV = pointsV;
    }
}
