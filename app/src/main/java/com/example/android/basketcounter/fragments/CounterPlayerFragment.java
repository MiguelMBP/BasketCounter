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
import com.example.android.basketcounter.adapters.CounterPlayerAdapter;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.utils.Utils;

import java.util.List;

public class CounterPlayerFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    CounterPlayerAdapter adapter;
    List<Player> players;


    public CounterPlayerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter_player, container, false);

        recyclerView = view.findViewById(R.id.counter_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        players = Utils.getDummyPlayers();

        adapter = new CounterPlayerAdapter(players, R.layout.counter_player_row, getContext());

        recyclerView.setAdapter(adapter);

        return view;
    }
}
