package org.danielsoares.pickupapp.Models;

import com.google.android.gms.maps.model.LatLng;

public class GameLocation {
    private LatLng degrees;
    private String name;

    public GameLocation(String gameName, LatLng gamedegrees) {
        name = gameName;
        degrees = gamedegrees;
    }

    public String toString() {
        return name;
    }
}
