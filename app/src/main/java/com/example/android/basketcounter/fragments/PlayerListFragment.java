package com.example.android.basketcounter.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.adapters.PlayerAdapter;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;
import com.example.android.basketcounter.viewmodel.PlayerViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerListFragment extends Fragment {

    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PlayerAdapter adapter;
    private List<Player> players = new ArrayList<>();
    private Team team;
    private PlayerViewModel model;

    private OnPlayerSelected callback;

    public PlayerListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_players);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createPopUp();

            }
        });

        recyclerView = view.findViewById(R.id.player_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new PlayerAdapter(players, R.layout.player_row, getContext(), new PlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Player player, int position) {
                callback.onSelection(player);
            }

            @Override
            public void onButtonCliked(View v, Player player) {
                if (v.getId() == R.id.editPlayerIcon) {
                    editPlayer(player);

                } else if(v.getId() == R.id.deletePlayerIcon) {
                    deletePlayer(player);
                }
            }
        });

        recyclerView.setAdapter(adapter);



        return view;
    }

    private void deletePlayer(Player player) {
        model.deletePlayer(player);
    }

    private void editPlayer(final Player player) {
        builder = new AlertDialog.Builder(getActivity());
        View view= LayoutInflater.from(getActivity()).inflate(R.layout.popup_player, null);

        builder.setView(view);

        dialog= builder.create();
        dialog.show();

        final EditText playerName= view.findViewById(R.id.popupPlayerName);
        final EditText playerNumber= view.findViewById(R.id.popupPlayerNumber);
        Button saveButton= view.findViewById(R.id.popupSavePlayerButton);

        playerName.setText(player.getName());
        playerNumber.setText(player.getNumber() + "");

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(playerName.getText()) && !TextUtils.isEmpty(playerNumber.getText())){

                    try{
                        player.setName(playerName.getText().toString());
                        player.setNumber(Integer.parseInt(playerNumber.getText().toString()));

                        model.updatePlayer(player);
                    } catch (Exception e) {
                        Toast.makeText(getContext(), "Error updating player", Toast.LENGTH_SHORT)
                                .show();
                    }


                }

                dialog.dismiss();

            }
        });
    }

    private void createPopUp() {

        builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.popup_player, null);

        builder.setView(view);

        dialog = builder.create();
        dialog.show();

        final EditText playerName = view.findViewById(R.id.popupPlayerName);
        final EditText playerNumber= view.findViewById(R.id.popupPlayerNumber);
        Button saveButton = view.findViewById(R.id.popupSavePlayerButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(playerName.getText()) && !TextUtils.isEmpty(playerNumber.getText())) {

                    addPlayer(playerName.getText().toString(), playerNumber.getText().toString());

                }

            }
        });
    }

    private void addPlayer(String name, String number) {

        try{
            Player player = new Player(Integer.parseInt(number), name, team);
            player.setTeamId(team.getTid());

            model.addPlayer(player);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error adding player", Toast.LENGTH_SHORT)
                    .show();
        }


        dialog.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            //callback recibe una referencia a la actividad que implementa la interfaz
            callback = (OnPlayerSelected) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    public void setTeam(Team team) {
        this.team = team;
        createModel();
    }

    private void createModel() {
        model= ViewModelProviders.of(this).get(PlayerViewModel.class);
        model.getPlayersByTeam(team.getTid()).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(List<Player> players) {
                adapter.addPlayers(players, team);

            }
        });
    }

    public interface OnPlayerSelected {
        public void onSelection(Player player);
    }
}
