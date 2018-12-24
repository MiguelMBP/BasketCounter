package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Match;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<Match> matches;
    private int layout;
    private Context context;

    public MatchAdapter(List<Match> matches, int layout, Context context) {
        this.matches = matches;
        this.layout = layout;
        this.context = context;
    }

    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ViewHolder viewHolder, int i) {
        final Match match = matches.get(i);

        viewHolder.teamsView.setText(match.getHomeTeam().getName() + " - " + match.getVisitor().getName());
        viewHolder.pointsView.setText(match.getPointsHT() + " - " + match.getPointsV());
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamsView;
        TextView pointsView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamsView = itemView.findViewById(R.id.textViewTeams);
            pointsView = itemView.findViewById(R.id.textViewPoints);
        }
    }
}
