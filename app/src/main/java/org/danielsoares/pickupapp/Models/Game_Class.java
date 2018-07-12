package org.danielsoares.pickupapp.Models;

import android.location.Location;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Game_Class implements Comparable<Game_Class> {
    private String sport;
    private String host;
    private Location location;
    private Time startTime;
    private Time endTime;
    private Queue<Player_Class> players;     // infinite list of people in/coming to the game
    private int maxSize;                     // Game could be set to unlimited size, then set this
                                             // to Integer_Max
    // Initialize class
    public Game_Class(String sportPlay, String hostStart, Location gameLocation, Time timeBegin, Time timeEnd,
                      int max) {
        sport = sportPlay;
        host = hostStart;
        location = gameLocation;
        startTime = timeBegin;
        endTime = timeEnd;
        players = new LinkedList<>();
        maxSize = max;
    }

    public Game_Class() {}

    // Add a player to the game
    // returns True if below max capacity, False otherwise
    public boolean add(Player_Class player) {
        // If below the maximum size
        if (players.size() < maxSize) {
            players.add(player);
            return true;
        }
        else return false;
    }

    // Change later for convenience
    // Prints out game info
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(sport + "/n");
        sb.append(startTime + " - " + endTime + "/n");
        sb.append("Host:" + host + "/n");
        return sb.toString();
    }

    // Distance from game to player
    public double distanceTo(Location player) {
        return 0.0;
    }

    // Allows us to sort by starting time
    // When sorted, games ALWAYS sort by starting time
    public int compareTo(Game_Class game) {
        return startTime.compareTo((Time) game.toMap().get("Start Time"));
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> values = new HashMap<>();
        values.put("Sport", sport);
        values.put("Host", host);
        values.put("Location", location);
        values.put("Start Time", startTime);
        values.put("End Time", endTime);
        values.put("Players", players);
        values.put("Capacity", maxSize);
        return values;
    }
}
