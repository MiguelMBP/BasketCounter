package com.example.android.basketcounter.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.MatchAdapter;
import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.utils.Utils;

import java.util.List;

public class MatchListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MatchAdapter adapter;
    List<Match> matches;

    public MatchListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_list, container, false);

        recyclerView = view.findViewById(R.id.match_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        matches = Utils.getDummyMatches();

        adapter = new MatchAdapter(matches, R.layout.match_row, getContext());

        recyclerView.setAdapter(adapter);

        return view;

    }
}
