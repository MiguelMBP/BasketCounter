package com.example.android.basketcounter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.PlayerViewModel;
import com.example.android.basketcounter.viewmodel.TeamViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class CounterFragment extends Fragment {

    private TeamViewModel teamModel;

    private Spinner homeSpinner;
    private Spinner visitSpinner;
    private TextView homePoints;
    private TextView visitPoints;
    private TextView homeFouls;
    private TextView visitFouls;
    private TextView possesion;
    private TextView quarter;
    private TextView quarterTime;

    private Team teamHome;
    private Team teamVisit;
    private List<Team> teamList;


    public CounterFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_counter, container, false);

        homeSpinner = view.findViewById(R.id.local_spinner);
        visitSpinner = view.findViewById(R.id.visitante_spinner);
        homePoints = view.findViewById(R.id.homePoints);
        homeFouls = view.findViewById(R.id.homeFouls);
        visitPoints = view.findViewById(R.id.visitPoints);
        visitFouls = view.findViewById(R.id.visitFouls);

        final ImageView resetPossesion = (ImageView) view.findViewById(R.id.resetPossesion);
        ImageView pausePosesion = (ImageView) view.findViewById(R.id.pausePossesion);
        ImageView continuePosesion = (ImageView) view.findViewById(R.id.continuePossesion);
        ImageView previousQuarter = (ImageView) view.findViewById(R.id.previousQuarter);
        ImageView pauseQuater = (ImageView) view.findViewById(R.id.pauseQuarter);
        ImageView continueQuarter = (ImageView) view.findViewById(R.id.continueQuarter);
        ImageView nextQuarter = (ImageView) view.findViewById(R.id.nextQuarter);


        fillSpinners();

        homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamHome = teamList.get(position);

                changeHome();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        visitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamVisit = teamList.get(position);

                changeVisit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        resetPossesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPossesion();
            }
        });

        pausePosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausePossesion();
            }
        });

        continuePosesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continuePossesion();
            }
        });

        previousQuarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousQuarter();
            }
        });

        pauseQuater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseQuater();
            }
        });

        continueQuarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueQuarter();
            }
        });

        nextQuarter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuarter();
            }
        });

        return view;
    }




    private void fillSpinners() {
        teamModel = ViewModelProviders.of(this).get(TeamViewModel.class);
        teamModel.getTeams().observe(this, new androidx.lifecycle.Observer<List<Team>>() {
            @Override
            public void onChanged(@Nullable List<Team> teams) {
                String[] teamNames = new String[teams.size()];

                for (int i = 0; i < teams.size(); i++) {
                    teamNames[i] = teams.get(i).getName();
                }

                ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, teamNames);
                aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                homeSpinner.setAdapter(aa);
                visitSpinner.setAdapter(aa);

                teamList = teams;

            }
        });
    }

    private void changeHome() {
        //getChildSupportManager porque el fragment está dentro de este
        CounterPlayerFragment cpfHome = (CounterPlayerFragment) getChildFragmentManager().findFragmentById(R.id.fragmentLocal);
        cpfHome.setTeam(teamHome);
    }

    private void changeVisit(){
        CounterPlayerFragment cpfVisit = (CounterPlayerFragment) getChildFragmentManager().findFragmentById(R.id.fragmentVisitante);
        cpfVisit.setTeam(teamVisit);
    }

    private void resetPossesion() {
        Toast.makeText(getContext(), "reset P", Toast.LENGTH_SHORT)
                .show();
    }

    private void continuePossesion() {
        Toast.makeText(getContext(), "continue P", Toast.LENGTH_SHORT)
                .show();
    }

    private void pausePossesion() {
        Toast.makeText(getContext(), "pause P", Toast.LENGTH_SHORT)
                .show();
    }

    private void nextQuarter() {
        Toast.makeText(getContext(), "next Q", Toast.LENGTH_SHORT)
                .show();
    }

    private void continueQuarter() {
        Toast.makeText(getContext(), "continue Q", Toast.LENGTH_SHORT)
                .show();
    }

    private void pauseQuater() {
        Toast.makeText(getContext(), "pause Q", Toast.LENGTH_SHORT)
                .show();
    }

    private void previousQuarter() {
        Toast.makeText(getContext(), "previous Q", Toast.LENGTH_SHORT)
                .show();
    }

}
