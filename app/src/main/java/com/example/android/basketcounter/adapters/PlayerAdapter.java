package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {
    private List<Player> players;
    private int layout;
    private Context context;
    private OnItemClickListener listener;

    private Team team;

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

        if (team != null) {
            switch (team.getColor()) {
                case "blue":
                    Picasso.get().load(R.drawable.blue).into(viewHolder.playerIcon);
                    break;

                case "green":
                    Picasso.get().load(R.drawable.green).into(viewHolder.playerIcon);
                    break;

                case "orange":
                    Picasso.get().load(R.drawable.orange).into(viewHolder.playerIcon);
                    break;

                case "red":
                    Picasso.get().load(R.drawable.red).into(viewHolder.playerIcon);
                    break;

                case "pink":
                    Picasso.get().load(R.drawable.pink).into(viewHolder.playerIcon);
                    break;

                case "black": default:
                    Picasso.get().load(R.drawable.black).into(viewHolder.playerIcon);
            }
        }



        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(player, i);
            }
        });
    }
    /* <item>black</item>
        <item>blue</item>
        <item>red</item>
        <item>green</item>
        <item>orange</item>
        <item>pink</item>*/

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void addPlayers(List<Player> players, Team team) {
        this.players = players;
        this.team = team;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView playerView;
        TextView playerNumberView;
        ImageView editPlayer;
        ImageView deletePlayer;
        ImageView playerIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playerView = itemView.findViewById(R.id.textViewPlayer);
            this.playerNumberView = itemView.findViewById(R.id.textViewPlayerNumber);
            this.editPlayer = itemView.findViewById(R.id.editPlayerIcon);
            this.deletePlayer = itemView.findViewById(R.id.deletePlayerIcon);
            this.playerIcon = itemView.findViewById(R.id.playerImage);

            editPlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonCliked(v, players.get(getAdapterPosition()));
                }
            });

            deletePlayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onButtonCliked(v, players.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Player player, int position);
        void onButtonCliked(View v, Player player);
    }
}
