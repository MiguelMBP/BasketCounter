package com.example.android.basketcounter.fragments;


import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.TeamAdapter;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.TeamViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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

    private onTeamSelected callback;

    private String color;

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

            @Override
            public void onButtonClicked(View v, Team team) {
                if (v.getId() == R.id.editTeamIcon) {
                    editTeam(team);

                } else if(v.getId() == R.id.deleteTeamIcon) {
                    deleteTeam(team);
                }
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
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_team, null);

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

        final EditText teamName = view.findViewById(R.id.popupTeamName);
        Spinner colorSpinner = view.findViewById(R.id.colorSpinner);
        Button saveButton = view.findViewById(R.id.popupSaveTeamButton);



        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = getResources().getStringArray(R.array.colors_value)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(teamName.getText())) {

                    addTeam(teamName.getText().toString(), color);

                }

            }
        });
    }

    private void addTeam(String teamName, String color) {
        Team team = new Team(teamName, color);
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

    private void editTeam(final Team team) {
        builder = new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.popup_team, null);

        builder.setView(view);

        dialog= builder.create();
        dialog.show();

        final EditText teamName= view.findViewById(R.id.popupTeamName);
        Spinner colorSpinner = view.findViewById(R.id.colorSpinner);
        Button saveButton= view.findViewById(R.id.popupSaveTeamButton);

        teamName.setText(team.getName());

        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                color = getResources().getStringArray(R.array.colors_value)[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(teamName.getText())){

                    team.setName(teamName.getText().toString());
                    team.setColor(color);

                    model.updateTeam(team);

                }

                dialog.dismiss();
            }
        });
    }

    private void deleteTeam(Team team) {
        model.deleteTeam(team);
    }

    public interface onTeamSelected {
        public void onSelection(Team team);
    }



}
