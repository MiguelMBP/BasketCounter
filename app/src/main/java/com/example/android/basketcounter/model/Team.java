package com.example.android.basketcounter.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "teams")
public class Team implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long tid;
    @NonNull
    private String name;


    public Team(String name) {
        this.name = name;
    }

    public Team() {
    }

    protected Team(Parcel in) {
        tid = in.readLong();
        name = in.readString();
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

    public long getTid() {
        return tid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(tid);
        dest.writeString(name);
    }

    public void setTid(long tid) {
        this.tid = tid;
    }
}
