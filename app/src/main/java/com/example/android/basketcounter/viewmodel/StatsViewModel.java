package com.example.android.basketcounter.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.basketcounter.data.DataBaseRoom;
import com.example.android.basketcounter.model.Stats;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class StatsViewModel extends AndroidViewModel {
    LiveData<List<Stats>> statsList;
    private static DataBaseRoom db;

    public StatsViewModel(@NonNull Application application) {
        super(application);
        db = DataBaseRoom.getInstance(application);

    }

    public LiveData<List<Stats>> getStats(int idPlayer) {
        statsList = db.statsDAO().findByPlayer(idPlayer);
        return statsList;
    }

    public void addStats(List<Stats> stats) {
        new AsyncAddStatsDB().execute(stats);
    }

    private class AsyncAddStatsDB extends AsyncTask<List<Stats>, Void, List<Long>> {

        List<Stats> stat;


        @Override
        protected List<Long> doInBackground(List<Stats>... lists) {
            List<Long> id = new ArrayList<>();

            if (lists.length != 0) {
                stat = lists[0];
                id = db.statsDAO().insertPlayer(lists[0]);
            }

            return id;
        }

        @Override
        protected void onPostExecute(List<Long> id) {
            boolean intro = true;
            for (int i = 0; i < id.size(); i++) {
                if (id.get(i) == null || id.get(i) == -1) {
                    intro = false;
                }
            }
            if (!intro) {
                Toast.makeText(getApplication(), "Error adding stats", Toast.LENGTH_SHORT)
                        .show();
            } else {

                Toast.makeText(getApplication(), "Stats added", Toast.LENGTH_SHORT)
                        .show();
                //productList.add(0,product);
                //adapter.notifyItemInserted(0);
            }
        }
    }
}
