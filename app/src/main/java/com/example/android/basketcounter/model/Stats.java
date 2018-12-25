package com.example.android.basketcounter.model;

public class Stats {

    //Estadisticas de un jugador en un partido

    private int playerNumber;
    private String name;
    private Match match;
    private Team team;
    private int points;
    private int threePointers;
    private int fouls;

    public Stats(int playerNumber, String name, Match match, Team team, int points, int threePointers, int fouls) {
        this.playerNumber = playerNumber;
        this.name = name;
        this.match = match;
        this.team = team;
        this.points = points;
        this.threePointers = threePointers;
        this.fouls = fouls;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
