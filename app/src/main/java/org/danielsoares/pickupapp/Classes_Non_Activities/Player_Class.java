package org.danielsoares.pickupapp.Classes_Non_Activities;

// Each account is made into a player class. Right now it's most a collection of Strings, but in the
// future we could use this as the foundation of a player profile, adding new variables like height,
// weight, and other stats.
public class Player_Class {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Player_Class(String first, String last, String username, String passcode) {
        firstName = first;
        lastName = last;
        email = username;
        password = passcode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName + " " + lastName + "/n");
        return sb.toString();
    }

}
