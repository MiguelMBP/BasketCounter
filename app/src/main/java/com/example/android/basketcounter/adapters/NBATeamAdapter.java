package com.example.android.basketcounter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.model.NBATeam;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NBATeamAdapter extends ArrayAdapter<NBATeam> {

    private List<NBATeam> teams;
    private Context context;

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
        ImageView icon = listItemView.findViewById(R.id.nba_icon);

        name.setText(team.getName());
        city.setText(team.getConfName() + " - " + team.getDivNAme());

        if (team.isAllStar()) {
            allstar.setText(R.string.allstar);
        } else {
            allstar.setText(R.string.nallstar);
        }

        if (team.isNBAFranchise()) {
            franchise.setText(R.string.nbafranchise);
        } else {
            franchise.setText(R.string.notnbafranchise);
        }

        String iconUrl = "https://www.nba.com/assets/logos/teams/primary/web/" + team.getTricode() + ".png";
        Picasso.get().load(iconUrl).error(R.drawable.nba).into(icon);

        return listItemView;
    }

}
