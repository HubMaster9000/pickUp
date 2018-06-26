package org.danielsoares.pickupapp.Models;

import android.location.Location;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public class Game_Class implements Comparable<Game_Class> {
    private String sport;
    private String host;
    private Location location;
    private Calendar startTime;
    private Calendar endTime;
    private Queue<Player_Class> players;     // infinite list of people in/coming to the game
    private int maxSize;                     // Game could be set to unlimited size, then set this
                                             // to Integer_Max
    // Initialize class
    public Game_Class(String sportPlay, String hostStart, Location gameLocation,
                      Calendar timebegin, Calendar timeEnd, int max) {
        sport = sportPlay;
        host = hostStart;
        location = gameLocation;
        startTime = timebegin;
        endTime = timeEnd;
        players = new LinkedList<>();
        maxSize = max;
    }

    // Add a player to the game
    // returns True if below max capcity, False otherwise
    public boolean add(Player_Class player) {
        // If below the maximum size
        if (size() < maxSize) {
            players.add(player);
            return true;
        }
        else return false;
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

    // Distance from game to player
    public double distanceTo(Location player) {
        return 0.0;
    }

    // Allows us to sort by starting time
    // When sorted, games ALWAYS sort by starting time
    public int compareTo(Game_Class game) {
        return startTime.compareTo(game.startTime());
    }
}
