package com.example.android.basketcounter.utils;

import com.example.android.basketcounter.model.CounterPlayer;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<CounterPlayer> getDummyData() {
        return new ArrayList<CounterPlayer>() {{
            add(new CounterPlayer("Jugador 1"));
            add(new CounterPlayer("Jugador 2"));
            add(new CounterPlayer("Jugador 3"));
            add(new CounterPlayer("Jugador 4"));
            add(new CounterPlayer("Jugador 5"));
            add(new CounterPlayer("Jugador 6"));

        }};
    }

}
