package com.example.android.basketcounter.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.fragments.PlayerDetailsFragment;
import com.example.android.basketcounter.fragments.PlayerListFragment;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerListActivity extends AppCompatActivity implements PlayerListFragment.OnPlayerSelected {

    private boolean isMultiPanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        Intent intent = getIntent();

        if (intent != null) {
            Team team = getIntent().getExtras().getParcelable("team");
            PlayerListFragment tlf = (PlayerListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPlayerList);
            tlf.setTeam(team);
        }
    }

    @Override
    public void onSelection(Player player) {
        setMultiPanel();

        if (isMultiPanel) {
            PlayerDetailsFragment pdf = (PlayerDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPlayerDetails);
            pdf.renderPlayer(player);

        } else {
            Intent intent = new Intent(PlayerListActivity.this, PlayerDetailsActivity.class);
            intent.putExtra("player", player);
            startActivity(intent);
        }

    }

    private void setMultiPanel() {
        isMultiPanel = isMultiPanel = (getSupportFragmentManager().findFragmentById(R.id.fragmentPlayerDetails) != null);
    }
}
