package org.danielsoares.pickupapp.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.danielsoares.pickupapp.Models.Player_Class;
import org.danielsoares.pickupapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Available_Games extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner sportsDropDown;
    private Spinner distanceDropDown;
    private Spinner sizeDropDown;
    private FloatingActionButton newGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available__games);
        /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); */

        // Initialize Stuff
        initialize();


        // Initialize views in xml
        TextView prof_name = (TextView) findViewById(R.id.account_Name);
        CircleImageView prof_pic = (CircleImageView) findViewById(R.id.account_Pic);

        // Pull Google account info
        Player_Class account = (Player_Class) getIntent().getParcelableExtra("Account");

        // Google Login info
        String name = account.getName();
        String img_URL = account.getPhoto().toString();

        // Put info into xml
        prof_name.setText(name);
        prof_pic.setImageURI(Uri.parse(img_URL));
    }

    private void initialize() {
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
        sizeDropDown = (Spinner) findViewById(R.id.size_list);
        sizeDropDown.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterSizes = ArrayAdapter.createFromResource(this,
                R.array.all_sizes, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterSizes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sizeDropDown.setAdapter(adapterSizes);

        // When button is clicked, go to page to make new game
        newGameButton = (FloatingActionButton) findViewById(R.id.new_Game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent newGame = new Intent(getApplicationContext(), Make_A_New_Game.class);
                startActivity(newGame);            }
        });
    }
    // Implements filters for spinners
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
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
