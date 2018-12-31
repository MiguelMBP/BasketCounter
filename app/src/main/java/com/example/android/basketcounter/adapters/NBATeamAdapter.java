package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.NBATeam;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NBATeamAdapter extends ArrayAdapter<NBATeam> {

    List<NBATeam> teams;
    Context context;

    public NBATeamAdapter(@NonNull Context context, List<NBATeam> teams) {
        super(context, 0, teams);
        this.teams = teams;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.nbateam_list_item, parent, false);
        }

        NBATeam team = getItem(position);

        TextView name = listItemView.findViewById(R.id.nbateam_name);
        TextView city = listItemView.findViewById(R.id.nbateam_city);
        TextView franchise = listItemView.findViewById(R.id.nbateam_franchise);
        TextView allstar = listItemView.findViewById(R.id.nbateam_allstar);

        name.setText(team.getName());
        city.setText(team.getCity());

        if (team.isAllStar()) {
            allstar.setText("All Star");
        } else {
            allstar.setText("Not All Star");
        }

        if (team.isNBAFranchise()) {
            franchise.setText("NBA Franchise");
        } else {
            franchise.setText("Not NBA Franchise");
        }

        return listItemView;
    }
}
