package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Player;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CounterPlayerAdapter extends RecyclerView.Adapter<CounterPlayerAdapter.ViewHolder> {

    private List<Player> players;
    private int layout;
    private Context context;
    private OnPlayerClickListener listener;

    public CounterPlayerAdapter(List<Player> players, int layout, Context context, OnPlayerClickListener listener) {
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
        viewHolder.playerNameView.setText(player.getName());


    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView playerIconView;
        TextView playerNameView;
        TextView freeThrowView;
        TextView twoPointerView;
        TextView threePointerView;
        TextView foulView;
        TextView cancelFreeThrowView;
        TextView cancelTwoPointerView;
        TextView cancelThreePointerView;
        TextView cancelFoulView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.playerIconView = itemView.findViewById(R.id.imageViewPlayer);
            this.playerNameView = itemView.findViewById(R.id.textViewPlayerName);

            this.freeThrowView = itemView.findViewById(R.id.plusFreeThrow);
            this.twoPointerView = itemView.findViewById(R.id.plusTwoPointer);
            this.threePointerView = itemView.findViewById(R.id.plusThreePointer);
            this.foulView = itemView.findViewById(R.id.plusFoul);

            this.cancelFreeThrowView = itemView.findViewById(R.id.cancelFreeThrow);
            this.cancelTwoPointerView = itemView.findViewById(R.id.cancelTwoPointer);
            this.cancelThreePointerView = itemView.findViewById(R.id.cancelThreePointer);
            this.cancelFoulView = itemView.findViewById(R.id.cancelFoul);

            this.freeThrowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.twoPointerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.threePointerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.foulView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.cancelFreeThrowView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.cancelTwoPointerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.cancelThreePointerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });

            this.cancelFoulView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onPlayerClick(view, players.get(getAdapterPosition()));
                }
            });
        }
    }

    public void addPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public interface OnPlayerClickListener{
        void onPlayerClick(View view, Player player);
    }
}
