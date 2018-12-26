package com.example.android.basketcounter.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "stats")
public class Stats {

    //Estadisticas de un jugador en un partido

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private Player player;
    @NonNull
    private Match match;
    private int points;
    private int freeThrows;
    private int twoPointers;
    private int threePointers;
    private int fouls;

    public Stats(Player player, Match match, int points, int freeThrows, int twoPointers, int threePointers, int fouls) {
        this.player = player;
        this.match = match;
        this.points = points;
        this.freeThrows = freeThrows;
        this.twoPointers = twoPointers;
        this.threePointers = threePointers;
        this.fouls = fouls;
    }

    public Stats() {
    }

    public long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrows(int freeThrows) {
        this.freeThrows = freeThrows;
    }

    public int getTwoPointers() {
        return twoPointers;
    }

    public void setTwoPointers(int twoPointers) {
        this.twoPointers = twoPointers;
    }

    public int getThreePointers() {
        return threePointers;
    }

    public void setThreePointers(int threePointers) {
        this.threePointers = threePointers;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public void freeThrow() {
        freeThrows++;
    }

    public void twoPointer() {
        twoPointers++;
    }

    public void threePointer() {
        threePointers++;
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

    public void cancelthreePointer() {
        threePointers--;
    }

    public void cancelFoul() {
        fouls--;
    }
}