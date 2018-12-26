package com.example.android.basketcounter.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.TeamAdapter;
import com.example.android.basketcounter.data.DataBaseRoom;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.utils.Utils;
import com.example.android.basketcounter.viewmodel.TeamViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TeamAdapter adapter;
    private List<Team> teamList = new ArrayList<>();
    private TeamViewModel model;

    onTeamSelected callback;

    public TeamListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);



        recyclerView = view.findViewById(R.id.team_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        adapter = new TeamAdapter(teamList, R.layout.team_row, getContext(), new TeamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Team team, int position) {
                callback.onSelection(team);
            }
        });

        recyclerView.setAdapter(adapter);

        /*model = ViewModelProviders.of(this).get(TeamViewModel.class);
        model.getTeams().observe(this, new androidx.lifecycle.Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                adapter.addTeams(teams);
            }
        });*/

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.team_list_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_team:
                Team t = new Team("Equipo0");

                return true;
        }
        return false;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (onTeamSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    public interface onTeamSelected {
        public void onSelection(Team team);
    }



}
