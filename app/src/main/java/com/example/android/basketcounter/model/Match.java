package com.example.android.basketcounter.model;


import android.os.Parcel;
import android.os.Parcelable;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "matches", foreignKeys = {
        @ForeignKey(entity = Team.class, parentColumns = "tid", childColumns = "homeTeamId"),
        @ForeignKey(entity = Team.class, parentColumns = "tid", childColumns = "visitorId")
})

public class Match implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    @NonNull
    private long homeTeamId;
    @Ignore
    private Team homeTeam;
    @NonNull
    private long visitorId;
    @Ignore
    private Team visitor;
    @NonNull
    private int pointsHT;
    @NonNull
    private int pointsV;


    public Match() {
    }

    public Match(Team homeTeam, Team visitor, @NonNull int pointsHT, @NonNull int pointsV) {
        this.homeTeam = homeTeam;
        //this.homeTeamId = homeTeam.getTid();
        this.visitor = visitor;
        //this.visitorId = visitor.getTid();
        this.pointsHT = pointsHT;
        this.pointsV = pointsV;
    }

    protected Match(Parcel in) {
        id = in.readLong();
        homeTeamId = in.readLong();
        homeTeam = in.readParcelable(Team.class.getClassLoader());
        visitorId = in.readLong();
        visitor = in.readParcelable(Team.class.getClassLoader());
        pointsHT = in.readInt();
        pointsV = in.readInt();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NonNull
    public long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(@NonNull long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
        this.homeTeamId = homeTeam.getTid();
    }

    @NonNull
    public long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(@NonNull long visitorId) {
        this.visitorId = visitorId;
    }

    public Team getVisitor() {
        return visitor;
    }

    public void setVisitor(Team visitor) {
        this.visitor = visitor;
        this.visitorId = visitor.getTid();

    }

    @NonNull
    public int getPointsHT() {
        return pointsHT;
    }

    public void setPointsHT(@NonNull int pointsHT) {
        this.pointsHT = pointsHT;
    }

    @NonNull
    public int getPointsV() {
        return pointsV;
    }

    public void setPointsV(@NonNull int pointsV) {
        this.pointsV = pointsV;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(homeTeamId);
        dest.writeParcelable(homeTeam, flags);
        dest.writeLong(visitorId);
        dest.writeParcelable(visitor, flags);
        dest.writeInt(pointsHT);
        dest.writeInt(pointsV);
    }
}
