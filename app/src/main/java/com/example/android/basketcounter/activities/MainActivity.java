package com.example.android.basketcounter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.basketcounter.R;
import com.example.android.basketcounter.fragments.CounterFragment;
import com.example.android.basketcounter.fragments.MatchListFragment;
import com.example.android.basketcounter.fragments.NBAFragment;
import com.example.android.basketcounter.fragments.TeamListFragment;
import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Team;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, TeamListFragment.onTeamSelected, MatchListFragment.OnMatchSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Counter Fragment selected on startup
        navigationView.getMenu().getItem(0).setChecked(true);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new CounterFragment())
                .commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_counter) {
            fragment = new CounterFragment();

        } else if (id == R.id.nav_Teams) {
            fragment = new TeamListFragment();

        } else if (id == R.id.nav_Matches) {
            fragment = new MatchListFragment();

        } else if (id == R.id.nav_NBA) {
            fragment = new NBAFragment();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onSelection(Team team) {
        Intent intent = new Intent(MainActivity.this, PlayerListActivity.class);
        intent.putExtra("team", team);
        startActivity(intent);
    }

    @Override
    public void onSelection(Match match) {
        Intent intent = new Intent(MainActivity.this, MatchDetailsActivity.class);
        intent.putExtra("match", match);
        startActivity(intent);
    }
}
