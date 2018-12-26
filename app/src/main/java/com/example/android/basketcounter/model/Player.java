package com.example.android.basketcounter.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "players")
public class Player implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private String name;
    @NonNull
    private int number;
    @NonNull
    private long teamId;
    @Ignore
    private Team team;

    public Player(@NonNull int number, @NonNull String name, Team team) {
        this.name = name;
        this.number = number;
        this.team = team;
    }

    public Player() {
    }

    protected Player(Parcel in) {
        id = in.readLong();
        name = in.readString();
        number = in.readInt();
        teamId = in.readLong();
        team = in.readParcelable(Team.class.getClassLoader());
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public int getNumber() {
        return number;
    }

    public void setNumber(@NonNull int number) {
        this.number = number;
    }

    @NonNull
    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(@NonNull long teamId) {
        this.teamId = teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeInt(number);
        dest.writeLong(teamId);
        dest.writeParcelable(team, flags);
    }
}
