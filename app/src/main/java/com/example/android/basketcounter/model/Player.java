package com.example.android.basketcounter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {

    private String name;
    private int number;
    private Team team;
    private int freeThrows;
    private int twoPointers;
    private int triples;
    private int fouls;

    public Player(int number, String name, Team team) {
        this.name = name;
        this.number = number;
        this.team = team;
        this.freeThrows = 0;
        this.twoPointers = 0;
        this.triples = 0;
        this.fouls = 0;
    }

    protected Player(Parcel in) {
        name = in.readString();
        number = in.readInt();
        team = in.readParcelable(Team.class.getClassLoader());
        freeThrows = in.readInt();
        twoPointers = in.readInt();
        triples = in.readInt();
        fouls = in.readInt();
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }

    public int getFreeThrows() {
        return freeThrows;
    }

    public int getTwoPointers() {
        return twoPointers;
    }

    public int getTriples() {
        return triples;
    }

    public int getFouls() {
        return fouls;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void freeThrow() {
        freeThrows++;
    }

    public void twoPointer() {
        twoPointers++;
    }

    public void triple() {
        triples++;
    }

    public void foul() {
        fouls++;
    }

    public void cancelFreeThrow() {
        freeThrows--;
    }

    public void cancelTwoPointer() {
        twoPointers--;
    }

    public void cancelTriple() {
        triples--;
    }

    public void cancelFoul() {
        fouls--;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(number);
        dest.writeParcelable(team, flags);
        dest.writeInt(freeThrows);
        dest.writeInt(twoPointers);
        dest.writeInt(triples);
        dest.writeInt(fouls);
    }
}
