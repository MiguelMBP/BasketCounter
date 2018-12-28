package com.example.android.basketcounter.data;


import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PlayerDAO {

    @Query("SELECT * FROM players")
    LiveData<List<Player>> getAll();

    @Query("SELECT * FROM players WHERE teamId = :teamId ORDER BY number")
    LiveData<List<Player>> findByTeam(long teamId);

    @Query("SELECT * FROM players WHERE id = :id")
    LiveData<Player> finById(int id);

    @Insert
    long insertPlayer(Player player);

    @Update
    int updatePlayer(Player player);

    @Delete
    int deletePlayer(Player player);
}
