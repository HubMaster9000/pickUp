package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.danielsoares.pickupapp.Models.Game_Class;
import org.danielsoares.pickupapp.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    private Button locationButton;
    private Button newGameButton;

    private LatLng location;

    private DatabaseReference ref;

    // Game info
    private String sportPlay;
    private String hostStart;
    private Location gameLocation;
    private Calendar timeBegin;
    private Calendar timeEnd;
    private int max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__a__new__game);

        initialize();
        pullTimeLocation();

        // If location is already selected
        if (location != null) {
            locationButton.setBackgroundColor(Color.GREEN);
            locationButton.setText("Location Selected");
        }

    }

    private void initialize() {
        selectTimeButton = findViewById(R.id.SelectTimeButton);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // C reates Dialogue Fragment
                            }
        });

        locationButton = findViewById(R.id.MapButton);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(map);
            }
        });

        newGameButton = findViewById(R.id.new_Game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                writeNewGame();
                Intent submit = new Intent(getApplicationContext(), Available_Games.class);
                startActivity(submit);
            }
        });

        // Firebase reference
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        ref = database.getReference("firestore/data~2FGames");
    }

    // Pulls info about time or location. null otherwise
    private void pullTimeLocation() {
        if (getIntent().getParcelableExtra("Location") != null)
            location = getIntent().getParcelableExtra("Location");

    }

    private void writeNewGame() {

        String key = ref.push().getKey();
        Game_Class newGame = new Game_Class(sportPlay, hostStart, gameLocation,
                timeBegin, timeEnd, max);
        Map<String, Object> postValues = newGame.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, postValues);

        ref.updateChildren(childUpdates);
    }
}
