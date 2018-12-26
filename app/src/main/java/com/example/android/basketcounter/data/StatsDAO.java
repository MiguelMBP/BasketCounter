package com.example.android.basketcounter.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;

import java.util.List;

@Dao
public interface StatsDAO {
    @Query("SELECT * FROM stats WHERE player = :player")
    List<Stats> findByPlayer(Player player);

    @Insert
    long insertPlayer(Player player);

    @Update
    int updatePlayer(Player player);

    @Delete
    int deletePlayer(Player player);

}
