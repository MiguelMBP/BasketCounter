package com.example.android.basketcounter.data;

import com.example.android.basketcounter.model.Match;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MatchDAO {

    @Query("SELECT * FROM matches")
    LiveData<List<Match>> getAll();

    @Query("SELECT COUNT(*) FROM matches")
    LiveData<Long> getMatchesCount();

    @Insert
    long insertMatch(Match match);

    @Update
    int updateMatch(Match match);

    @Delete
    int deleteMatch(Match match);
}
