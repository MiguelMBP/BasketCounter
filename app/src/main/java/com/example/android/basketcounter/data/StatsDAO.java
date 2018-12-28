package com.example.android.basketcounter.data;



import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface StatsDAO {
    @Query("SELECT * FROM stats WHERE playerId = :playerId")
    LiveData<List<Stats>> findByPlayer(long playerId);

    @Insert
    List<Long> insertPlayer(List<Stats> stats);


}
