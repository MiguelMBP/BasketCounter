package com.example.android.basketcounter.model;

public class CounterPlayer {

    private String name;
    private int freeThrows;
    private int twoPointers;
    private int triples;
    private int fouls;

    public CounterPlayer(String name) {
        this.name = name;
        this.freeThrows = 0;
        this.twoPointers = 0;
        this.triples = 0;
        this.fouls = 0;
    }

    public String getName() {
        return name;
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
