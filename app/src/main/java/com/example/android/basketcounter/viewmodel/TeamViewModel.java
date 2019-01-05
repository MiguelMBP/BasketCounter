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

    public void deleteTeam(Team team){
        new AsynDeleteTeamDB().execute(team);
    }

    public void updateTeam(Team team){
        new AsyncEditTeamDB().execute(team);
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

    private class AsynDeleteTeamDB extends AsyncTask<Team, Void, Integer> {

        public AsynDeleteTeamDB() {

        }

        @Override
        protected Integer doInBackground(Team... teams) {

            int deletedrows = 0;

            if (teams.length != 0) {

                deletedrows = db.teamDAO().deleteTeam(teams[0]);

            }

            return deletedrows;

        }

        @Override
        protected void onPostExecute(Integer deletedRows) {
            if (deletedRows == 0) {
                Toast.makeText(getApplication(), "Error deleting team", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplication(), "Team deleted", Toast.LENGTH_SHORT)
                        .show();
            }
        }

    }

    private class AsyncEditTeamDB extends AsyncTask<Team, Void, Integer> {



        public AsyncEditTeamDB() {

        }

        @Override
        protected Integer doInBackground(Team... teams) {
            int updatedrows = 0;
            if (teams.length != 0) {

                updatedrows = db.teamDAO().updateTeam(teams[0]);

            }

            return updatedrows;
        }

        @Override
        protected void onPostExecute(Integer updatedRows) {
            if (updatedRows == 0) {
                Toast.makeText(getApplication(), "Error updating team", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getApplication(), "Team updated", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
