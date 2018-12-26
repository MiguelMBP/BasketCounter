package com.example.android.basketcounter.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Match;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ViewHolder> {
    private List<Match> matches;
    private int layout;
    private Context context;
    private OnItemClickListener listener;

    public MatchAdapter(List<Match> matches, int layout, Context context, OnItemClickListener listener) {
        this.matches = matches;
        this.layout = layout;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MatchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MatchAdapter.ViewHolder viewHolder, final int i) {
        final Match match = matches.get(i);

        viewHolder.teamsView.setText(match.getHomeTeam().getName() + " - " + match.getVisitor().getName());
        viewHolder.pointsView.setText(match.getPointsHT() + " - " + match.getPointsV());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(match, i);
            }
        });
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

    public interface OnItemClickListener {
        void onItemClick(Match match, int position);
    }
}
