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
import com.example.android.basketcounter.adapters.PlayerAdapter;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.utils.Utils;

import java.util.List;

public class PlayerListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    PlayerAdapter adapter;
    List<Player> players;

    private OnPlayerSelected callback;

    public PlayerListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_list, container, false);

        recyclerView = view.findViewById(R.id.player_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        players = Utils.getDummyPlayers();

        adapter = new PlayerAdapter(players, R.layout.player_row, getContext(), new PlayerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Player player, int position) {
                callback.onSelection(player);
            }
        });

        recyclerView.setAdapter(adapter);


        return view;
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

    public interface OnPlayerSelected {
        public void onSelection(Player player);
    }
}
