package com.example.android.basketcounter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.StatsAdapter;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.StatsViewModel;
import com.example.android.basketcounter.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerDetailsFragment extends Fragment {

    private TextView teamName;
    private TextView playerName;
    private TextView totalPoints;
    private TextView totalTP;
    private TextView ppm;
    private TextView tppm;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private StatsAdapter adapter;
    List<Stats> stats = new ArrayList<>();
    List<Team> teams;

    private TeamViewModel teamViewModel;
    private StatsViewModel statsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_detail, container, false);

        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        statsViewModel = ViewModelProviders.of(this).get(StatsViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerViewStats);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        //stats = Utils.getDummyStats();

        adapter = new StatsAdapter(stats, R.layout.stat_row, getContext());

        recyclerView.setAdapter(adapter);

        teamName = view.findViewById(R.id.textViewTeamName);
        playerName = view.findViewById(R.id.textViewFragmentPlayerName);
        totalPoints = view.findViewById(R.id.textViewGlobalPoints);
        totalTP = view.findViewById(R.id.textViewGlobalTP);
        ppm = view.findViewById(R.id.textViewPPM);
        tppm = view.findViewById(R.id.textViewGlobalTPPM);

        return view;
    }


    public void renderPlayer(Player player) {

        teamViewModel.getTeamById(player.getTeamId()).observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                teamName.setText(team.getName());
            }
        });

        playerName.setText(player.getName());

        teamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> list) {
                teams = list;
            }
        });

        statsViewModel.getStats(player.getId()).observe(this, new Observer<List<Stats>>() {
            @Override
            public void onChanged(List<Stats> stats) {
                int partidos = stats.size();
                int puntos = 0;
                int triples = 0;
                double ppmN;
                double tppmN;

                for (int i = 0; i < stats.size(); i++) {
                    puntos += stats.get(i).getPoints();
                    triples += stats.get(i).getThreePointers();

                    //if (stats.get(i).getPlayerId().)
                }

                totalPoints.setText(puntos + "");
                totalTP.setText(triples + "");
                if (partidos != 0){
                    ppmN = puntos / partidos;
                    tppmN = triples / partidos;
                } else {
                    ppmN = 0;
                    tppmN = 0;
                }


                ppm.setText(String.format("%1$,.2f", ppmN));
                tppm.setText(String.format("%1$,.2f", tppmN));

                adapter.addStats(stats);

            }
        });

    }
}
