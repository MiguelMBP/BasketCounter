package com.example.android.basketcounter.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.basketcounter.model.Match;

import java.util.List;

@Dao
public interface MatchDAO {

    @Query("SELECT * FROM matches")
    List<Match> getAll();

    @Insert
    long insertMatch(Match match);

    @Update
    int updateMatch(Match match);

    @Delete
    int deleteMatch(Match match);
}
