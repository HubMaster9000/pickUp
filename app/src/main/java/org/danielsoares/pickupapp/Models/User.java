package org.danielsoares.pickupapp.Models;

import android.net.Uri;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private Uri photo;

    // Constructor with photo
    public User(int iD, String playerName, String playerEmail, String playerPassword, Uri playerPhoto) {
        id = iD;
        name = playerName;
        email = playerEmail;
        photo = playerPhoto;
    }
    // Constructor without photo
    public User(String playerName, String playerEmail) {
        name = playerName;
        email = playerEmail;
    }
    public User() {
    }

    public void setPhoto(Uri pic) {
        photo = pic;
    }

    public Uri getPhoto() {
        return photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}