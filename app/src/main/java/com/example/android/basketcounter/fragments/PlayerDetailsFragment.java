package com.example.android.basketcounter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.StatsAdapter;
import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.MatchViewModel;
import com.example.android.basketcounter.viewmodel.StatsViewModel;
import com.example.android.basketcounter.viewmodel.TeamViewModel;
import com.squareup.picasso.Picasso;

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
    private ImageView playerIcon;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private StatsAdapter adapter;
    private List<Stats> stats = new ArrayList<>();
    private List<Team> teams;
    private List<Match> matches;

    private TeamViewModel teamViewModel;
    private StatsViewModel statsViewModel;
    private MatchViewModel matchViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_detail, container, false);

        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        statsViewModel = ViewModelProviders.of(this).get(StatsViewModel.class);
        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);

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
        playerIcon = view.findViewById(R.id.textViewPlayerDetailsIcon);

        return view;
    }


    public void renderPlayer(Player player) {

        teamViewModel.getTeamById(player.getTeamId()).observe(this, new Observer<Team>() {
            @Override
            public void onChanged(Team team) {
                teamName.setText(team.getName());
                if (team != null) {
                    switch (team.getColor()) {
                        case "blue":
                            Picasso.get().load(R.drawable.blue).into(playerIcon);
                            break;

                        case "green":
                            Picasso.get().load(R.drawable.green).into(playerIcon);
                            break;

                        case "orange":
                            Picasso.get().load(R.drawable.orange).into(playerIcon);
                            break;

                        case "red":
                            Picasso.get().load(R.drawable.red).into(playerIcon);
                            break;

                        case "pink":
                            Picasso.get().load(R.drawable.pink).into(playerIcon);
                            break;

                        case "black": default:
                            Picasso.get().load(R.drawable.black).into(playerIcon);
                    }
                }
            }
        });

        playerName.setText(player.getName());

        teamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> list) {
                teams = list;
            }
        });

        matchViewModel.getMatchList().observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(List<Match> list) {
                matches = list;
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

                if (matches != null) {
                    for (int i = 0; i < matches.size(); i++) {
                        for (int j = 0; j < teams.size(); j++) {
                            if (matches.get(i).getHomeTeamId() == teams.get(j).getTid()) {
                                matches.get(i).setHomeTeam(teams.get(j));
                            }

                            if (matches.get(i).getVisitorId() == teams.get(j).getTid()) {
                                matches.get(i).setVisitor(teams.get(j));
                            }
                        }
                    }

                    for (int i = 0; i < stats.size(); i++) {
                        puntos += stats.get(i).getPoints();
                        triples += stats.get(i).getThreePointers();

                        for (int j = 0; j < matches.size(); j++) {
                            if (stats.get(i).getMatchId() == matches.get(j).getId()) {
                                stats.get(i).setMatch(matches.get(j));
                            }
                        }
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


            }
        });

    }
}
