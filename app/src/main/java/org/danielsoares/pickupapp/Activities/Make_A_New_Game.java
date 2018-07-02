package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;

import org.danielsoares.pickupapp.R;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    private Button locationButton;

    private LatLng location;


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
    }

    // Pulls info about time or location. null otherwise
    private void pullTimeLocation() {
        if (getIntent().getParcelableExtra("Location") != null)
            location = getIntent().getParcelableExtra("Location");

    }


}
