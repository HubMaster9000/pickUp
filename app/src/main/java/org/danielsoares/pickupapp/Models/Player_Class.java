package org.danielsoares.pickupapp.Models;

import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

// Each account is made into a player class using either the custom login or google account
public class Player_Class {

    private User customLogin;
    private GoogleSignInAccount googleAccount;
    private int accountType;

    private final int CUSTOM = 0;
    private final int GOOGLE = 1;

    // Constuctors
    public Player_Class(User custom) {
        customLogin = custom;
        accountType = CUSTOM;
    }
    public Player_Class(GoogleSignInAccount google) {
        googleAccount = google;
        accountType = GOOGLE;
    }

    public String getName() {
        if (accountType == CUSTOM) return customLogin.getName();
        if (accountType == GOOGLE) return googleAccount.getDisplayName();
    }

    public Uri getPhoto() {
        // if (accountType == CUSTOM) return
        if (accountType == GOOGLE) return googleAccount.getPhotoUrl();
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName + " " + lastName + "/n");
        return sb.toString();
    }

}
