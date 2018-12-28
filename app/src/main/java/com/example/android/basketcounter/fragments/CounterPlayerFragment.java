package com.example.android.basketcounter.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.CounterPlayerAdapter;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.utils.Utils;
import com.example.android.basketcounter.viewmodel.PlayerViewModel;
import com.example.android.basketcounter.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CounterPlayerFragment extends Fragment implements CounterPlayerAdapter.OnPlayerClickListener {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CounterPlayerAdapter adapter;
    private List<Player> players = new ArrayList<>();

    private PlayerViewModel Playermodel;
    private Team team;

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

        adapter = new CounterPlayerAdapter(players, R.layout.counter_player_row, getContext(), this);

        recyclerView.setAdapter(adapter);



        return view;
    }


    @Override
    public void onPlayerClick(View view, Player player) {
        if (view.getId() == R.id.plusFreeThrow) {
            Toast.makeText(getContext(), "+1", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.plusTwoPointer) {
            Toast.makeText(getContext(), "+2", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.plusThreePointer) {
            Toast.makeText(getContext(), "+3", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.plusFoul) {
            Toast.makeText(getContext(), "F", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.cancelFreeThrow) {
            Toast.makeText(getContext(), "-1", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.cancelTwoPointer) {
            Toast.makeText(getContext(), "-2", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.cancelThreePointer) {
            Toast.makeText(getContext(), "-3", Toast.LENGTH_SHORT).show();

        } else if (view.getId() == R.id.cancelFoul) {
            Toast.makeText(getContext(), "CF", Toast.LENGTH_SHORT).show();

        }
    }

    public void setTeam(Team team) {
        this.team = team;

        Playermodel= ViewModelProviders.of(this).get(PlayerViewModel.class);
        Playermodel.getPlayersByTeam(team.getTid()).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                adapter.addPlayers(players);

            }
        });
    }
}
