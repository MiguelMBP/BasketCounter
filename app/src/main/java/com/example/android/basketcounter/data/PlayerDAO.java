package com.example.android.basketcounter.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;

import java.util.List;

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM players")
    List<Player> getAll();

    @Query("SELECT * FROM players WHERE teamId = :teamId")
    List<Player> findByTeam(long teamId);

    @Query("SELECT * FROM players WHERE id = :id")
    Player finById(int id);

    @Insert
    long insertTeam(Team team);

    @Update
    int updateTeam(Team team);

    @Delete
    int deleteTeam(Team team);
}
