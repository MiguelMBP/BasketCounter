package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.Stats;

import java.util.List;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.ViewHolder> {
    private List<Stats> stats;
    private int layout;
    private Context context;

    public StatsAdapter(List<Stats> stats, int layout, Context context) {
        this.stats = stats;
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
        final Stats stat = stats.get(i);

        viewHolder.matchNameView.setText("Stats Match " + stat.getMatch().getHomeTeam() + " - " + stat.getMatch().getVisitor());
        viewHolder.pointsView.setText(stat.getPoints());
        viewHolder.tPView.setText(stat.getThreePointers());
        viewHolder.foulsView.setText(stat.getFouls());

    }

    @Override
    public int getItemCount() {
        return stats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView matchNameView;
        TextView pointsView;
        TextView tPView;
        TextView foulsView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            matchNameView = itemView.findViewById(R.id.TextViewMatchName);
            pointsView = itemView.findViewById(R.id.textViewPoints);
            tPView = itemView.findViewById(R.id.textViewTP);
            foulsView = itemView.findViewById(R.id.textViewFouls);
        }
    }
}
