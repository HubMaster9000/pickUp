package org.danielsoares.pickupapp.Models;


import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@IgnoreExtraProperties
public class Game {
    private String sport;
    private String host;
    private String location;
    private int startTimeHour;
    private int startTimeMinute;
    private int endTimeHour;
    private int endTimeMinute;
    private Queue<Player_Class> players;     // infinite list of people in/coming to the game
    private int maxSize;                     // Game could be set to unlimited size, then set this
                                             // to Integer_Max
    // Initialize class
    public Game(String sport, String host, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute, int maxSize, String location) {
        this.sport = sport;
        this.host = host;
        this.startTimeHour = startTimeHour;
        this.startTimeMinute = startTimeMinute;
        this.endTimeHour = endTimeHour;
        this.endTimeMinute = endTimeMinute;
        this.players = new LinkedList<>();
        this.maxSize = maxSize;
        this.location = location;
    }

    public Game() {

    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getStartTimeHour() {
        return startTimeHour;
    }

    public void setStartTimeHour(int startTimeHour) {
        this.startTimeHour = startTimeHour;
    }

    public int getStartTimeMinute() {
        return startTimeMinute;
    }

    public void setStartTimeMinute(int startTimeMinute) {
        this.startTimeMinute = startTimeMinute;
    }

    public int getEndTimeHour() {
        return endTimeHour;
    }

    public void setEndTimeHour(int endTimeHour) {
        this.endTimeHour = endTimeHour;
    }

    public int getEndTimeMinute() {
        return endTimeMinute;
    }

    public void setEndTimeMinute(int endTimeMinute) {
        this.endTimeMinute = endTimeMinute;
    }


    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

}
