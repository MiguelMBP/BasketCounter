package com.example.android.basketcounter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.viewmodel.StatsViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class MatchDetailsFragment extends Fragment {
    private TextView teams;
    private TextView teamA;
    private TextView teamB;
    private TextView pointsA;
    private TextView pointsB;

    StatsViewModel statsViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_details, container, false);

        statsViewModel = ViewModelProviders.of(this).get(StatsViewModel.class);

        teams = view.findViewById(R.id.textViewTeamDetails);
        teamA = view.findViewById(R.id.textViewMatchNameA);
        teamB = view.findViewById(R.id.textViewMatchNameB);
        pointsA = view.findViewById(R.id.textViewMatchPointsA);
        pointsB = view.findViewById(R.id.textViewMatchPointsB);

        return view;
    }

    public void renderMatch(Match match) {

        teams.setText(match.getHomeTeam().getName() + " - " + match.getVisitor().getName());
        teamA.setText(match.getHomeTeam().getName());
        teamB.setText(match.getVisitor().getName());
        pointsA.setText(match.getPointsHT() + "");
        pointsB.setText(match.getPointsV() + "");

    }
}
