package org.danielsoares.pickupapp;

import android.location.Location;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Queue;

public class Game_Class implements Comparable<Game_Class> {
    private String sport;
    private String host;
    private Location location;    // Change class later
    private Calendar startTime;      // Change class later
    private Calendar endTime;        // Change class later
    private Queue<Player_Class> players;

    // Initialize class
    public Game_Class(String sportPlay, String hostStart, Location gameLocation,
                      Calendar timebegin, Calendar timeEnd) {
        sport = sportPlay;
        host = hostStart;
        location = gameLocation;
        startTime = timebegin;
        endTime = timeEnd;
    }

    // Add a player to the game
    public void add(Player_Class player) {
        players.add(player);
        return;
    }

    // Current number of people playing
    public int size() {
        return players.size();
    }

    // Get location of game
    public Location location() {
        return location;
    }
    // List of all players playing
    public Iterable<Player_Class> players() {
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

    // Get start and end time
    public Calendar startTime() {
        return startTime;
    }
    public Calendar endTime() {
        return endTime;
    }

    public int compareTo(Game_Class game) {
        return compareTo(startTime(), game.startTime());
    }
}
