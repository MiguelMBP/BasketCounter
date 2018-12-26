package com.example.android.basketcounter.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Team;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {
    private List<Team> teams;
    private int layout;
    private Context context;
    private OnItemClickListener listener;

    public TeamAdapter(List<Team> teams, int layout, Context context, OnItemClickListener listener) {
        this.teams = teams;
        this.layout = layout;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        final Team team = teams.get(i);
        viewHolder.teamView.setText(team.getName());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                listener.onItemClick(team, i);
            }
        });
    }

    public void addTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.teamView = itemView.findViewById(R.id.textViewTeam);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Team team, int position);
    }
}
