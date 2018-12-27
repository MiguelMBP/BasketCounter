package com.example.android.basketcounter.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "stats", foreignKeys = {
        @ForeignKey(entity = Player.class, parentColumns = "id", childColumns = "playerId"),
        @ForeignKey(entity = Match.class, parentColumns = "id", childColumns = "matchId")})

public class Stats {

    //Estadisticas de un jugador en un partido

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private long playerId;
    @Ignore
    private Player player;
    @NonNull
    private long matchId;
    @Ignore
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

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(@NonNull long playerId) {
        this.playerId = playerId;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @NonNull
    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(@NonNull long matchId) {
        this.matchId = matchId;
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
}
