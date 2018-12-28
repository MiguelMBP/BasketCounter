package com.example.android.basketcounter.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.android.basketcounter.data.DataBaseRoom;
import com.example.android.basketcounter.model.Match;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MatchViewModel extends AndroidViewModel {
    private LiveData<List<Match>> matchList;
    private static DataBaseRoom db;
    private long idIntro;

    public MatchViewModel(@NonNull Application application) {
        super(application);
        db = DataBaseRoom.getInstance(application);
        matchList = db.matchDAO().getAll();
    }

    public LiveData<List<Match>> getMatchList() {
        return matchList;
    }

    public void addMatch(Match match) {
        try {
            new AsyncAddMatch().execute(match).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public long getIdIntro() {
        return idIntro;
    }

    public LiveData<Long> getMatchCount() {
        return db.matchDAO().getMatchesCount();
    }


    private class AsyncAddMatch extends AsyncTask<Match, Void, Long> {

        Match match;

        @Override
        protected Long doInBackground(Match... matches) {

            idIntro = -1;

            if (matches.length != 0) {
                match = matches[0];
                idIntro = db.matchDAO().insertMatch(matches[0]);
                match.setId(idIntro);
            }

            return idIntro;
        }

        @Override
        protected void onPostExecute(Long id) {
            if (id == -1) {
                Toast.makeText(getApplication(), "Error adding match", Toast.LENGTH_SHORT)
                        .show();
            } else {

                Toast.makeText(getApplication(), "Match added", Toast.LENGTH_SHORT)
                        .show();
            }
        }


    }


}
