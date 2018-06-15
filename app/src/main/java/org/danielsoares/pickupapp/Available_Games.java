package org.danielsoares.pickupapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class Available_Games extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sportsDropDown;
    private Spinner distanceDropDown;
    private Spinner sizeDropDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available__games);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // Initialize Spinner for Sports
        sportsDropDown = (Spinner) findViewById(R.id.sport_list);
        sportsDropDown.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSports = ArrayAdapter.createFromResource(this,
                R.array.all_sports, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSports.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sportsDropDown.setAdapter(adapterSports);

        // Initialize Spinner for Distance
        distanceDropDown = (Spinner) findViewById(R.id.distance_list);
        distanceDropDown.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterDistances = ArrayAdapter.createFromResource(this,
                R.array.all_distances, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDistances.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        distanceDropDown.setAdapter(adapterDistances);

        // Initialize Spinner for Size
        sizeDropDown = (Spinner) findViewById(R.id.distance_list);
        sizeDropDown.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSizes = ArrayAdapter.createFromResource(this,
                R.array.all_sizes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sizeDropDown.setAdapter(adapterSizes);
    }
    // Implements filters for spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        /* Use the following switch-statement if you want to keep all spinner actions/callbacks together */
        /* The same can be done to the onNothingSelected callback */
        switch(parent.getId()) {
            case R.id.sport_list:
                // Do stuff for sport drop down menu...Filter Stuff
                break;
            case R.id.distance_list:
                // Do stuff for sport drop down menu...Filter Stuff
                break;
            case R.id.size_list:
                // Do stuff for sport drop down menu...Filter Stuff
                break;
        }
    }
    // Useless method...but needed for spinner override stuff
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "You selected nothing", Toast.LENGTH_LONG).show();
    }
}
