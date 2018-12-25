package com.example.android.basketcounter.utils;

import com.example.android.basketcounter.model.Match;
import com.example.android.basketcounter.model.Player;
import com.example.android.basketcounter.model.Team;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<Player> getDummyPlayers() {
        return new ArrayList<Player>() {{
            add(new Player(1, "Jugador 1", new Team("a")));
            add(new Player(2, "Jugador 2", new Team("b")));
            add(new Player(3, "Jugador 3", new Team("c")));
            add(new Player(4, "Jugador 4", new Team("d")));
            add(new Player(5, "Jugador 5", new Team("e")));
            add(new Player(6, "Jugador 6", new Team("f")));
        }};
    }

    public static List<Match> getDummyMatches() {
        return new ArrayList<Match>() {{
            add(new Match(new Team("Equipo A"), new Team("Equipo B"), 5, 10));
            add(new Match(new Team("Equipo C"), new Team("Equipo D"), 15, 40));
            add(new Match(new Team("Equipo E"), new Team("Equipo F"), 50, 14));
            add(new Match(new Team("Equipo G"), new Team("Equipo H"), 20, 21));

        }};
    }

    public static List<Team> getDummyTeams() {
        return new ArrayList<Team>() {{
            add(new Team("Equipo A"));
            add(new Team("Equipo B"));
            add(new Team("Equipo C"));
            add(new Team("Equipo D"));

        }};
    }

}
