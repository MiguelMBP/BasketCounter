package com.example.android.basketcounter.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MatchDetailsFragment extends Fragment {
    private TextView teams;
    private TextView teamA;
    private TextView teamB;
    private TextView pointsA;
    private TextView pointsB;
    private TextView tPA;
    private TextView tPB;
    private TextView foulsA;
    private TextView foulsB;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_details, container, false);

        teams = view.findViewById(R.id.textViewTeamDetails);
        teamA = view.findViewById(R.id.textViewMatchNameA);
        teamB = view.findViewById(R.id.textViewMatchNameB);
        pointsA = view.findViewById(R.id.textViewMatchPointsA);
        pointsB = view.findViewById(R.id.textViewMatchPointsB);
        tPA = view.findViewById(R.id.textViewMatchTPA);
        tPB = view.findViewById(R.id.textViewMatchTPB);
        foulsA = view.findViewById(R.id.textViewMatchFoulsA);
        foulsB = view.findViewById(R.id.textViewMatchFoulsB);

        return view;
    }
}
