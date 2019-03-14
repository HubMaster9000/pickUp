package org.danielsoares.pickupapp.Models;

import android.net.Uri;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.io.Serializable;

// Each account is made into a player class using either the custom login or google account
public class Player_Class implements Serializable{

    private User customLogin;
    private GoogleSignInAccount googleAccount;
    private int accountType;

    private final int CUSTOM = 0;
    private final int GOOGLE = 1;

    // Constructors
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
        else if (accountType == GOOGLE) return googleAccount.getDisplayName();
        else return null;
    }

    public Uri getPhoto() {
        // if (accountType == CUSTOM) return
        if (accountType == GOOGLE) return googleAccount.getPhotoUrl();
        else return null;
    }

}
