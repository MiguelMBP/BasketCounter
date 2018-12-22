package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.CounterPlayer;

import java.util.List;

public class CounterPlayerAdapter extends RecyclerView.Adapter<CounterPlayerAdapter.ViewHolder> {

    private List<CounterPlayer> players;
    private int layout;
    private Context context;

    public CounterPlayerAdapter(List<CounterPlayer> players, int layout, Context context) {
        this.players = players;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final CounterPlayer player = players.get(i);
        viewHolder.playerNameView.setText(player.getName());
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView playerNameView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playerNameView = itemView.findViewById(R.id.textViewPlayerName);
        }
    }
}
