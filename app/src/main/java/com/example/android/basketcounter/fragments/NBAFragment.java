package com.example.android.basketcounter.fragments;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.NBATeamAdapter;
import com.example.android.basketcounter.model.NBATeam;
import com.example.android.basketcounter.viewmodel.NBAViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class NBAFragment extends Fragment {

    private List<NBATeam> teams;
    private NBATeamAdapter adapter;
    private TextView emptyview;
    ProgressBar progressBar;

    public NBAFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nba, container, false);

        teams = new ArrayList<>();

        ListView nbaListView = view.findViewById(R.id.nba_list);
        emptyview = view.findViewById(R.id.NoFoundTextView);
        progressBar = view.findViewById(R.id.progressBar);

        adapter = new NBATeamAdapter(getActivity(), teams);

        nbaListView.setAdapter(adapter);
        nbaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NBATeam team = teams.get(position);
                String url = "https://stats.nba.com/team/" + team.getTeamId() + "/";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        ConnectivityManager connectivityManager= (ConnectivityManager) getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        boolean isConnected= info != null && info.isConnected();

        if (isConnected) {
            NBAViewModel model = ViewModelProviders.of(this).get(NBAViewModel.class);

            model.getTeams().observe(this, new Observer<List<NBATeam>>() {
                @Override
                public void onChanged(List<NBATeam> nbaTeams) {

                    progressBar.setVisibility(View.GONE);

                    adapter.clear();
                    if (teams != null) {
                        adapter.addAll(nbaTeams);
                    } else {
                        emptyview.setText("No teams Found");
                    }


                }
            });
        } else {
            progressBar.setVisibility(View.GONE);
            emptyview.setText("No Internet Connection");
        }

        return view;
    }
}
