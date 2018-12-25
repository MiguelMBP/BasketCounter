package com.example.android.basketcounter.fragments;

import android.content.Context;
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
import com.example.android.basketcounter.adapters.TeamAdapter;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.utils.Utils;

import java.util.List;

public class TeamListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TeamAdapter adapter;
    List<Team> teams;

    onTeamSelected callback;

    public TeamListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_list, container, false);

        recyclerView = view.findViewById(R.id.team_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        teams = Utils.getDummyTeams();

        adapter = new TeamAdapter(teams, R.layout.team_row, getContext(), new TeamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Team team, int position) {
                callback.onSelection(team);
            }
        });

        recyclerView.setAdapter(adapter);

        return view;
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
