package com.example.android.basketcounter.data;


import com.example.android.basketcounter.model.Team;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TeamDAO {

    @Query("SELECT * FROM teams")
    LiveData<List<Team>> getAll();

    @Query("SELECT * FROM teams WHERE tid = :teamId")
    Team findById(int teamId);

    @Insert
    long insertTeam(Team team);

    @Delete
    int deleteTeam(Team team);

    @Update
    int updateTeam(Team team);
}
