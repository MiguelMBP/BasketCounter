package com.example.android.basketcounter.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.android.basketcounter.data.DataBaseRoom;
import com.example.android.basketcounter.model.Team;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TeamViewModel extends AndroidViewModel {

    private LiveData<List<Team>> teamsList;
    private static DataBaseRoom db;

    public TeamViewModel(@NonNull Application application) {
        super(application);
        db = DataBaseRoom.getInstance(application);
        this.teamsList = db.teamDAO().getAll();
    }

    public LiveData<List<Team>> getTeams() {
        return teamsList;
    }

    public LiveData<Team> getTeamById(long id) {
        return db.teamDAO().findById(id);
    }

    public void addTeam(Team team) {
    new AsyncAddTeamDB().execute(team);
    }

    private class AsyncAddTeamDB extends AsyncTask<Team, Void, Long> {

        Team team;

        @Override
        protected Long doInBackground(Team... teams) {
            long id = -1;
            if (teams.length != 0) {
                team = teams[0];
                id = db.teamDAO().insertTeam(teams[0]);
                team.setTid(id);
            }

            return id;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1) {
                Toast.makeText(getApplication(), "Error adding team", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplication(), "Team added", Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }
}
