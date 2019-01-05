package com.example.android.basketcounter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.CounterPlayerAdapter;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.PlayerViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CounterPlayerFragment extends Fragment implements CounterPlayerAdapter.OnPlayerClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CounterPlayerAdapter adapter;
    private List<Player> players = new ArrayList<>();
    private List<Stats> stats;

    private PlayerViewModel Playermodel;
    private Team team;
    private boolean homeVisit;      //true: home, false: visitor
    private CounterFragment fragmentParent;


    public CounterPlayerFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter_player, container, false);

        fragmentParent = (CounterFragment) getParentFragment();

        recyclerView = view.findViewById(R.id.counter_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new CounterPlayerAdapter(players, R.layout.counter_player_row, getContext(), this);

        recyclerView.setAdapter(adapter);



        return view;
    }


    @Override
    public void onPlayerClick(View view, Player player) {
        if (view.getId() == R.id.plusFreeThrow) {
            fragmentParent.addPoints(1, homeVisit);
            stats.get(getPlayerPosition(player)).freeThrow();

        } else if (view.getId() == R.id.plusTwoPointer) {
            fragmentParent.addPoints(2, homeVisit);
            stats.get(getPlayerPosition(player)).twoPointer();


        } else if (view.getId() == R.id.plusThreePointer) {
            fragmentParent.addPoints(3, homeVisit);
            stats.get(getPlayerPosition(player)).threePointer();


        } else if (view.getId() == R.id.plusFoul) {
            fragmentParent.addFoul(homeVisit);
            stats.get(getPlayerPosition(player)).foul();


        } else if (view.getId() == R.id.cancelFreeThrow) {
            if (stats.get(getPlayerPosition(player)).getFreeThrows() > 0) {
                fragmentParent.addPoints(-1, homeVisit);
                stats.get(getPlayerPosition(player)).cancelFreeThrow();
            }

        } else if (view.getId() == R.id.cancelTwoPointer) {
            if (stats.get(getPlayerPosition(player)).getTwoPointers() > 0) {
                fragmentParent.addPoints(-2, homeVisit);
                stats.get(getPlayerPosition(player)).cancelTwoPointer();
            }


        } else if (view.getId() == R.id.cancelThreePointer) {
            if (stats.get(getPlayerPosition(player)).getThreePointers() > 0) {
                fragmentParent.addPoints(-3, homeVisit);
                stats.get(getPlayerPosition(player)).cancelThreePointer();
            }


        } else if (view.getId() == R.id.cancelFoul) {
            if (stats.get(getPlayerPosition(player)).getFouls() > 0) {
                fragmentParent.cancelFoul(homeVisit);
                stats.get(getPlayerPosition(player)).cancelFoul();
            }


        }
    }

    private int getPlayerPosition(Player player) {
        for (int i = 0; i < stats.size(); i++) {
            if (stats.get(i).getPlayerId() == player.getId()) {
                return i;
            }
        }
        return -1;
    }

    public void setTeam(final Team team, boolean homeVisit) {
        this.team = team;
        this.homeVisit = homeVisit;

        Playermodel= ViewModelProviders.of(this).get(PlayerViewModel.class);
        if (team != null) {
            Playermodel.getPlayersByTeam(team.getTid()).observe(this, new Observer<List<Player>>() {
                @Override
                public void onChanged(List<Player> players) {
                    adapter.addPlayers(players, team);

                    stats = new ArrayList<>();

                    for (int i = 0; i < players.size(); i++) {
                        stats.add(new Stats(players.get(i)));/**/
                    }

                }
            });
        }

    }

    public List<Stats> getStats() {
        return stats;
    }
}
