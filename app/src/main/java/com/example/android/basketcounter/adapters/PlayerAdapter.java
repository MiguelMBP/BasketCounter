package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> players;
    private int layout;
    private Context context;
    private OnItemClickListener listener;

    public PlayerAdapter(List<Player> players, int layout, Context context, OnItemClickListener listener) {
        this.players = players;
        this.layout = layout;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Player player = players.get(i);

        viewHolder.playerView.setText(player.getName());
        viewHolder.playerNumberView.setText(player.getNumber() + "");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(player, i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void addPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerView;
        TextView playerNumberView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playerView = itemView.findViewById(R.id.textViewPlayer);
            this.playerNumberView = itemView.findViewById(R.id.textViewPlayerNumber);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(Player player, int position);
    }
}
