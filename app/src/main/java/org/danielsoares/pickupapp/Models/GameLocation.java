package org.danielsoares.pickupapp.Models;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GameLocation implements Serializable {
    private Location location;
    private String name;

    public GameLocation(String gameName, LatLng gamedegrees) {
        name = gameName;
        double lat = gamedegrees.latitude;
        double lng = gamedegrees.longitude;
        location.setLatitude(lat);
        location.setLongitude(lng);
    }

    public float distanceTo(GameLocation that) {
        return location.distanceTo((Location) that.toMap().get("Location"));
    }
    public float distanceTo(Location that) {
        return location.distanceTo(that);
    }

    public LatLng getLatLng() {
        double Lng = location.getLongitude();
        double Lat = location.getLatitude();
        LatLng latlng = new LatLng(Lat, Lng);
        return latlng;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> values = new HashMap<>();
        values.put("Name", name);
        values.put("Location", location);
        return values;
    }
    public String toString() {
        return name;
    }
}
