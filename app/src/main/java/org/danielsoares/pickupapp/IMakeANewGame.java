package org.danielsoares.pickupapp;

import org.danielsoares.pickupapp.Models.Game;

public interface IMakeANewGame {

    void createNewGame(String sport, String host, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute, int maxSize, String location);
}
