package com.example.android.basketcounter.fragments;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.TeamAdapter;
import com.example.android.basketcounter.data.DataBaseRoom;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.utils.Utils;
import com.example.android.basketcounter.viewmodel.TeamViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamListFragment extends Fragment {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TeamAdapter adapter;
    private List<Team> teamList = new ArrayList<>();
    private TeamViewModel model;

    onTeamSelected callback;

    public TeamListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_teams);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createPopUp();

            }
        });

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

        model = ViewModelProviders.of(this).get(TeamViewModel.class);
        model.getTeams().observe(this, new androidx.lifecycle.Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                adapter.addTeams(teams);
            }
        });

        return view;
    }


    private void createPopUp() {

        builder = new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.popup_team, null);

        builder.setView(view);

        dialog= builder.create();
        dialog.show();

        final EditText teamName= view.findViewById(R.id.popupTeamName);
        Button saveButton= view.findViewById(R.id.popupSaveProductButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(teamName.getText())) {

                    addTeam(teamName.getText().toString());

                }

            }
        });
    }

    private void addTeam(String teamName) {
        Team team = new Team(teamName);
        model.addTeam(team);

        dialog.dismiss();
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
