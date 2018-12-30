package com.example.android.basketcounter.activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.fragments.MatchDetailsFragment;
import com.example.android.basketcounter.model.Match;

import androidx.appcompat.app.AppCompatActivity;

public class MatchDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_details);

        Intent intent = getIntent();

        if (intent != null) {
            Match match = getIntent().getExtras().getParcelable("match");
            MatchDetailsFragment mdf = (MatchDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentMatchDetails);
            mdf.renderMatch(match);
        }
    }
}
