package com.example.android.basketcounter.model;

public class Player {

    private String name;
    private Team team;
    private int freeThrows;
    private int twoPointers;
    private int triples;
    private int fouls;

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
        this.freeThrows = 0;
        this.twoPointers = 0;
        this.triples = 0;
        this.fouls = 0;
    }

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
}
