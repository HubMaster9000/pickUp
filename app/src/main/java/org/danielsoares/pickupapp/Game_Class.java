package org.danielsoares.pickupapp;

import java.util.Queue;

public class Game_Class {
    private String sport;
    private String host;
    private String location;    // Change class later
    private int startTime;      // Change class later
    private int endTime;        // Change class later
    private Queue<int> players; // Change Queue class later

    // Change input class to player
    public void add(int player) {
        players.add(player);
        return;
    }

    public int size() {
        return players.size();
    }

}
