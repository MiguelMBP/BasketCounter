package com.example.android.basketcounter.model;

public class NBATeam {

    private String name;
    private String city;
    private boolean isNBAFranchise;
    private boolean isAllStar;
    private long teamId;

    public NBATeam(String name, String city, boolean isNBAFranchise, boolean isAllStar, long teamId) {
        this.name = name;
        this.city = city;
        this.isNBAFranchise = isNBAFranchise;
        this.isAllStar = isAllStar;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isNBAFranchise() {
        return isNBAFranchise;
    }

    public void setNBAFranchise(boolean NBAFranchise) {
        isNBAFranchise = NBAFranchise;
    }

    public boolean isAllStar() {
        return isAllStar;
    }

    public void setAllStar(boolean allStar) {
        isAllStar = allStar;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
