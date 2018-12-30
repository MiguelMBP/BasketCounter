package com.example.android.basketcounter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.MatchAdapter;
import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.MatchViewModel;
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

public class MatchListFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    MatchAdapter adapter;
    List<Match> matches = new ArrayList<>();
    MatchViewModel matchViewModel;
    TeamViewModel teamViewModel;
    List<Team> teams;

    OnMatchSelected callback;

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

        //matches = Utils.getDummyMatches();

        adapter = new MatchAdapter(matches, R.layout.match_row, getContext(), new MatchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Match match, int position) {
                callback.onSelection(match);
            }
        });

        recyclerView.setAdapter(adapter);

        matchViewModel = ViewModelProviders.of(this).get(MatchViewModel.class);
        teamViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);

        teamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> list) {
                teams = list;
            }
        });

        matchViewModel.getMatchList().observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(final List<Match> matches) {
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
                adapter.addMatches(matches);
            }
        });

        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (OnMatchSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }

    }

    public interface OnMatchSelected {
        public void onSelection(Match match);
    }
}
