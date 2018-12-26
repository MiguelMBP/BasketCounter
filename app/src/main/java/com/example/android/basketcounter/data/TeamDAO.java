package com.example.android.basketcounter.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.basketcounter.model.Team;

import java.util.List;

@Dao
public interface TeamDAO {

    @Query("SELECT * FROM teams")
    List<Team> getAll();

    @Query("SELECT * FROM teams WHERE tid = :teamId")
    Team findById(int teamId);

    @Insert
    long insertTeam(Team team);

    @Delete
    int deleteTeam(Team team);

    @Update
    int updateTeam(Team team);
}
