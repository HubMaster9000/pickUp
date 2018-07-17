package org.danielsoares.pickupapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.danielsoares.pickupapp.Models.Game_Class;
import org.danielsoares.pickupapp.Models.Time;
import org.danielsoares.pickupapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

<<<<<<< HEAD
public class MakeANewGame extends AppCompatActivity implements View.OnClickListener {

    private Button selectStartTimeButton;
    private Button locationButton;
    private Button createGameButton;
    private Button cancelGameButton;
=======
public class MakeANewGame extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button selectStartTimeButton;
    private Button locationButton;
    private Button newGameButton;
    private Spinner selectSports;
    private Spinner selectSize;
>>>>>>> d32090bbe7daf3fd255ea4a8f173f97a3b411813

    private LatLng location;

    private CustomDateTimePicker custom;
    private int currentDay, currentMonth, currentYear, currentHour, currentMinute;
    boolean allowTime;

    private DocumentReference ref = FirebaseFirestore.getInstance().document("Games/");

    private String[] sports, sizes;

    // Game info
    private String sportPlay;
    private String hostStart;
    private Location gameLocation;
    private Time timeBegin;
    private Time timeEnd;
    private int max;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_a_new_game);

<<<<<<< HEAD
        initViews();
        initListeners();

=======
        setStartTime();
        // TODO: Victor: Make setEndTime() method. Refer to setStartTime(). Optimally: we just have one method: setTime, and we add a boolean to determine whether it is start or end time

        /**
         * Pass Directly current time format it will return AM and PM if you set
         * false
         */
        custom.set24HourFormat(false);
        /**
         * Pass Directly current data and time to show when it pop up
         */
        custom.setDate(Calendar.getInstance());
        currentYear = Calendar.YEAR;
        currentMonth = Calendar.MONTH;
        currentDay = Calendar.DAY_OF_MONTH;
        currentHour = Calendar.HOUR;
        currentMinute = Calendar.MINUTE;


        initialize();
        pullLocation();

        // If location is already selected
        if (location != null) {
            locationButton.setBackgroundColor(Color.GREEN);
            locationButton.setText("Location Selected");
        }
    }

    private void setStartTime() {
>>>>>>> d32090bbe7daf3fd255ea4a8f173f97a3b411813
        custom = new CustomDateTimePicker(this,
                new CustomDateTimePicker.ICustomDateTimeListener() {

                    @Override
                    public void onSet(Dialog dialog, Calendar calendarSelected,
                                      Date dateSelected, int year, String monthFullName,
                                      String monthShortName, int monthNumber, int date,
                                      String weekDayFullName, String weekDayShortName,
                                      int hour24, int hour12, int min, int sec,
                                      String AM_PM) {

                        if(currentYear >= year &&
                                currentMonth >= monthNumber &&
                                currentDay >= Calendar.DAY_OF_MONTH) {
                            allowTime = true;
                        } else
                            allowTime = false;

                        if (allowTime = true) {
                            selectStartTimeButton.setText("");
                            selectStartTimeButton.setText(monthFullName + " " + calendarSelected.get(Calendar.DAY_OF_MONTH)
                                    + ", " + hour12 + ":" + min
                                    + " " + AM_PM);
                            selectStartTimeButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.completedGreen));

                            int setDay = date;
                            int setMonth = monthNumber + 1;
                            int setYear = year;
                            int setMinute = min;
                            int setHour = hour24;

                            // Notice this is timeBegin. Change to timeEnd for setEndTime
                            timeBegin = new Time(setYear, setMonth, setDay, setHour, setMinute);
                        } else
                            ;
                    }

                    @Override
                    public void onCancel() {

                    }
                });
    }

    /**
     * Initializes views
     */
    private void initViews() {
        selectStartTimeButton = findViewById(R.id.SelectStartTimeButton);
        locationButton = findViewById(R.id.locationButton);
        createGameButton = findViewById(R.id.createGameButton);
        cancelGameButton = findViewById(R.id.cancelGameButton);

    }

    /**
     * Initializes listeners
     */
    private void initListeners() {
        selectStartTimeButton.setOnClickListener(this);
        locationButton.setOnClickListener(this);
        createGameButton.setOnClickListener(this);
        cancelGameButton.setOnClickListener(this);

    }

    /**
     * Listens the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SelectStartTimeButton:
                // Select Start Time
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        custom.showDialog();
                    }
                };
                break;
            case R.id.locationButton:
                // Select a location
                Intent map = new Intent(getApplicationContext(), AvailableGames.MapsActivity.class);
                startActivity(map);
                break;
            case R.id.createGameButton:
                // Make a new Game
                writeNewGame();
<<<<<<< HEAD
                Intent submitGame = new Intent(getApplicationContext(), AvailableGames.class);
                startActivity(submitGame);
                finish();
                break;
            case R.id.cancelGameButton:
                // Make a new Game
                Intent cancelGame = new Intent(getApplicationContext(), AvailableGames.class);
                startActivity(cancelGame);
                finish();
                break;

        }
=======
                Intent submit = new Intent(getApplicationContext(), AvailableGames.class);
                startActivity(submit);
            }
        });

        selectSports = findViewById(R.id.sport_list);
        ArrayAdapter<CharSequence> adapterSports = ArrayAdapter.createFromResource(this,
                R.array.all_sports, android.R.layout.simple_spinner_item);
        adapterSports.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSports.setAdapter(adapterSports);

        selectSize = findViewById(R.id.size_list);
        ArrayAdapter<CharSequence> adapterSize = ArrayAdapter.createFromResource(this,
                R.array.all_sizes, android.R.layout.simple_spinner_item);
        adapterSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSize.setAdapter(adapterSize);

        Resources res = getResources();
        sports = res.getStringArray(R.array.all_sports);
        sizes = res.getStringArray(R.array.all_sizes);
>>>>>>> d32090bbe7daf3fd255ea4a8f173f97a3b411813
    }

    // Pulls info about time or location. null otherwise
    private void pullLocation() {
        if (getIntent().getParcelableExtra("Location") != null)
            location = getIntent().getParcelableExtra("Location");

    }

    // Creates game and puts into database
    private void writeNewGame() {
        // Unique Key for game
        Game_Class newGame = new Game_Class(sportPlay, hostStart, gameLocation,
                timeBegin, timeEnd, max);
        Map<String, Object> postValues = newGame.toMap();

        ref.set(postValues);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.sport_list :
                sportPlay = sports[i];
                break;
            case R.id.size_list :
                if (sizes[i] == "10+") max = Integer.MAX_VALUE;
                else max = Integer.parseInt(sizes[i]);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
