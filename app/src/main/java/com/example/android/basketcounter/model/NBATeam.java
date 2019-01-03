package com.example.android.basketcounter.model;

public class NBATeam {

    private String name;
    private boolean isNBAFranchise;
    private boolean isAllStar;
    private long teamId;
    private String confName;
    private String divNAme;
    private String tricode;

    public NBATeam(String name, boolean isNBAFranchise, boolean isAllStar, long teamId, String confName, String divNAme, String tricode) {
        this.name = name;
        this.isNBAFranchise = isNBAFranchise;
        this.isAllStar = isAllStar;
        this.teamId = teamId;
        this.confName = confName;
        this.divNAme = divNAme;
        this.tricode = tricode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getDivNAme() {
        return divNAme;
    }

    public void setDivNAme(String divNAme) {
        this.divNAme = divNAme;
    }

    public String getTricode() {
        return tricode;
    }

    public void setTricode(String tricode) {
        this.tricode = tricode;
    }
}

