package com.example.android.basketcounter.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams")
public class Team implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long tid;
    @NonNull
    private String name;
    private String color;


    public Team(@NonNull String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Team() {
    }

    protected Team(Parcel in) {
        tid = in.readLong();
        name = in.readString();
        color = in.readString();
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

    public long getTid() {
        return tid;
    }

    public void setTid(long tid) {
        this.tid = tid;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(tid);
        dest.writeString(name);
        dest.writeString(color);
    }
}
