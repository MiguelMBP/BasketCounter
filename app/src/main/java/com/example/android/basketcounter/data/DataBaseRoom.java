package com.example.android.basketcounter.data;


import android.content.Context;

import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;
import com.example.android.basketcounter.model.Team;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Team.class, Player.class, Match.class, Stats.class}, version = 1, exportSchema = false)
public abstract class DataBaseRoom extends RoomDatabase {

    public abstract TeamDAO teamDAO();
    public abstract PlayerDAO playerDAO();
    public abstract MatchDAO matchDAO();
    public abstract StatsDAO statsDAO();
    private static DataBaseRoom INSTANCE = null;

    public static DataBaseRoom getInstance(final Context context){
        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DataBaseRoom.class, "basket_counter.db").fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

}
