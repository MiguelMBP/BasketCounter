package com.example.android.basketcounter.utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.basketcounter.model.NBATeam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class QueryUtils {

    private static List<NBATeam> teams;

    public QueryUtils() {
    }

    public static List<NBATeam> extractFeatureFromJson(String nbaJSON) {

        if (TextUtils.isEmpty(nbaJSON)) {
            return null;
        }

        List<NBATeam> teams = new ArrayList<>();


        try {

            JSONObject baseJsonResponse = new JSONObject(nbaJSON);

            JSONObject confObj = baseJsonResponse.getJSONObject("league");
            JSONArray teamArray = confObj.getJSONArray("standard");

            for (int i = 0; i < teamArray.length(); i++) {

                JSONObject currentTeam = teamArray.getJSONObject(i);

                String name = currentTeam.getString("fullName");

                boolean isNBAFranchise = currentTeam.getBoolean("isNBAFranchise");

                boolean isAllStar = currentTeam.getBoolean("isAllStar");

                long teamId = currentTeam.getLong("teamId");

                String confName = currentTeam.getString("confName");

                String divName = currentTeam.getString("divName");

                String tricode = currentTeam.getString("tricode");

                NBATeam team = new NBATeam(name, isNBAFranchise, isAllStar, teamId, confName, divName, tricode);

                if (isNBAFranchise) {
                    teams.add(team);
                }


            }

        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the team JSON results", e);
        }

        return teams;
    }

}
