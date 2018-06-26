package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.danielsoares.pickupapp.R;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    private Button locationButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__a__new__game);

        // Initializes Buttons
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


}
