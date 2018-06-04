package org.danielsoares.pickupapp;

import java.util.Queue;

public class Game_Class {
    private String sport;
    private String host;
    private String location;    // Change class later
    private int startTime;      // Change class later
    private int endTime;        // Change class later
    private Queue<Integer> players; // Change Queue class later

    // Initialize class
    public Game_Class(String sportPlay, String hostStart, String gameLocation,
                      int timebegin, int timeEnd) {
        sport = sportPlay;
        host = hostStart;
        location = gameLocation;
        startTime = timebegin;
        endTime = timeEnd;
    }

    // Change input class to player
    // Add a player to the game
    public void add(int player) {
        players.add(player);
        return;
    }

    // Current number of people playing
    public int size() {
        return players.size();
    }

    // Change class of Queue to players
    // List of all players playing
    public Iterable<Integer> players() {
        return players;
    }

    // Change later for convinence
    // Prints out game info
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sport + "/n");
        sb.append(startTime + " - " + endTime + "/n");
        sb.append("Host:" + host + "/n");
        return sb.toString();
    }
}
