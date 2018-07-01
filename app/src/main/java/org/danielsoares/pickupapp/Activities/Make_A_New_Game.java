package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.quickstart.database.models.Post;
import com.google.firebase.quickstart.database.models.User;

import com.google.android.gms.maps.model.LatLng;

import org.danielsoares.pickupapp.R;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    private Button locationButton;

    private LatLng location;

    private DatabaseReference database;


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
        selectTimeButton = (Button) findViewById(R.id.SelectTimeButton);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Creates Dialogue Fragment
                            }
        });

        locationButton = (Button) findViewById(R.id.MapButton);
        selectTimeButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent map = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(map);
            }
        });
    }

    // Pulls info about time or location. null otherwise
    private void pullTimeLocation() {
        if (getIntent().getParcelableExtra("Location") != null)
            location = (LatLng) getIntent().getParcelableExtra("Location");

    }


}
