package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.danielsoares.pickupapp.Models.Game_Class;
import org.danielsoares.pickupapp.R;

import java.util.Calendar;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    private Button locationButton;

    private LatLng location;

    private DatabaseReference database;

    // Game info
    private String sportPlay;
    private String hostStart;
    private Location gameLocation;
    private Calendar timebegin;
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
                // Creates Dialogue Fragment
                            }
        });

        locationButton = findViewById(R.id.MapButton);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(map);
            }
        });

        // Firebase reference
        database = FirebaseDatabase.getInstance().getReference();
    }

    // Pulls info about time or location. null otherwise
    private void pullTimeLocation() {
        if (getIntent().getParcelableExtra("Location") != null)
            location = getIntent().getParcelableExtra("Location");

    }

    private void writeNewGame() {

        Game_Class newGame = new Game_Class(sportPlay, hostStart, gameLocation,
                timebegin, timeEnd, max);

        database.child("Games").setValue(newGame);
    }

    // Get id of user
    public String getUid() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }


}
