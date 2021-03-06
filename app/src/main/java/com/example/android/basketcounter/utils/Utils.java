package com.example.android.basketcounter.utils;

import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Stats;
import com.example.android.basketcounter.model.Team;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static long QUARTER_TIME = 600000;
    public static long POSSESSION_TIME = 24000;

    public static List<Player> getDummyPlayers() {
        return new ArrayList<Player>() {{
            add(new Player(1, "Jugador 1", new Team("a", "black")));
            add(new Player(2, "Jugador 2", new Team("b", "black")));
            add(new Player(3, "Jugador 3", new Team("c", "black")));
            add(new Player(4, "Jugador 4", new Team("d", "black")));
            add(new Player(5, "Jugador 5", new Team("e", "black")));
            add(new Player(6, "Jugador 6", new Team("f", "black")));
        }};
    }

    public static List<Match> getDummyMatches() {
        return new ArrayList<Match>() {{
            add(new Match(new Team("Equipo A", "black"), new Team("Equipo B", "black"), 5, 10));
            add(new Match(new Team("Equipo C", "black"), new Team("Equipo D", "black"), 15, 40));
            add(new Match(new Team("Equipo E", "black"), new Team("Equipo F", "black"), 50, 14));
            add(new Match(new Team("Equipo G", "black"), new Team("Equipo H", "black"), 20, 21));

        }};
    }

    public static List<Team> getDummyTeams() {
        return new ArrayList<Team>() {{
            add(new Team("Equipo A", "black"));
            add(new Team("Equipo B", "black"));
            add(new Team("Equipo C", "black"));
            add(new Team("Equipo D", "black"));

        }};
    }

    public static List<Stats> getDummyStats() {
        return new ArrayList<Stats>() {{
           add(new Stats(new Player(), new Match(), 1, 2, 3, 4, 5));
        }};
    }

}
