package org.danielsoares.pickupapp.Activities;

import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import org.danielsoares.pickupapp.IMakeANewGame;
import org.danielsoares.pickupapp.Models.Game;
import org.danielsoares.pickupapp.R;

public class MakeANewGame extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, TimePickerDialog.OnTimeSetListener, IMakeANewGame {

    private Button selectStartTimeButton;
    private Button selectEndTimeButton;
    private EditText locationText;
    private Button createGameButton;
    private Button cancelGameButton;
    private Spinner selectedSport;
    private Spinner selectedSize;
    private boolean startTime = false;
    private boolean endTime = false;

    boolean allowTime;

    private FirebaseFirestore db;
    public FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    // Game info
    private String sportPlay;
    private String hostStart;
    private int timeBeginHour;
    private int timeBeginMinute;
    private int timeEndHour;
    private int timeEndMinute;
    private int max;
    private String location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_a_new_game);

        initViews();
        initListeners();


    }

    /**
     * Initializes views
     */
    private void initViews() {
        selectStartTimeButton = findViewById(R.id.SelectStartTimeButton);
        selectEndTimeButton = findViewById(R.id.SelectEndTimeButton);
        locationText = findViewById(R.id.field_location);
        createGameButton = findViewById(R.id.createGameButton);
        cancelGameButton = findViewById(R.id.cancelGameButton);
        user = getIntent().getParcelableExtra("user");
        hostStart = user.getDisplayName();

        //Inititalize spinners
        selectedSport = findViewById(R.id.setSportSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sportsAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_sports, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        sportsAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        selectedSport.setAdapter(sportsAdapter);

        selectedSize = findViewById(R.id.setSizeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this,
                R.array.all_sizes, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        sizeAdapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        selectedSize.setAdapter(sizeAdapter);


    }

    /**
     * Initializes listeners
     */
    private void initListeners() {
        selectStartTimeButton.setOnClickListener(this);
        selectEndTimeButton.setOnClickListener(this);
        locationText.setOnClickListener(this);
        createGameButton.setOnClickListener(this);
        cancelGameButton.setOnClickListener(this);

        selectedSport.setOnItemSelectedListener(this);
        selectedSize.setOnItemSelectedListener(this);

    }

    /**
     * Listens the click on view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.SelectStartTimeButton:
                startTime = true;
                endTime = false;
                DialogFragment timePicker = new TimePicker();
                timePicker.show(getFragmentManager(), "time picker");
                break;
            case R.id.SelectEndTimeButton:
                startTime = false;
                endTime = true;
                DialogFragment timePicker2 = new TimePicker();
                timePicker2.show(getFragmentManager(), "time picker");
                break;
            case R.id.createGameButton:
                // Make a new Game
                writeNewGame();
                Intent submitGame = new Intent(getApplicationContext(), AvailableGames.class);
                startActivity(submitGame);
                finish();
                break;
            case R.id.cancelGameButton:
                // Cancel new Game
                Intent cancelGame = new Intent(getApplicationContext(), AvailableGames.class);
                startActivity(cancelGame);
                finish();
                break;

        }
    }

    @Override
    public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
        if (startTime) {
            selectStartTimeButton.setText(hourOfDay + " : " + minute);
            timeBeginHour = hourOfDay;
            timeBeginMinute = minute;
        } else if (endTime) {
            selectEndTimeButton.setText(hourOfDay + " : " + minute);
            timeEndHour = hourOfDay;
            timeEndMinute = minute;
            if (timeBeginHour > timeEndHour) {
                Toast.makeText(MakeANewGame.this, "End time must be later than start time",
                        Toast.LENGTH_SHORT).show();
            }
            if (timeBeginHour == timeEndHour && timeBeginMinute > timeEndMinute) {
                Toast.makeText(MakeANewGame.this, "End time must be later than start time",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    // Creates game and puts into database
    private void writeNewGame() {
        boolean valid = true;
        //Check that location field isn't empty
        location = locationText.getText().toString();
        if (TextUtils.isEmpty(location)) {
            locationText.setError("Required.");
            valid = false;
        } else {
            locationText.setError(null);
        }

        if (valid) {
            createNewGame(sportPlay, hostStart,
                    timeBeginHour, timeBeginMinute, timeEndHour, timeEndMinute, max, location);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch(adapterView.getId()){
            case R.id.setSportSpinner :
                sportPlay = selectedSport.getSelectedItem().toString();
                break;
            case R.id.setSizeSpinner :
                String selected = selectedSize.getSelectedItem().toString();
                max = Integer.parseInt(selected);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void createNewGame(String sport, String host, int startTimeHour, int startTimeMinute, int endTimeHour, int endTimeMinute, int maxSize, String location) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference newGameRef = db.collection("games")
                .document();

        Game game = new Game();
        game.setSport(sport);
        game.setHost(host);
        game.setStartTimeHour(startTimeHour);
        game.setStartTimeMinute(startTimeMinute);
        game.setEndTimeHour(endTimeHour);
        game.setEndTimeMinute(endTimeMinute);
        game.setMaxSize(maxSize);
        game.setLocation(location);

        newGameRef.set(game).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MakeANewGame.this, "New Game Created",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MakeANewGame.this, "Failed to Create a New Game",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
