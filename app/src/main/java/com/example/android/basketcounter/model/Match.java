package com.example.android.basketcounter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Match implements Parcelable {

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

    protected Match(Parcel in) {
        homeTeam = in.readParcelable(Team.class.getClassLoader());
        visitor = in.readParcelable(Team.class.getClassLoader());
        pointsHT = in.readInt();
        pointsV = in.readInt();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(homeTeam, flags);
        dest.writeParcelable(visitor, flags);
        dest.writeInt(pointsHT);
        dest.writeInt(pointsV);
    }
}
