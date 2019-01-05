package com.example.android.basketcounter.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
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

    public void updatePlayer(Player player){
        new AsyncEditPlayerDB().execute(player);
    }

    public void deletePlayer(Player player){
        new AsynDeletePlayerDB().execute(player);
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

    private class AsyncEditPlayerDB extends AsyncTask<Player, Void, Integer> {



        public AsyncEditPlayerDB() {

        }

        @Override
        protected Integer doInBackground(Player... players) {
            int updatedrows = 0;
            if (players.length != 0) {

                updatedrows = db.playerDAO().updatePlayer(players[0]);

            }

            return updatedrows;
        }

        @Override
        protected void onPostExecute(Integer updatedRows) {
            if (updatedRows == 0) {
                Toast.makeText(getApplication(), "Error updating player", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplication(), "Player updated", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private class AsynDeletePlayerDB extends AsyncTask<Player, Void, Integer> {

        public AsynDeletePlayerDB() {

        }

        @Override
        protected Integer doInBackground(Player... players) {

            int deletedrows = 0;

            if (players.length != 0) {

                deletedrows = db.playerDAO().deletePlayer(players[0]);

            }

            return deletedrows;

        }

        @Override
        protected void onPostExecute(Integer deletedRows) {
            if (deletedRows == 0) {
                Toast.makeText(getApplication(), "Error deleting player", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplication(), "Player deleted", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }
}
