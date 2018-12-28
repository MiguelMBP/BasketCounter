package com.example.android.basketcounter.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.basketcounter.data.DataBaseRoom;
import com.example.android.basketcounter.model.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PlayerViewModel extends AndroidViewModel {

    private static DataBaseRoom db;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        db = DataBaseRoom.getInstance(application);
    }

    public LiveData<List<Player>> getPlayersByTeam(long teamId) {
        return db.playerDAO().findByTeam(teamId);
    }

    public void addPlayer(Player player) {
        new AsyncAddPlayer().execute(player);
    }

    private class AsyncAddPlayer extends AsyncTask<Player, Void, Long> {

        Player player;

        @Override
        protected Long doInBackground(Player... players) {

            long id = -1;

            if (players.length != 0) {
                player = players[0];
                
                id = db.playerDAO().insertPlayer(players[0]);
                player.setId(id);
            }

            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1) {
                Toast.makeText(getApplication(), "Error adding player", Toast.LENGTH_SHORT)
                        .show();
            } else {

                Toast.makeText(getApplication(), "Player added", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
