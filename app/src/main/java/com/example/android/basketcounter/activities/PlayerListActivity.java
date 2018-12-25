package com.example.android.basketcounter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.fragments.PlayerListFragment;
import com.example.android.basketcounter.model.Team;

public class PlayerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_list);

        Intent intent = getIntent();

        if (intent != null) {
            Team team = getIntent().getExtras().getParcelable("team");
            PlayerListFragment tlf = (PlayerListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPlayerList);

        }
    }
}
