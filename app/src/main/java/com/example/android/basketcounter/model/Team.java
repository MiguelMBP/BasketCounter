package com.example.android.basketcounter.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {
    private String name;
    private int nPlayers;

    public Team(String name) {
        this.name = name;
    }

    protected Team(Parcel in) {
        name = in.readString();
        nPlayers = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getnPlayers() {
        return nPlayers;
    }

    public void setnPlayers(int nPlayers) {
        this.nPlayers = nPlayers;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(nPlayers);
    }
}
