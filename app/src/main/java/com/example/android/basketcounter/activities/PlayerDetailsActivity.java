package com.example.android.basketcounter.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.fragments.PlayerDetailsFragment;
import com.example.android.basketcounter.model.Player;

import androidx.appcompat.app.AppCompatActivity;

public class PlayerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        Intent intent = getIntent();

        if (intent != null) {
            Player player = getIntent().getExtras().getParcelable("player");

            PlayerDetailsFragment pdf = (PlayerDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentPlayerDetails);
            pdf.renderPlayer(player);
        }
    }
}
