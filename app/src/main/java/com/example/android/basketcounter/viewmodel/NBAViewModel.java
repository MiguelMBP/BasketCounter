package com.example.android.basketcounter.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.basketcounter.model.NBATeam;
import com.example.android.basketcounter.utils.QueryUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NBAViewModel extends AndroidViewModel {

    private static MutableLiveData<List<NBATeam>> teams;
    private Application application;
    private static final String NBA_REQUEST_URL = "http://data.nba.net/10s/prod/v2/2018/teams.json";

    public NBAViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public LiveData<List<NBATeam>> getTeams() {
        if (teams == null) {
            teams = new MutableLiveData<>();
            loadTeams();
        }

        return teams;
    }

    private void loadTeams() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(application);

        Uri baseUri = Uri.parse(NBA_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();

        uriBuilder.appendQueryParameter("limit", "10");

        RequestQueue requestQueue = Volley.newRequestQueue(application);
        StringRequest request = new StringRequest(Request.Method.GET, uriBuilder.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                List<NBATeam> teamList = QueryUtils.extractFeatureFromJson(response);
                teams.setValue(teamList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Volley", error.toString());
            }
        });
        requestQueue.add(request);
    }
}
