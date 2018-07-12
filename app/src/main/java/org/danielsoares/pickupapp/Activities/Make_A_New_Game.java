package org.danielsoares.pickupapp.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.danielsoares.pickupapp.Models.Game_Class;
import org.danielsoares.pickupapp.R;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class Make_A_New_Game extends AppCompatActivity {

    private Button selectTimeButton;
    private Button locationButton;
    private Button newGameButton;

    private LatLng location;

    private CustomDateTimePicker custom;
    private int setDay, setMonth, setYear, setHour, setMinute;
    private int currentDay, currentMonth, currentYear, currentHour, currentMinute;
    boolean allowTime;

    private DocumentReference ref = FirebaseFirestore.getInstance().document("Games/");

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
                            selectTimeButton.setText("");
                            selectTimeButton.setText(monthFullName + " " + calendarSelected.get(Calendar.DAY_OF_MONTH)
                                    + ", " + hour12 + ":" + min
                                    + " " + AM_PM);
                            selectTimeButton.setBackgroundColor(getResources().getColor(R.color.completedGreen));

                            setDay = date;
                            setMonth = monthNumber;
                            setYear = year;
                            setMinute = min;
                            setHour = hour24;
                        } else
                            ;
                    }

                    @Override
                    public void onCancel() {

                    }
                });

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
        pullTimeLocation();

        // If location is already selected
        if (location != null) {
            locationButton.setBackgroundColor(Color.GREEN);
            locationButton.setText("Location Selected");
        }
    }

    private void initialize() {
        selectTimeButton = findViewById(R.id.selectTimeButton);
        selectTimeButton.setOnClickListener(
                new View.OnClickListener() {

                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(View v) {
                        custom.showDialog();
                    }
                });

        locationButton = findViewById(R.id.mapButton);
        locationButton.setOnClickListener(new View.OnClickListener() {
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
    }

    // Pulls info about time or location. null otherwise
    private void pullTimeLocation() {
        if (getIntent().getParcelableExtra("Location") != null)
            location = getIntent().getParcelableExtra("Location");

    }

    private void writeNewGame() {
        // Unique Key for game
        Game_Class newGame = new Game_Class(sportPlay, hostStart, gameLocation,
                timeBegin, timeEnd, max);
        Map<String, Object> postValues = newGame.toMap();

        ref.set(postValues);
    }
}
